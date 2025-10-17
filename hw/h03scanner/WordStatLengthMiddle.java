import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordStatLengthMiddle {

    private static void processWord(LinkedHashMap<String, Integer> map, String word) {
        int len = word.length();
        if (len < 7) {
            return;
        }
        if (len > 0) {
            String w = word.substring(3, len - 3);
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
    }

    public static void main(String[] args) {
        boolean readSuccess = false;
        LinkedHashMap<String, Integer> wordMap = new LinkedHashMap<>();

        try (FileInputStream fin = new FileInputStream(args[0])) {
            MyScanner scanner = new MyScanner(fin, StandardCharsets.UTF_8, 8192);

            while (scanner.hasNextWord()) {
                processWord(wordMap, scanner.nextWord().toLowerCase());
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
            ArrayList<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordMap.entrySet());
            wordList.sort(Comparator.comparingInt(e -> e.getKey().length()));

            for (Map.Entry<String, Integer> entry : wordList) {
                out.write(entry.getKey() + " " + entry.getValue());
                out.newLine();
            }
        } catch (IOException e) {
            System.err.println("Failed to write in file " + args[1] + ": " + e.getMessage());
        }
    }
}
