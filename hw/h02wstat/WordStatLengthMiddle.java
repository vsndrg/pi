import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordStatLengthMiddle {
    private static boolean isPartOfWord(char character) {
        return Character.isLetter(character) || character == '\'' || Character.getType(character) == Character.DASH_PUNCTUATION;
    }

    private static void processWord(LinkedHashMap<String, Integer> map, StringBuilder wordBuf) {
        int len = wordBuf.length();
        if (len < 7) {
            wordBuf.setLength(0);
            return;
        }
        if (len > 0) {
            String word = wordBuf.substring(3, len - 3);
            map.put(word, map.getOrDefault(word, 0) + 1);
            wordBuf.setLength(0);
        }
    }

    public static void main(String[] args) {
        int bufSize = 4096;
        boolean readSuccess = false;
        LinkedHashMap<String, Integer> wordMap = new LinkedHashMap<>();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8), bufSize)) {
            char[] characters = new char[bufSize];
            StringBuilder wordBuf = new StringBuilder();
            int readLen;

            while ((readLen = in.read(characters)) != -1) {
                for (int i = 0; i < readLen; i++) {
                    char character = characters[i];
                    if (isPartOfWord(character)) {
                        wordBuf.append(Character.toLowerCase(character));
                    } else {
                        processWord(wordMap, wordBuf);
                    }
                }
            }
            processWord(wordMap, wordBuf);
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
