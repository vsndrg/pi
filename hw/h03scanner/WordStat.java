import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordStat {
    private static boolean isPartOfWord(char ch) {
        return Character.isLetter(ch) || ch == '\'' || Character.getType(ch) == Character.DASH_PUNCTUATION;
    }

    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(args[0]),
                "utf8"
            ), 4096);
            try {
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter( // Open file for write after read all
                    new FileOutputStream(args[1]),
                    "utf8"
                ));
                try {
                    LinkedHashMap<String, Integer> wordsMap = new LinkedHashMap<>();

                    int bufSize = 4096;
                    char[] chars = new char[bufSize];
                    StringBuilder word = new StringBuilder();
                    int readLen;

                    while ((readLen = in.read(chars, 0, bufSize)) != -1) {
                        for (int i = 0; i < readLen; i++) {
                            char c = chars[i];
                            if (isPartOfWord(c)) {
                                word.append(Character.toLowerCase(c));
                            } else {
                                if (word.length() > 0) {
                                    String w = word.toString();
                                    wordsMap.put(w, wordsMap.getOrDefault(w, 0) + 1);
                                    word.setLength(0);
                                }
                            }
                        }
                    }
                    if (word.length() > 0) {
                        String w = word.toString();
                        wordsMap.put(w, wordsMap.getOrDefault(w, 0) + 1);
                        word.setLength(0);
                    }

                    for (Map.Entry<String, Integer> entry : wordsMap.entrySet()) {
                        out.write(entry.getKey() + " " + entry.getValue());
                        out.newLine();
                    }
                } finally {
                    out.close();
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            System.err.println("Input-output error: " + e.getMessage());
        }
    }
}

