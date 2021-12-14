package MyGUI;

import api.DirectedWeightedGraphAlgorithms;

import javax.swing.*;

public class ShortestPathDistGUI {
    private DirectedWeightedGraphAlgorithms g;

    public ShortestPathDistGUI(DirectedWeightedGraphAlgorithms graph){
        this.g = graph;
        JFrame frame=new JFrame();
        String source = JOptionPane.showInputDialog(frame,"Source Node");
        String dest = JOptionPane.showInputDialog(frame,"Destination Node");

        try{
            int src=Integer.parseInt(source);
            int d=Integer.parseInt(dest);
            JOptionPane.showMessageDialog(frame,"The shortest path destination is:" + this.g.shortestPathDist(src,d));
            frame.repaint();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
