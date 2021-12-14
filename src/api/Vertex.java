package api;

public class Vertex implements NodeData {
    private int key;
    private GeoLocation location;
    private double weight;
    private int tag;
    private String info;

    public Vertex(int key) {
        this.key = key;
        this.weight = 0;
        this.tag = 0;
        this.info = "";
    }

    public Vertex(int key, double weight, int tag, String info) {
        this.key = key;
        this.weight = weight;
        this.tag = tag;
        this.info = info;
    }

    public Vertex(Vertex v) {
        this.key = v.key;
        this.weight = v.weight;
        this.tag = v.tag;
        this.info = v.info;
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public GeoLocation getLocation() {

        return this.location;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.location = p;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight=w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info=s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag=t;
    }
}
