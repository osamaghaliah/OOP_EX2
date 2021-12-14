package Testers;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Collection;
import java.util.Iterator;

import api.*;
import org.junit.jupiter.api.Test;

public class DWGTester {
    @Test
    void nodeSize() {
        DirectedWeightedGraph g = new DWG();

        Vertex n1 = new Vertex(10);
        Vertex n2 = new Vertex(15);
        Vertex n3 = new Vertex(20);
        Vertex n4 = new Vertex(42);

        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);

        g.removeNode(n1.getKey());
        g.removeNode(n3.getKey());
        g.removeNode(n3.getKey());

        int size = g.nodeSize();

        assertEquals(2,size);
        g.addNode(n3);
        g.addNode(n3);
        size = g.nodeSize();
        assertEquals(3,size);
    }

    @Test
    void edgeSize() {
        DirectedWeightedGraph g = new DWG();

        Vertex n1 = new Vertex(10);
        Vertex n2 = new Vertex(15);
        Vertex n3 = new Vertex(20);
        Vertex n4 = new Vertex(42);

        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);

        g.connect(1,2,10);
        g.connect(10,15,3);
        g.connect(15,42,10);
        g.connect(42,15,20);
        g.connect(42,20,15);
        EdgeData edge = g.getEdge(1,3);

        assertEquals(null, edge);

        int edgeSize =  g.edgeSize();

        assertEquals(4, edgeSize);
        edge = g.getEdge(10,15);
        assertEquals(3, edge.getWeight());
        edge = g.getEdge(15,20);
        assertEquals(null, edge);

        g.connect(10,15,22);

        edge = g.getEdge(10,15);
        assertEquals(22, edge.getWeight());

        edge = g.getEdge(15,42);
        assertEquals(10, edge.getWeight());

        edge = g.getEdge(42,15);
        assertEquals(20, edge.getWeight());
    }

    @Test
    void nodeIter() {
        DirectedWeightedGraph g = new DWG();

        Vertex n1 = new Vertex(10);
        Vertex n2 = new Vertex(15);
        Vertex n3 = new Vertex(20);
        Vertex n4 = new Vertex(42);

        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);

        g.connect(1,2,10);
        g.connect(10,15,3);
        g.connect(15,42,10);
        g.connect(42,15,20);
        g.connect(42,20,15);
        Iterator <NodeData> k = g.nodeIter();
        for (Iterator <NodeData> it = k; it.hasNext(); ) {
            NodeData key = it.next();
            assertNotNull(key);
        }
    }

    @Test
    void edgeIter() {
        DirectedWeightedGraph g = new DWG();

        Vertex n1 = new Vertex(10);
        Vertex n2 = new Vertex(15);
        Vertex n3 = new Vertex(20);
        Vertex n4 = new Vertex(42);

        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);

        g.connect(1,2,10);
        g.connect(10,15,3);
        g.connect(15,42,10);
        g.connect(42,15,20);
        g.connect(42,20,15);

        Iterator <EdgeData> k = g.edgeIter(15);
        for (Iterator<EdgeData> it = k; it.hasNext(); ) {
            EdgeData key = it.next();
            assertNotNull(key);
        }
    }

    @Test
    void connect() {
        DirectedWeightedGraph g = new DWG();

        Vertex n1 = new Vertex(10);
        Vertex n2 = new Vertex(15);
        Vertex n3 = new Vertex(20);
        Vertex n4 = new Vertex(42);

        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);

        g.connect(1,2,10);
        g.connect(10,15,3);
        g.connect(15,42,10);
        g.connect(42,15,20);
        g.connect(42,20,15);

        EdgeData edge = g.getEdge(1,2);
        assertNull(edge);

        edge = g.getEdge(10,15);
        assertNotNull(edge);
        g.removeEdge(10,15);

        edge = g.getEdge(10,15);
        assertNull(edge);
    }


    @Test
    void removeNode() {
        DirectedWeightedGraph g = new DWG();

        Vertex n1 = new Vertex(10);
        Vertex n2 = new Vertex(15);
        Vertex n3 = new Vertex(20);
        Vertex n4 = new Vertex(42);

        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);

        g.connect(10,15,3);
        g.connect(15,42,10);
        g.connect(42,15,20);
        g.connect(42,20,15);

        g.removeNode(15);

        EdgeData edge = g.getEdge(10,15);
        assertNull(edge);
        edge = g.getEdge(42,15);
        assertNull(edge);
        edge = g.getEdge(15,42);
        assertNull(edge);

        int edgesize = g.edgeSize();

        assertEquals(1,edgesize);
    }

    @Test
    void removeEdge() {
        DirectedWeightedGraph g = new DWG();

        Vertex n1 = new Vertex(10);
        Vertex n2 = new Vertex(15);
        Vertex n3 = new Vertex(20);
        Vertex n4 = new Vertex(42);

        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);

        g.connect(10,15,3);
        g.connect(15,42,10);
        g.connect(42,15,20);
        g.connect(42,20,15);
        EdgeData edge = g.getEdge(10,15);
        assertEquals(edge.getWeight(),3);
        assertNotNull(edge);

        edge = g.removeEdge(1,3);
        assertNull(edge);

        edge = g.removeEdge(10,15);
        assertNotNull(edge);

        edge = g.getEdge(10,15);
        assertNull(edge);
    }
}
