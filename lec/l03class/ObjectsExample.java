public class ObjectsExample {
    public static void main(String[] args) {
        // PolVector v = new PolVector(10, 20);
        // PolVector v2 = new PolVector(100, 200);
        // v.x = 0;
        // System.out.println(v.getX() + " " + v.getY() + " " + v.getRho() + " " + v.getPhi());
        // System.out.println(v2.getX() + " " + v2.getY() + " " + v2.getRho() + " " + v2.getPhi());
        // System.out.println(v.toString());
        // System.out.println(v2.toString());

        MutVector v = MutVector.cartesian(30, 17);
        MutCircle c = new MutCircle(v, 3);

        System.out.println(c);
        c.translate(100, 200);
        System.out.println(c);
    }
}
