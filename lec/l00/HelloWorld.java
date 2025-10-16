public class HelloWorld {
    static String capitalizeFirst(String name) {
        if (name.isEmpty()) {
            return "";
        }
        char first = Character.toUpperCase(name.charAt(0));
        String rest = name.substring(1);
        return first + rest;
    }

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println("Hello, " + capitalizeFirst(args[i]) + "!");
        }
        // String name;
        // if (args.length > 0) {
        //     name = args[0];
        // } else {
        //     name = "world";
        // }
        // System.out.println("Hello, " + capitalizeFirst(name) + "!");
    }
}
