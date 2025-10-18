import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

class MyScanner {
    private final Reader in;
    private final char[] buf;

    private StringBuilder tokenBuf = new StringBuilder();
    private StringBuilder sepBuf = new StringBuilder();

    private static final String lineSep = System.lineSeparator();
    private static final int BUFFER_SIZE = 8192;

    private int pos = 0;
    private int len = 0;
    private boolean eof = false;
    private boolean isClosed = false;

    private static record TokenSpec(
        Predicate<Character> token,
        Predicate<Character> prefix,
        Predicate<Character> suffix
    ) {}

    private static final TokenSpec INT_SPEC = new TokenSpec(
        c -> c != null && Character.isDigit(c),
        c -> c != null && (c == '-' || c == '+'),
        c -> c != null && (c == 'o' || c == 'O')
    );

    private static final TokenSpec WORD_SPEC = new TokenSpec(
        c -> c != null && (Character.isLetter(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION),
        c -> false,
        c -> false
    );

    public MyScanner(InputStream inputStream, Charset charset) {
        this.in = new BufferedReader(new InputStreamReader(inputStream, charset));
        this.buf = new char[BUFFER_SIZE];
    }

    public MyScanner(String str) {
        this.in = new BufferedReader(new StringReader(str));
        this.buf = new char[BUFFER_SIZE];
    }

    public boolean hasNext(Predicate<Character> partOfToken) throws IOException {
        checkClosed();
        return hasNext(new TokenSpec(partOfToken, c -> false, c -> false));
    }

    public boolean hasNextInt() throws IOException {
        checkClosed();
        return hasNext(INT_SPEC);
    }

    public boolean hasNextWord() throws IOException {
        checkClosed();
        return hasNext(WORD_SPEC);
    }

    public boolean hasNextLine() throws IOException {
        checkClosed();
        sepBuf.setLength(0);
        tokenBuf.setLength(0);

        Character character = peek();

        while (!eof && sepBuf.length() < lineSep.length()) {
            if (character.equals(lineSep.charAt(sepBuf.length()))) {
                sepBuf.append(character);
            } else {
                tokenBuf.append(character);
                sepBuf.setLength(0);
            }
            character = readCharacter();
        }
        return !(eof && sepBuf.length() == 0);
    }

    public String next(Predicate<Character> partOfToken) throws IOException {
        checkClosed();
        return next(new TokenSpec(partOfToken, c -> false, c -> false));
    }

    public int nextInt() throws IOException {
        checkClosed();

        char suffix = tokenBuf.charAt(tokenBuf.length() - 1);
        if (suffix == 'o' || suffix == 'O') {
            tokenBuf.setLength(tokenBuf.length() - 1);
            return Integer.parseUnsignedInt(next(INT_SPEC), 8);
        }
        return Integer.parseInt(next(INT_SPEC), 10);
    }

    public String nextWord() throws IOException {
        checkClosed();
        return next(WORD_SPEC);
    }

    public String nextLine() throws IOException {
        return tokenBuf.toString();
    }

    public void close() throws IOException {
        checkClosed();
        try {
            if (in != null) {
                in.close();
                isClosed = true;
            }
        } catch (IOException e) {
            System.err.println("Failed to close reader");
        }
    }

    private boolean hasNext(TokenSpec spec) throws IOException {
        tokenBuf.setLength(0);

        Character character = peek();

        // Find expected token
        while (!eof && tokenBuf.length() == 0 && !spec.token().test(character)) {
            if (spec.prefix().test(character)) {
                if (tokenBuf.length() == 0) {
                    tokenBuf.append(character);
                } else {
                    tokenBuf.setCharAt(0, character);
                }
            } else if (tokenBuf.length() > 0) {
                tokenBuf.setLength(0);
            }
            character = readCharacter();
        }
        // Consume expected token
        while (!eof && spec.token().test(character)) {
            tokenBuf.append(character);
            character = readCharacter();
        }
        // Consume token suffix
        if (!eof && spec.suffix().test(character)) {
            tokenBuf.append(character);
            character = readCharacter();
        }
        return !(eof && tokenBuf.length() == 0);
    }

    private String next(TokenSpec spec) throws IOException {
        checkClosed();
        if (tokenBuf.length() == 0 && !hasNext(spec)) {
            throw new NoSuchElementException("Requested element does not exist");
        }
        return tokenBuf.toString();
    }

    private void checkClosed() {
        if (isClosed) {
            throw new IllegalStateException();
        }
    }

    private Character readCharacter() throws IOException {
        read();
        return peek();
    }

    private void update() throws IOException {
        if (eof) return;
        len = in.read(buf, 0, buf.length);
        pos = 0;
        if (len == -1) {
            eof = true;
            len = 0;
        }
    }

    private Character read() throws IOException {
        if (pos >= len) {
            update();
            if (eof) return null;
        }
        return buf[pos++];
    }

    private Character peek() throws IOException {
        if (pos >= len) {
            update();
            if (eof) return null;
        }
        return buf[pos];
    }
}


