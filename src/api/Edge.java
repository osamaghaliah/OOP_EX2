package api;

public class Edge implements EdgeData {
    private int src;
    private int dest;
    private double w;
    private String info;
    private int tag;

    public Edge() {
        this.src = 0;
        this.dest = 0;
        this.w = 0.0;
        this.info = "";
        this.tag = 0;
    }

    public Edge(int src, double w, int dest) {
        if (w >= 0) {
            this.src = src;
            this.dest = dest;
            this.w = w;
            this.info = "";
            this.tag = 0;
        }
    }

    public Edge(Edge e) {
        this.src = e.src;
        this.dest = e.dest;
        this.w = e.w;
        this.info = e.info;
        this.tag = e.tag;
    }

    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }
}
