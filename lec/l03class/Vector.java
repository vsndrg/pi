// public - общедоступный
// private - закрытый

public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector() {
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

    public static Vector cartesian(double x, double y) {
        return new Vector(x, y);
    }

    public static Vector polar(double rho, double phi) {
        return new Vector(rho * Math.cos(rho), phi * Math.sin(phi));
    }

    @Override
    public String toString() {
        return "("  + x + ", " + y + ")";
    }
}

