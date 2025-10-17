import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner("""
            1 2 3
            4 5
            6
        """, 8192);
        // Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            System.out.println(scanner.nextInt());
        }
        scanner.close();
        // while (scanner.hasNextWord()) {
        //     System.out.println(scanner.nextWord());
        // }
    }
}

