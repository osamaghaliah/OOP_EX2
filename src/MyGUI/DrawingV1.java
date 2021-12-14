package MyGUI;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Iterator;

public class DrawingV1 extends JPanel {
    private DirectedWeightedGraph graph;
    private double minX;
    private double minY;
    private double maxX;
    private double maxY;
    private double wantedXcor;
    private double wantedYcor;
    private final int ARR_SIZE = 8;

    public DrawingV1(DirectedWeightedGraph graph) {
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.graph = graph;
        updateXY();
    }

    private void updateXY() {
        Iterator <NodeData> n = graph.nodeIter();
        NodeData node = n.next();
        minX = node.getLocation().x();
        minY = node.getLocation().y();

        maxX = node.getLocation().x();
        maxY = node.getLocation().y();
        while (n.hasNext()) {
            node = n.next();
            minX = Math.min(minX, node.getLocation().x());
            minY = Math.min(minY, node.getLocation().y());

            maxX = Math.max(maxX, node.getLocation().x());
            maxY = Math.max(maxY, node.getLocation().y());
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.wantedXcor = this.getWidth() / Math.abs(maxX - minX)*0.89999 ;
        this.wantedYcor = this.getHeight() / Math.abs(maxY - minY)*0.855;
        drawGraph(g);
    }

    private void drawGraph(Graphics g) {
        Iterator<NodeData> NodesIter=this.graph.nodeIter();
        while(NodesIter.hasNext()){
            NodeData n=NodesIter.next();
            drawNode(g,n);
            Iterator<EdgeData> edgesIter=this.graph.edgeIter(n.getKey());
            while(edgesIter.hasNext()){
                EdgeData e=edgesIter.next();
                drawEdge(g,e);
            }
        }
    }

    public void drawNode(Graphics g,NodeData node) {
        int x = (int) ((node.getLocation().x() - this.minX) * this.wantedXcor);
        int y = (int) ((node.getLocation().y() - this.minY) * this.wantedYcor);
        g.setColor(Color.yellow);
        g.fillOval(x, y, 28, 28);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Tahoma", Font.BOLD, 15));
        g.drawString(String.valueOf(node.getKey()), x+8 , y+18);

    }


    public void drawEdge(Graphics g,EdgeData edge) {
        double x1 = graph.getNode(edge.getSrc()).getLocation().x();
        x1 = ((x1 - minX) * this.wantedXcor) + 20;
        double x2 = graph.getNode(edge.getSrc()).getLocation().y();
        x2 = ((x2 - minY) * this.wantedYcor) + 20;

        double y1 = this.graph.getNode(edge.getDest()).getLocation().x();
        y1 = ((y1 - this.minX) * this.wantedXcor) + 20;
        double y2 = this.graph.getNode(edge.getDest()).getLocation().y();
        y2 = ((y2 - this.minY) * this.wantedYcor) + 20;

        g.setColor(Color.RED.darker());
        drawArrow(g,(int) x1, (int) x2, (int) y1, (int) y2);
        String weightString =String.valueOf(edge.getWeight()) ;
        weightString = weightString.substring(0,weightString.indexOf(".")+3);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Tahoma", Font.BOLD, 12));
        g.drawString(weightString, (int)(x1*0.25 + y1*0.75),(int)(x2*0.25 + y2*0.75));

    }
    void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
        Graphics2D g = (Graphics2D) g1.create();
        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);
        g.drawLine(4, 3, len, 4);
        g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
                new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }
}
