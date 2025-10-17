import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner("""
            1 2 3
            4 5
            6
        """, 8192);
        while (scanner.hasNextInt()) {
            System.out.println(scanner.nextInt());
        }
        scanner.close();
    }
}

