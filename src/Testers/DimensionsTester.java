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

public class DimensionsTester {
    @Test
    void x() {
        Dimensions g = new Dimensions(2,2,2);
        Dimensions gs = new Dimensions("2,2,2");
        assertEquals(2,gs.x());
        assertEquals(2,g.x());
    }

    @Test
    void y() {
        Dimensions g = new Dimensions(2,2,2);
        Dimensions gs = new Dimensions("2,2,2");
        assertEquals(2,gs.y());
        assertEquals(2,g.y());
    }

    @Test
    void z() {
        Dimensions g = new Dimensions(2,2,2);
        Dimensions gs = new Dimensions("2,2,2");
        assertEquals(2,gs.z());
        assertEquals(2,g.z());
    }

    @Test
    void distance() {
        Dimensions g1 = new Dimensions(2,2,2);
        Dimensions g2 = new Dimensions(2,2,4);
        Dimensions gs1 = new Dimensions("2,2,2");
        Dimensions gs2 = new Dimensions("2,2,4");
        assertEquals(2,gs1.distance(gs2));
        assertEquals(2,g1.distance(g2));
    }
}
