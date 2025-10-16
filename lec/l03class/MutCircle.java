public class MutCircle {
    private MutVector center;
    private double radius;

    public MutCircle(MutVector center, double radius) {
        this.center = new MutVector(center);
        this.radius = radius;
    }

    public MutVector getCenter() {
        return new MutVector(center);
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Circle(" + center + ", " + radius + ")";
    }

    public void translate(double dx, double dy) {
        center.setX(center.getX() + dx);
        center.setY(center.getY() + dy);
    }
}
