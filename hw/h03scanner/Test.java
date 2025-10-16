import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Test {
    public static void main(String[] args) throws IOException {
        int bufferSize = 1 << 14;
        MyScanner scanner = new MyScanner(System.in, StandardCharsets.UTF_8, 8192);
        while (scanner.hasNextInt()) {
            System.out.println(scanner.nextInt());
        }
        // while (scanner.hasNextWord()) {
        //     System.out.println(scanner.nextWord());
        // }
    }
}

