package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.w3c.dom.Node;

import java.util.*;

public class DWG implements DirectedWeightedGraph {
    private HashMap <Integer, NodeData> Nodes;
    private HashMap <Integer, HashMap<Integer, EdgeData>> Edges;
    private int NumberOfEdges;
    private int MC;

    public DWG() {
        Nodes = new HashMap <Integer, NodeData> ();
        Edges = new HashMap <Integer, HashMap <Integer, EdgeData>> ();
        this.NumberOfEdges = 0;
        this.MC = 0;
    }

    @Override
    public NodeData getNode(int key) {
        if (this.Nodes.get(key) != null)
            return this.Nodes.get(key);
        else
            return null;
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        if (this.Nodes.containsKey(src) || this.Nodes.containsKey(dest)) {
            if (this.Edges.get(src) != null && src != dest)
                return this.Edges.get(src).get(dest);
        }
        return null;
    }

    @Override
    public void addNode(NodeData n) {
        if (!(this.Nodes.containsKey(n.getKey()))) {
            this.Nodes.put(n.getKey(), n);
            this.Edges.put(n.getKey(), new HashMap <Integer, EdgeData> ());
            this.MC++;
        }
    }

    @Override
    public void connect(int src, int dest, double w) {
        EdgeData e = new Edge(src, w, dest);

        if (!(!this.Nodes.containsKey(src) || !this.Nodes.containsKey(dest) || src == dest)) {
            if (this.Edges.get(src).containsKey(dest)) {
                this.Edges.get(src).remove(dest);
                this.Edges.get(src).put(dest, e);
            }
            else {
                this.Edges.get(src).put(dest, e);
                this.MC++;
                this.NumberOfEdges++;
            }
        }
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        Collection <NodeData> VS = this.Nodes.values();
        Iterator <NodeData> It = VS.iterator();
        return It;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        ArrayList <EdgeData> ES = new ArrayList <EdgeData> ();
        for (int i = 0; i < Edges.size(); i++) {
            ES.add(Edges.get(i).get(i));
        }
        Iterator <EdgeData> It = ES.iterator();
        return It;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        NodeData v = getNode(node_id);

        if (v == null || this.Edges.get(node_id) == null)
            return null;
        else {
            Collection <EdgeData> ES = this.Edges.get(node_id).values();
            Iterator <EdgeData> It = ES.iterator();
            return It;
        }
    }

    @Override
    public NodeData removeNode(int key) {
        int edgeCount = 0;
        NodeData v = getNode(key);

        if (this.Edges.get(key) != null) {
            edgeCount = this.Edges.get(key).values().size();
            this.NumberOfEdges -= edgeCount; // Update Edge Size
            this.Edges.remove(key);
        }

        Iterator It = this.Edges.entrySet().iterator();

        while (It.hasNext()) {
            Map.Entry pair = (Map.Entry)It.next();
            if (this.Edges.get(pair.getKey()).containsKey(key))
                removeEdge((int) pair.getKey(), key);
        }

        this.Nodes.remove(key);
        return v;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        EdgeData e = getEdge(src, dest);

        if (!(!this.Nodes.containsKey(src) || !this.Nodes.containsKey(dest))) {
            if (e != null) {
                this.Edges.get(src).remove(dest);
                this.NumberOfEdges--;
                this.MC++;
            }
        }
        return e;
    }

    @Override
    public int nodeSize() {
        return this.Nodes.size();
    }

    @Override
    public int edgeSize() {
        return this.NumberOfEdges;
    }

    @Override
    public int getMC() {
        return this.MC;
    }
}
