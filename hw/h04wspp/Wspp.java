import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class Wspp {
    private static void updateMap(LinkedHashMap<String, IntList> map, String key, int value) {
        IntList values = map.getOrDefault(key, new IntList());
        values.append(value);
        map.put(key, values);
    }

    public static void main(String[] args) {
        boolean readSuccess = false;
        LinkedHashMap<String, IntList> wordMap = new LinkedHashMap<>();

        try (FileInputStream fin = new FileInputStream(args[0])) {
            MyScanner scanner = new MyScanner(fin, StandardCharsets.UTF_8, 8192);

            int index = 1;
            while (scanner.hasNextWord()) {
                updateMap(wordMap, scanner.nextWord().toLowerCase(), index++);
            }

            readSuccess = true;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + args[0] + ": " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Failed to read from file " + args[0] + ": " + e.getMessage());
        }

        if (!readSuccess) {
            return;
        }

        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
            for (Map.Entry<String, IntList> entry : wordMap.entrySet()) {
                IntList values = entry.getValue();
                out.write(entry.getKey() + " " + values.length + " " + values.toString());
                out.newLine();
            }
        } catch (IOException e) {
            System.err.println("Failed to write in file " + args[1] + ": " + e.getMessage());
        }
    }
}

