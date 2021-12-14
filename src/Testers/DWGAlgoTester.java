package Testers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;
import api.DWGAlgo;
import api.DWG;
import api.NodeData;
import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.*;
import org.w3c.dom.Node;

public class DWGAlgoTester {

    @Test
    void isConnected() {
        DirectedWeightedGraph g = new DWG();

        NodeData n1 = new Vertex(10);
        NodeData n2 = new Vertex(15);
        NodeData n3 = new Vertex(20);
        NodeData n4 = new Vertex(42);

        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);

        g.connect(10,15,3);
        g.connect(15,42,10);
        g.connect(42,15,20);
        g.connect(42,20,15);
        DirectedWeightedGraphAlgorithms ag1 = new DWGAlgo();
        ag1.init(g);

        assertFalse(ag1.isConnected());

        g.connect(10, 42, 1);
        g.connect(20, 10, 12);
        assertTrue(ag1.isConnected());

        g.removeEdge(15, 42);
        assertFalse(ag1.isConnected());
    }

    @Test
    void shortestPathDist() {
        //init a graph
        DirectedWeightedGraph g = new DWG();
        //add some nodes
        NodeData n1 = new Vertex(10);
        NodeData n2 = new Vertex(15);
        NodeData n3 = new Vertex(20);
        NodeData n4 = new Vertex(42);
        //add some node with similar key
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);
        //add connection
        g.connect(10,15,3);
        g.connect(15,42,10);
        g.connect(42,15,20);
        g.connect(42,20,15);
        g.connect(10, 42, 1);
        g.connect(20, 10, 12);

        DirectedWeightedGraphAlgorithms ag1 = new DWGAlgo();
        ag1.init(g);
        //Should return Strongly Connected Graph = True
        assertTrue(ag1.isConnected());
        double d = ag1.shortestPathDist(10,42);
        assertEquals(d, 1);
        //changed for a higher weight so he should return another path
        g.connect(10, 42, 22);
        d = ag1.shortestPathDist(10,42);
        assertEquals(d, 13);
    }

    @Test
    void shortestPath() {
        DirectedWeightedGraph g = new DWG();

        NodeData n1 = new Vertex(10);
        NodeData n2 = new Vertex(15);
        NodeData n3 = new Vertex(20);
        NodeData n4 = new Vertex(42);

        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);

        g.connect(10,15,3);
        g.connect(15,42,10);
        g.connect(42,15,20);
        g.connect(42,20,15);
        g.connect(10, 42, 15);
        g.connect(20, 10, 12);

        DirectedWeightedGraphAlgorithms ag1 = new DWGAlgo();
        ag1.init(g);
        List<NodeData> l1 = ag1.shortestPath(10,42);

        int[] check = {10, 15,42};
        int i = 0;

        for(NodeData n: l1) {
            assertEquals(n.getKey(), check[i]);
            i++;
        }
        List<NodeData> l1swap = ag1.shortestPath(42,10);
        int[] checkswap = {42, 20,10};
        i = 0;
        for(NodeData n: l1swap) {
            assertEquals(n.getKey(), checkswap[i]);
            i++;
        }
        g.connect(10, 42, 1);
        l1 = ag1.shortestPath(10,42);
        int[] check2 = {10,42};
        i = 0;
        for(NodeData n: l1) {
            assertEquals(n.getKey(), check2[i]);
            i++;
        }
    }

    @Test
    void center() {
        DirectedWeightedGraph dwgAlgo = new DWG();
        boolean flag;
        dwgAlgo.addNode(new Vertex(1));
        dwgAlgo.addNode(new Vertex(2));
        dwgAlgo.addNode(new Vertex(3));
        dwgAlgo.addNode(new Vertex(4));
        dwgAlgo.addNode(new Vertex(5));
        dwgAlgo.addNode(new Vertex(6));
        dwgAlgo.addNode(new Vertex(7));
        dwgAlgo.addNode(new Vertex(8));
        dwgAlgo.addNode(new Vertex(9));
        dwgAlgo.connect(1, 4, 1);
        dwgAlgo.connect(2, 3, 1);
        dwgAlgo.connect(6, 7, 1);
        dwgAlgo.connect(3, 4, 1);
        dwgAlgo.connect(4, 5, 1);
        dwgAlgo.connect(3, 8, 1);
        dwgAlgo.connect(5, 8, 1);
        dwgAlgo.connect(8, 9, 1);
        dwgAlgo.connect(5, 6, 1);
        dwgAlgo.connect(1, 2, 1);
        DirectedWeightedGraphAlgorithms ag1 = new DWGAlgo();
        ag1.init(dwgAlgo);

        flag = ag1.isConnected();
        assertFalse(ag1.isConnected());
    }

    public static <T> Collection <T> IteratorToCollection(Iterator <T> itr) {
        Collection <T> DS = new ArrayList<T>();

        for (Iterator<T> it = itr; it.hasNext();) {
            T t = it.next();
            DS.add(t);
        }

        return DS;
    }

    @Test
    void tsp() {
        DirectedWeightedGraph dwgAlgo = new DWG();
        dwgAlgo.addNode(new Vertex(1, 1, 0, ""));
        dwgAlgo.addNode(new Vertex(2, 1, 0, ""));
        dwgAlgo.addNode(new Vertex(3, 1, 0, ""));
        dwgAlgo.addNode(new Vertex(4, 1, 0, ""));
        dwgAlgo.addNode(new Vertex(5, 1, 0, ""));
        dwgAlgo.connect(1, 2, 2);
        dwgAlgo.connect(2, 1, 1);
        dwgAlgo.connect(2, 4, 1);
        dwgAlgo.connect(2, 3, 2);
        dwgAlgo.connect(3, 4, 3);
        dwgAlgo.connect(4, 3, 1);
        dwgAlgo.connect(4, 5, 2);
        dwgAlgo.connect(1, 5, 4);
        dwgAlgo.connect(5, 1, 2);
        dwgAlgo.connect(1, 3, 1);
        dwgAlgo.connect(3, 2, 2);
        dwgAlgo.connect(4, 1, 1);
        dwgAlgo.connect(2, 5, 1);

        Iterator <NodeData> it = dwgAlgo.nodeIter();
        List <NodeData> list = (List) IteratorToCollection(it);
        List <NodeData> list1 = new LinkedList<NodeData>();

        DirectedWeightedGraphAlgorithms ag1 = new DWGAlgo();
        ag1.init(dwgAlgo);

        list1 = ag1.tsp(list);

        assertEquals(list1.get(0).getKey(), 1);
        assertEquals(list1.get(1).getKey(), 5);
        assertEquals(list1.get(2).getKey(), 2);
        assertEquals(list1.get(3).getKey(), 3);
        assertEquals(list1.get(4).getKey(), 4);
    }

    @Test
    void save_load() {
        boolean check;

        DirectedWeightedGraph g = new DWG();
        DirectedWeightedGraph g2 = new DWG();

        NodeData n1 = new Vertex(10);
        NodeData n2 = new Vertex(15);
        NodeData n3 = new Vertex(20);
        NodeData n4 = new Vertex(42);

        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);

        g.connect(10,15,3);
        g.connect(15,42,10);
        g.connect(42,15,20);
        g.connect(42,20,15);
        g.connect(10, 42, 15);
        g.connect(20, 10, 12);

        NodeData b1 = new Vertex(1);
        NodeData b2 = new Vertex(2);
        NodeData b3 = new Vertex(3);
        NodeData b4 = new Vertex(4);

        g2.addNode(b1);
        g2.addNode(b2);
        g2.addNode(b3);
        g2.addNode(b4);

        g2.connect(1,3,12);
        g2.connect(4,1,1);
        g2.connect(2,1,3);
        g2.connect(4,2,10);
        g2.connect(2, 4, 20);
        g2.connect(3, 4, 15);

        DirectedWeightedGraphAlgorithms ag1 = new DWGAlgo();

        ag1.init(g);
        ag1.save("g.json");
        ag1.init(g2);
        ag1.save("gg.json");

        ag1.load("g.json");
        check = ((DWGAlgo) ag1).equalsGraph(g);
        assertEquals(check,true);

        ag1.load("gg.json");
        check = ((DWGAlgo) ag1).equalsGraph(g2);
        assertEquals(check,true);
    }
}