package Testers;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import api.DWGAlgo;
import api.DWG;
import api.NodeData;
import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.*;

public class VertexTester {
    @Test
    void getKey() {
        Vertex n = new Vertex(1,2,3,"new");
        assertEquals(1,n.getKey());
    }

    @Test
    void getLocation() {
        Vertex n = new Vertex(1,2,3,"new");
        Dimensions g = new Dimensions(2,2,2);
        n.setLocation(g);
        assertEquals(g,n.getLocation());

    }

    @Test
    void setLocation() {
        Vertex n = new Vertex(1,2,3,"new");
        Dimensions g = new Dimensions(3,5,7);
        n.setLocation(g);
        assertEquals(g,n.getLocation());
    }

    @Test
    void getWeight() {
        Vertex n = new Vertex(1,2,3,"new");
        assertEquals(2,n.getWeight());
    }

    @Test
    void setWeight() {
        Vertex n = new Vertex(1,2,3,"new");
        n.setWeight(4);
        assertEquals(4,n.getWeight());
    }

    @Test
    void getInfo() {
        Vertex n = new Vertex(1,2,3,"new");
        assertEquals("new",n.getInfo());
    }

    @Test
    void setInfo() {
        Vertex n = new Vertex(1,2,3,"new");
        n.setInfo("newnew");
        assertEquals("newnew",n.getInfo());
    }

    @Test
    void getTag() {
        Vertex n = new Vertex(1,2,3,"new");
        assertEquals(3,n.getTag());
    }

    @Test
    void setTag() {
        Vertex n = new Vertex(1,2,3,"new");
        n.setTag(6);
        assertEquals(6,n.getTag());
    }
}
