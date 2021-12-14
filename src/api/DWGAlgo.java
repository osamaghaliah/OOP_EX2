package api;

import org.w3c.dom.Node;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DWGAlgo implements DirectedWeightedGraphAlgorithms {
    private DirectedWeightedGraph Graph;

    public DWGAlgo() {
        this.Graph = new DWG();
    }

    @Override
    public void init(DirectedWeightedGraph g) {
        this.Graph = g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.Graph;
    }

    public static <T> Collection <T> IteratorToCollection(Iterator <T> itr) {
        Collection <T> DS = new ArrayList <T> ();

        for (Iterator<T> it = itr; it.hasNext();) {
            T t = it.next();
            DS.add(t);
        }

        return DS;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph GraphV2 = new DWG();
        Iterator <NodeData> It1 = this.Graph.nodeIter();
        Iterator <NodeData> It2 = GraphV2.nodeIter();
        Collection <NodeData> vs1 = IteratorToCollection(It1);
        Collection <NodeData> vs2 = IteratorToCollection(It2);


        for (NodeData key : vs1) {
            NodeData k = new Vertex(key.getKey());
            GraphV2.addNode(k);
        }

        for (NodeData key : vs2) {
            Iterator <EdgeData> It3 = this.Graph.edgeIter(key.getKey());
            Collection <EdgeData> es = IteratorToCollection(It3);
            for (EdgeData k : es) {
                GraphV2.connect(key.getKey(), k.getDest(), k.getWeight());
            }
        }

        return GraphV2;
    }

    public DirectedWeightedGraph getTranspose()
    {
        DirectedWeightedGraph TransposedGraph = new DWG();
        Iterator <NodeData> It1 = this.Graph.nodeIter();
        Collection <NodeData> vs1 = IteratorToCollection(It1);

        for (NodeData v : vs1) {
            TransposedGraph.addNode(v);
        }

        for (NodeData v : vs1) {
            Iterator <EdgeData> eI = this.Graph.edgeIter(v.getKey());
            while (eI.hasNext()) {
                EdgeData e = new Edge();
                e = eI.next();
                TransposedGraph.connect(e.getDest(), v.getKey(), e.getWeight());
            }
        }
        return TransposedGraph;
    }

    public void DFS(int v , DirectedWeightedGraph g)
    {
        String yes = "Visited" , no = "NotVisited";
        g.getNode(v).setInfo(yes);
        EdgeData e;
        Iterator <EdgeData> edgeIt = g.edgeIter(v);

        while (edgeIt.hasNext())
        {
            e = edgeIt.next();
            if (g.getNode(e.getDest()).getInfo().equals(no))
                DFS(e.getDest(), g);
        }
    }

    @Override
    public boolean isConnected() {
        String yes = "Visited" , no = "NotVisited";
        Iterator <NodeData> It1 = this.Graph.nodeIter();
        Collection <NodeData> vs1 = IteratorToCollection(It1);
        
        for (NodeData v : vs1) {
            v.setInfo(no);
        }

        Iterator<NodeData> it = this.Graph.nodeIter();
        NodeData firstVertex = it.next();
        DFS(firstVertex.getKey(), this.Graph);

        for(NodeData v : vs1) {
            if(v.getInfo().equals(no))
                return false;
        }

        DirectedWeightedGraph TransposedGraph = getTranspose();
        Iterator <NodeData> It2 = TransposedGraph.nodeIter();
        Collection <NodeData> vs2 = IteratorToCollection(It2);

        for(NodeData v: vs2) {
            v.setInfo(no);
        }

        DFS(firstVertex.getKey(), TransposedGraph);

        for (NodeData v : vs2) {
            if(v.getInfo().equals(no))
                return false;
        }
        return true;
    }

    private int MaxValue(DirectedWeightedGraph g) {
        int max = 0;
        Iterator <NodeData> It = g.nodeIter();
        Collection <NodeData> vs = IteratorToCollection(It);

        for (NodeData v : vs) {
            if (v.getKey() > max)
                max = v.getKey();
        }
        return max;
    }

    private int getIndex(double dist[], LinkedList<NodeData> list) {
        int index = 0, i;
        double min = Integer.MAX_VALUE;

        for(i = 0; i < dist.length; i++) {
            if(dist[i] < min && list.contains(this.Graph.getNode(i))) {
                min = dist[i];
                index = i;
            }
        }
        return index;
    }

    private boolean BFSV1(DirectedWeightedGraph g, int src, int dest, int v, double prev[], double dist[])
    {
        int i = 0;
        double alt = 0;
        LinkedList <NodeData> myList = new LinkedList <NodeData> ();

        for (i = 0; i < v; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }

        Iterator <NodeData> It = g.nodeIter();
        Collection <NodeData> vs = IteratorToCollection(It);

        for (NodeData ver : vs) {
            myList.add(ver);
        }

        dist[src] = 0;

        while (!myList.isEmpty()) {
            int index = getIndex(dist,myList);
            NodeData myV = this.Graph.getNode(index);
            myList.remove(myV);
            Iterator <EdgeData> It3 = g.edgeIter(myV.getKey());
            Collection <EdgeData> es = IteratorToCollection(It3);
            for (EdgeData e : es) {
                if (myList.contains(g.getNode(e.getDest()))) {
                    alt = dist[index] + e.getWeight();
                    if (alt < dist[e.getDest()]) {
                        dist[e.getDest()] = alt;
                        prev[e.getDest()] =  myV.getKey();
                    }
                }
            }
        }
        return true;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        int max = MaxValue(this.Graph);

        if (this.Graph.getNode(src) == null || this.Graph.getNode(dest) == null)
            return -1;

        double prev[] = new double [max + 1];
        double dist[] = new double [max + 1];

        if (BFSV1(this.Graph, src, dest, max + 1, prev, dist) == false)
            return -1;

        return dist[dest];
    }

    private List<NodeData> BFSV2(DirectedWeightedGraph g, int src, int dest, int v, double prev[], double dist[])
    {
        int i = 0;
        double alt = 0;
        LinkedList <NodeData> myList = new LinkedList <NodeData> ();
        List <NodeData> ListOfVertices = new LinkedList ();
        NodeData [] ArrayOfVertices = new NodeData[v];

        for(i = 0; i < v; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }

        Iterator <NodeData> It = g.nodeIter();
        Collection <NodeData> vs = IteratorToCollection(It);

        for(NodeData ver : vs) {
            myList.add(ver);
        }

        dist[src] = 0;

        while (!myList.isEmpty()) {
            int index = getIndex(dist,myList);
            NodeData myV = this.Graph.getNode(index);
            myList.remove(myV);
            Iterator <EdgeData> It3 = g.edgeIter(myV.getKey());
            Collection <EdgeData> es = IteratorToCollection(It3);
            for (EdgeData e : es) {
                if (myList.contains(g.getNode(e.getDest()))) {
                    alt = dist[index] + e.getWeight();
                    if (alt < dist[e.getDest()]) {
                        dist[e.getDest()] = alt;
                        prev[e.getDest()] =  myV.getKey();
                    }
                }
            }
        }

        i = 0;
        NodeData u = this.Graph.getNode(dest);

        if (prev[u.getKey()] != -1 || u.getKey()==src)
            while(u != null) {
                ArrayOfVertices[i++] = u;
                u = this.Graph.getNode((int)prev[u.getKey()]);
            }

        if (i == 1)
            return null;

        while ( --i >= 0)
            ListOfVertices.add(ArrayOfVertices[i]);

        return ListOfVertices;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        int max = MaxValue(this.Graph);

        if(this.Graph.getNode(src) == null || this.Graph.getNode(dest) == null)
            return null;

        double prev[] = new double[max + 1];
        double dist[] = new double[max + 1];

        List<NodeData> myList = BFSV2(this.Graph, src, dest, max + 1, prev, dist);

        return myList;
    }

    @Override
    public NodeData center() {
        //loops through all vertices and sums distances to all other vertices, vertex with min sum is center
        if (!isConnected()){
            return null;
        }
        Iterator <NodeData> It1 = this.Graph.nodeIter();
        Collection <NodeData> vs1 = IteratorToCollection(It1);
        int count =0;
        NodeData cntr = null, tmp = null;
        double min = 100000; //so first vertex will be min
        for (NodeData v : vs1) {
            for (NodeData vx : vs1) {
                if(v.getKey() != vx.getKey()){
                    count += shortestPathDist(v.getKey(), vx.getKey());
                    tmp = vx;
                }
                if(count < min){
                    min = count;
                    cntr = tmp;
                }
            }
            min = 100000;
            count = 0;
        }
        return cntr;
    }

    public boolean inColors(int k, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == k) {
                return true;
            }
        }
        return  false;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        List <NodeData> result = new LinkedList <NodeData> () ;
        int [] colors = new int [cities.size()];
        int k = 1;
        NodeData tmp = center();
        colors[0] = tmp.getKey();
        result.add(this.Graph.getNode(colors[0]));
        double min = 100000;
        int minKey = colors[0];
        for (int j = 0; j < cities.size(); j++) {
            for (int i = 0; i < cities.size(); i++) {
                if (!inColors(cities.get(i).getKey(), colors)) {
                    double shortest = shortestPathDist(minKey, cities.get(i).getKey());
                    if (shortest < min) {
                        min = shortest;
                        minKey = cities.get(i).getKey();
                        colors[k] = minKey;
                        k++;
                    }
                }
            }
            result.add(cities.get(minKey));
            minKey = cities.get(j).getKey();
        }
        return result;

    }

    public boolean equalsGraph(DirectedWeightedGraph g)
    {
        boolean flag; // to tell if i found a specific node into the other graph
        //check size if not equals there is no need to go to loop
        if (g.nodeSize() != this.Graph.nodeSize())
            return false;
        //loop to get over all the nodes
        Iterator <NodeData> It1 = g.nodeIter();
        Collection <NodeData> vs1 = IteratorToCollection(It1);
        Iterator <NodeData> It2 = this.Graph.nodeIter();
        Collection <NodeData> vs2 = IteratorToCollection(It2);
        for (NodeData myVer1: vs1) {
            flag = false;
            for(NodeData myVer2: vs2) {
                if(myVer1.getKey() == myVer2.getKey())
                    flag=true;
            } // check if the graph contain it1
            if (flag == false)
                return false;
        }
        return true;
    }

    @Override
    public boolean save(String file) {
        //Make JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this.Graph);

        //Write JSON to file
        try {
            PrintWriter pw = new PrintWriter(new File(file));
            pw.write(json);
            pw.close();
            return true;
        }

        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean load(String file) {
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(DWG.class, new DeserializerV1());
            Gson gson = builder.create();

            FileReader reader = new FileReader(file);
            DWG graph = gson.fromJson(reader, DWG.class);
            init(graph);
            return true;
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
