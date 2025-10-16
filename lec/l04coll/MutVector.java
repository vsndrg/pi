
// public - общедоступный
// private - закрытый

public class MutVector {
    private double x;
    private double y;

    public MutVector(MutVector v) {
        this.x = v.x;
        this.y = v.y;
    }

    public MutVector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public MutVector() {
        this.x = 0;
        this.y = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRho() {
        return Math.hypot(x, y);
    }

    public double getPhi() {
        return Math.atan2(y, x);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public static MutVector cartesian(double x, double y) {
        return new MutVector(x, y);
    }

    public static MutVector polar(double rho, double phi) {
        return new MutVector(rho * Math.cos(rho), phi * Math.sin(phi));
    }

    @Override
    public String toString() {
        return "("  + x + ", " + y + ")";
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof MutVector that) {
            return this.getX() == that.getX() && this.getY() == that.getY();
        }
        return false;
    }

    // Need to override hashCode if overrided equals
    @Override
    public int hashCode() {
        return Double.hashCode(getX()) * 23 + Double.hashCode(getY());
    }
}

