package api;

public class Dimensions implements GeoLocation {
    private double x;
    private double y;
    private double z;

    public Dimensions(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Dimensions(String pos) {
        String [] a = pos.split(",");
        this.x = Double.parseDouble(a[0]);
        this.y = Double.parseDouble(a[1]);
        this.z = Double.parseDouble(a[2]);
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(GeoLocation g) {
        return Math.sqrt(Math.pow(x - g.x(), 2) + Math.pow(y - g.y(), 2) + Math.pow(z - g.z(), 2));
    }
}
