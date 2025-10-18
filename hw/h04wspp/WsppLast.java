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
import java.util.function.Predicate;

public class WsppLast {
    private static class WordData {
        private int totalNumOfOccurences = 0;
        private int lastLineIndex = 0;
        private IntList occurences = new IntList();

        public void update(int wordIndex, int lineIndex) {
            if (occurences.length == 0 || lineIndex != lastLineIndex) {
                occurences.append(wordIndex);
                lastLineIndex = lineIndex;
            } else {
                occurences.set(occurences.length - 1, wordIndex);
            }
            totalNumOfOccurences++;
        }

        @Override
        public String toString() {
            return Integer.toString(totalNumOfOccurences) + " " + occurences.toString();
        }
    }

    private static int updateMap(LinkedHashMap<String, WordData> map, String line, int wordIndex, int lineIndex) throws IOException {
        MyScanner scanner = new MyScanner(line);
        Predicate<Character> partOfWord =
            c -> c != null && (Character.isLetter(c) || Character.isDigit(c) || c == '\'' ||
            Character.getType(c) == Character.DASH_PUNCTUATION || c == '$' || c == '_');

        while (scanner.hasNext(partOfWord)) {
            String word = scanner.next(partOfWord);
            WordData occurences = map.computeIfAbsent(word, k -> new WordData());
            occurences.update(wordIndex, lineIndex);
            wordIndex++;
        }
        return wordIndex;
    }

    public static void main(String[] args) {
        boolean readSuccess = false;
        LinkedHashMap<String, WordData> wordMap = new LinkedHashMap<>();

        try (FileInputStream fin = new FileInputStream(args[0])) {
            MyScanner scanner = new MyScanner(fin, StandardCharsets.UTF_8);
            int lineIndex = 0;
            int wordIndex = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                wordIndex = updateMap(wordMap, line.toLowerCase(), wordIndex, lineIndex++);
            }
            scanner.close();
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
            ArrayList<Map.Entry<String, WordData>> wordList = new ArrayList<>(wordMap.entrySet());
            wordList.sort(Comparator.comparingInt(e -> e.getKey().length()));

            for (Map.Entry<String, WordData> entry : wordList) {
                out.write(entry.getKey() + " " + entry.getValue());
                out.newLine();
            }
        } catch (IOException e) {
            System.err.println("Failed to write in file " + args[1] + ": " + e.getMessage());
        }
    }
}

