// public - общедоступный
// private - закрытый

public class PolVector {
    private double rho;
    private double phi;

    public PolVector(double rho, double phi) {
        this.rho = rho;
        this.phi = phi;
    }

    public PolVector() {
        this.rho = 0;
        this.phi = 0;
    }

    public double getX() {
        return rho * Math.cos(phi);
    }

    public double getY() {
        return phi * Math.sin(phi);
    }

    public void setX(double x) {
        double y = getY();
        rho = Math.hypot(x, y);
        phi = Math.atan2(y, x);
    }

    public void setY(double y) {
        double x = getX();
        rho = Math.hypot(x, y);
        phi = Math.atan2(y, x);
    }

    public void setRho(double rho) {
        this.rho = rho;
    }

    public void setPhi(double phi) {
        this.phi = phi;
    }

    public double getRho() {
        return Math.hypot(rho, phi);
    }

    public double getPhi() {
        return Math.atan2(phi, rho);
    }

    public static PolVector cartesian(double x, double y) {
        return new PolVector(Math.hypot(x, y), Math.atan2(y, x));
    }

    public static PolVector polar(double rho, double phi) {
        return new PolVector(rho, phi);
    }

    @Override
    public String toString() {
        return "("  + rho + ", " + phi + ")";
    }
}

