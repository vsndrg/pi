
// public - общедоступный
// private - закрытый

public class Mut {
    private final double x;
    private final double y;

    public Mut(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public Mut() {
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

    public Mut setX(double x) {
        return new Mut(x, y);
    }

    public Mut setY(double y) {
        return new Mut(x, y);
    }

    public static Mut cartesian(double x, double y) {
        return new Mut(x, y);
    }

    public static Mut polar(double rho, double phi) {
        return new Mut(rho * Math.cos(rho), phi * Math.sin(phi));
    }

    @Override
    public String toString() {
        return "("  + x + ", " + y + ")";
    }
}

