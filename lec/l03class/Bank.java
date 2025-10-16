public class Bank {
    public static void main(String[] args) {
        // Client c = new Client("vsndrg", null);
        //
        // System.out.println(c.getName());
        // System.out.println(c.checkPassport("1234567890"));
        // System.out.println(c.checkPassport("103498t"));
        // System.out.println(c.checkPassport(""));
        // System.out.println(c.checkPassport(null));
        // System.out.println(c.checkPassport("null"));

        Client[] cs = {
            new Client("nigga", null),
            new Client("nigga1", "1"),
            new Client("nigga2", "2"),
            new Client("nigga3", "3"),
            new Client("nigga4", "4")
        };

        StringBuilder names = new StringBuilder();

        for (Client c : cs) {
            if (!names.isEmpty()) {
                names.append(", ");
            }
            names.append(c.getName());
        }
        // String namesStr = names.toString();
        System.out.println(names);
    }
}
