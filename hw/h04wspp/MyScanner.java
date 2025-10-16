import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.NoSuchElementException;

class MyScanner {
    private final Reader in;
    private final char[] buf;

    private StringBuilder tokenBuf = new StringBuilder();
    private StringBuilder sepBuf = new StringBuilder();

    private TokenType curTokenType;
    private int curRadix = 10;

    private int pos = 0;
    private int len = 0;
    private boolean eof = false;

    private enum TokenType {
        WORD, INT, LINE, SIGN, LINESEP, EOF, UNKNOWN;
    }

    public MyScanner(InputStream inputStream, Charset charset, int bufferSize) {
        this.in = new BufferedReader(new InputStreamReader(inputStream, charset));
        this.buf = new char[bufferSize];
    }

    public MyScanner(String str, int bufferSize) {
        this.in = new BufferedReader(new StringReader(str));
        this.buf = new char[bufferSize];
    }

    public boolean hasNextInt() throws IOException {
        return hasNext(TokenType.INT);
    }

    public boolean hasNextWord() throws IOException {
        return hasNext(TokenType.WORD);
    }

    public boolean hasNextLine() throws IOException {
        tokenBuf.setLength(0);
        sepBuf.setLength(0);

        int character = peek();
        if (character == -1) {
            return false;
        }

        while (character != -1) {
            if (consumeSep()) {
                curTokenType = TokenType.LINE;
                return true;
            }
            tokenBuf.append((char)character);
            read();
            character = peek();
        }
        curTokenType = TokenType.LINE;
        return true;
    }

    private boolean consumeSep() throws IOException {
        final String sep = System.lineSeparator();
        boolean consumed = false;
        int i = 0;
        int character;
        while (((character = peek()) != -1) && i < sep.length() && (char)character == sep.charAt(i)) {
            sepBuf.append((char)character);
            character = read();
            i++;
        }
        if (i < sep.length()) {
            if (i > 0) {
                tokenBuf.append(sepBuf);
                System.err.println("aaa");
            }
            return consumed;
        }
        if (i == sep.length()) {
            return true;
        }
        return false;
    }

    public int nextInt() throws IOException {
        if (curRadix != 10) {
            return Integer.parseUnsignedInt(next(), curRadix);
        }
        return Integer.parseInt(next(), curRadix);
    }

    public String nextWord() throws IOException {
        return next();
    }

    public String nextLine() throws IOException {
        return next();
    }

    public void close() throws IOException {
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            System.err.println("Failed to close reader");
        }
    }

    private boolean hasNext(TokenType tokenType) throws IOException {
        if (tokenType == TokenType.LINE) {
            return hasNextLine();
        }
        while (true) {
            nextToken();
            if (curTokenType == TokenType.EOF) {
                return false;
            }
            if (curTokenType == tokenType) {
                return true;
            }
        }
    }

    private String next() throws IOException {
        if (curTokenType == TokenType.LINE) {
            return tokenBuf.toString();
        }
        if (tokenBuf.length() == 0 && !hasNext(curTokenType)) {
            throw new NoSuchElementException("Requested element does not exist");
        }
        return tokenBuf.toString();
    }

    private void nextToken() throws IOException {
        tokenBuf.setLength(0);
        skipDelimiters();

        int character = peek();
        TokenType tokenType = getTokenType(character);
        while (tokenType != TokenType.EOF && tokenType == getTokenType(character)) {
            if (tokenType == TokenType.UNKNOWN) {
                read();
                break;
            }
            tokenBuf.append((char)character);
            read();
            character = peek();
        }
        if (tokenType == TokenType.INT) {
            if (peek() == 'o' || peek() == 'O') {
                curRadix = 8;
            } else {
                curRadix = 10;
            }
            read();
        }
        curTokenType = tokenType;
    }

    private TokenType getTokenType(int character) throws IOException {
        if (character == -1) {
            return TokenType.EOF;
        }
        char c = (char)character;
        if (c == '-' || c == '+') {
            if (tokenBuf.length() > 0) {
                tokenBuf.setLength(0);
                return TokenType.UNKNOWN;
            }
            return TokenType.INT;
        }
        if (Character.isDigit(c)) {
            return TokenType.INT;
        }
        if (Character.isLetter(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION) {
            return TokenType.WORD;
        }
        return TokenType.UNKNOWN;
    }

    private void skipDelimiters() throws IOException {
        while (Character.isWhitespace(peek())) {
            read();
        }
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

    private int read() throws IOException {
        if (pos >= len) {
            update();
            if (eof) return -1;
        }
        return buf[pos++];
    }

    private int peek() throws IOException {
        if (pos >= len) {
            update();
            if (eof) return -1;
        }
        return buf[pos];
    }
}
