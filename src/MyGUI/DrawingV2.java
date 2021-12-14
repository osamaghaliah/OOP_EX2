package MyGUI;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class DrawingV2 extends JFrame implements ActionListener {
    private DrawingV1 MyStructure;
    private DirectedWeightedGraph graph;
    private DirectedWeightedGraphAlgorithms graphAlgo;
    private JMenuBar CustomMenu;
    private JMenu g;
    private JMenuItem connectBUTTON;
    private JMenuItem removeNodeBUTTON;
    private JMenuItem removeEdgeBUTTON;
    private JMenuItem nodeSizeBUTTON;
    private JMenuItem edgeSizeBUTTON;
    private JMenu MyGraphAlgo;
    private JMenuItem isConnectedBUTTON;
    private JMenuItem shortestPathDist;
    private JMenuItem shortestPath;
    private JMenuItem center;
    private JMenuItem save;

    public DrawingV2(DirectedWeightedGraphAlgorithms ans) {
        super();
        this.MyStructure = new DrawingV1(ans.getGraph());
        this.graph = ans.getGraph();
        this.graphAlgo=ans;
        build();
        this.setResizable(true);
        this.setVisible(true);

    }
    private void build() {
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
        this.g = new JMenu("Operations");
        this.connectBUTTON = new JMenuItem("Connect Two Nodes");
        this.connectBUTTON.addActionListener(this);
        this.removeNodeBUTTON = new JMenuItem("Remove A Node");
        this.removeNodeBUTTON.addActionListener(this);
        this.removeEdgeBUTTON = new JMenuItem("Remove An Edge");
        this.removeEdgeBUTTON.addActionListener(this);
        this.nodeSizeBUTTON = new JMenuItem("Show The Number Of Nodes");
        this.nodeSizeBUTTON.addActionListener(this);
        this.edgeSizeBUTTON = new JMenuItem("Show The Number Of Edges");
        this.edgeSizeBUTTON.addActionListener(this);
        this.MyGraphAlgo = new JMenu("Algorithms");
        this.isConnectedBUTTON = new JMenuItem("IsConnected()");
        this.isConnectedBUTTON.addActionListener(this);
        this.shortestPathDist = new JMenuItem("shortestPathDist()");
        this.shortestPathDist.addActionListener(this);
        this.shortestPath = new JMenuItem("shortestPath");
        this.shortestPath.addActionListener(this);
        this.center = new JMenuItem("center()");
        this.center.addActionListener(this);
        this.save = new JMenuItem("Save A Json");
        this.save.addActionListener(this);
        this.MyGraphAlgo.add(isConnectedBUTTON);
        this.MyGraphAlgo.add(shortestPathDist);
        this.MyGraphAlgo.add(shortestPath);
        this.MyGraphAlgo.add(center);
        this.MyGraphAlgo.add(save);
        this.g.add(connectBUTTON);
        this.g.add(removeNodeBUTTON);
        this.g.add(removeEdgeBUTTON);
        this.g.add(nodeSizeBUTTON);
        this.g.add(edgeSizeBUTTON);
        CustomMenu = new JMenuBar();
        CustomMenu.add(g);
        CustomMenu.add(MyGraphAlgo);
        setJMenuBar(CustomMenu);
        this.add(this.MyStructure);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == connectBUTTON) {
            JFrame frame = new JFrame();
            String source = JOptionPane.showInputDialog(frame, "Source Node");
            String dest = JOptionPane.showInputDialog(frame, "Destination Node");
            String weight = JOptionPane.showInputDialog(frame, "Weight of the Edge");
            try {
                int src = Integer.parseInt(source);
                int des = Integer.parseInt(dest);
                double wet = Double.parseDouble(weight);
                this.graph.connect(src, des, wet);
                this.graphAlgo.init(this.graph);
                this.add(MyStructure);
                repaint();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if(e.getSource() == removeNodeBUTTON) {
            JFrame frame=new JFrame();
            String key=JOptionPane.showInputDialog(frame,"Enter the wanted node's key to remove: ");
            try{
                int KeyNode=Integer.parseInt(key);
                this.graph.removeNode(KeyNode);
                this.graphAlgo.init(graph);
                repaint();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if(e.getSource() == removeEdgeBUTTON) {
            JFrame frame = new JFrame();
            String source = JOptionPane.showInputDialog(frame,"Source ");
            String dest = JOptionPane.showInputDialog(frame,"Destination ");

            try
            {
                int src = Integer.parseInt(source);
                int des = Integer.parseInt(dest);
                graph.removeEdge(src, des);
                this.graphAlgo.init(graph);
                repaint();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if (e.getSource() == nodeSizeBUTTON) {
            JFrame frame=new JFrame();
            JOptionPane.showMessageDialog(frame,"The number of nodes in this graph is: " + this.graph.nodeSize());
        }
        else if(e.getSource() == edgeSizeBUTTON) {
            JFrame frame=new JFrame();
            JOptionPane.showMessageDialog(frame,"The number of edges in this graph is: " + this.graph.edgeSize());
        }
        else if(e.getSource() == isConnectedBUTTON) {
            JFrame frame=new JFrame();
            if(this.graphAlgo.isConnected()){
                JOptionPane.showMessageDialog(frame,"This graph is connected.");
            }else {
                JOptionPane.showMessageDialog(frame,"This graph is NOT connected.");
            }
        }
        else if(e.getSource() == shortestPathDist) {
            new ShortestPathDistGUI(this.graphAlgo);
        }
        else if(e.getSource() == shortestPath) {
            JFrame frame=new JFrame();
            String source=JOptionPane.showInputDialog(frame,"Enter a source node: ");
            String d=JOptionPane.showInputDialog(frame,"Enter a destination node: ");
            try {
                int src=Integer.parseInt(source);
                int dest=Integer.parseInt(d);
                List<NodeData> l=this.graphAlgo.shortestPath(src,dest);
                int [] arr=new int[l.size()];
                for (int i = 0; i < arr.length; i++) {
                    arr[i]=l.get(i).getKey();
                }
                JOptionPane.showMessageDialog(frame,""+Arrays.toString(arr));
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        else if(e.getSource() == center) {
            JFrame frme=new JFrame();
           JOptionPane.showMessageDialog(frme,"The center of this graph is: " + this.graphAlgo.center().getKey());
        }

        else if(e.getSource() == save) {
            JFrame frame=new JFrame();
            String FileName=JOptionPane.showInputDialog(frame,"Insert a name: ");
            try {
                this.graphAlgo.save(FileName);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

    }
}
