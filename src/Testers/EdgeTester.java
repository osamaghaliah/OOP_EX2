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

public class EdgeTester {
    @Test
    void getSrc() {
        Edge e = new Edge(1,3,4);
        assertEquals(1,e.getSrc());
    }

    @Test
    void getDest() {
        Edge e = new Edge(1,3,4);
        assertEquals(4,e.getDest());
    }

    @Test
    void getWeight() {
        Edge e = new Edge(1,3,4);
        assertEquals(3,e.getWeight());
    }

    @Test
    void getInfo() {
        Edge e = new Edge(1,3,4);
        assertEquals("",e.getInfo());
    }

    @Test
    void setInfo() {
        Edge e = new Edge(1,3,4);
        e.setInfo("eee");
        assertEquals("eee",e.getInfo());
    }

    @Test
    void getTag() {
        Edge e = new Edge(1,3,4);
        assertEquals(0,e.getTag());
    }

    @Test
    void setTag() {
        Edge e = new Edge(1,3,4);
        e.setTag(1122);
        assertEquals(1122,e.getTag());
    }
}
