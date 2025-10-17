// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.io.Reader;
// import java.io.StringReader;
// import java.nio.charset.Charset;
// import java.util.NoSuchElementException;
//
// class MyScanner {
//     private final Reader in;
//     private final char[] buf;
//
//     private StringBuilder tokenBuf = new StringBuilder();
//     private StringBuilder sepBuf = new StringBuilder();
//
//     private final String lineSep = System.lineSeparator();
//
//     private TokenType expectedTokenType;
//     private int curRadix = 10;
//
//     private int pos = 0;
//     private int len = 0;
//     private boolean eof = false;
//     private boolean isClosed = false;
//
//     private enum TokenType {
//         WORD, INT, SEP, SIGN, LINESEP, EOF, UNKNOWN;
//     }
//
//     public MyScanner(InputStream inputStream, Charset charset, int bufferSize) {
//         this.in = new BufferedReader(new InputStreamReader(inputStream, charset));
//         this.buf = new char[bufferSize];
//     }
//
//     public MyScanner(String str, int bufferSize) {
//         this.in = new BufferedReader(new StringReader(str));
//         this.buf = new char[bufferSize];
//     }
//
//     public boolean hasNextInt() throws IOException {
//         checkClosed();
//         return hasNext(TokenType.INT);
//     }
//
//     public boolean hasNextWord() throws IOException {
//         checkClosed();
//         return hasNext(TokenType.WORD);
//     }
//
//     public boolean hasNextLine() throws IOException {
//         checkClosed();
//         tokenBuf.setLength(0);
//         sepBuf.setLength(0);
//
//         int character = peek();
//         if (character == -1) {
//             return false;
//         }
//
//         while (character != -1) {
//             if (consumeSep()) {
//                 return true;
//             }
//             tokenBuf.append((char)character);
//             read();
//             character = peek();
//         }
//         return true;
//     }
//
//     private boolean consumeSep() throws IOException {
//         final String sep = System.lineSeparator();
//         boolean consumed = false;
//         int i = 0;
//         int character;
//         while (((character = peek()) != -1) && i < sep.length() && (char)character == sep.charAt(i)) {
//             sepBuf.append((char)character);
//             character = read();
//             i++;
//         }
//         if (i < sep.length()) {
//             if (i > 0) {
//                 tokenBuf.append(sepBuf);
//             }
//             return consumed;
//         }
//         if (i == sep.length()) {
//             return true;
//         }
//         return false;
//     }
//
//     public int nextInt() throws IOException {
//         checkClosed();
//         if (curRadix != 10) {
//             return Integer.parseUnsignedInt(next(), curRadix);
//         }
//         return Integer.parseInt(next(), curRadix);
//     }
//
//     public String nextWord() throws IOException {
//         checkClosed();
//         return next();
//     }
//
//     public String nextLine() throws IOException {
//         checkClosed();
//         return next();
//     }
//
//     public void close() throws IOException {
//         checkClosed();
//         try {
//             if (in != null) {
//                 in.close();
//                 isClosed = true;
//             }
//         } catch (IOException e) {
//             System.err.println("Failed to close reader");
//         }
//     }
//
//     private boolean hasNext(TokenType tokenType) throws IOException {
//         expectedTokenType = tokenType;
//         if (expectedTokenType == TokenType.LINE) {
//             return hasNextLine();
//         }
//         tokenBuf.setLength(0);
//         skipDelimiters();
//
//         TokenType tt = getTokenType(peek());
//
//         // Find expected token
//         while (tt != expectedTokenType) {
//             if (tt == TokenType.EOF && expectedTokenType != TokenType.SEP) {
//                 return false;
//             }
//             if (tt == TokenType.SEP) {
//                 sepBuf.append(peek());
//             }
//             tt = readToken();
//         }
//         // Consume expected token
//         while (tt == expectedTokenType) {
//             tokenBuf.append((char)peek());
//             tt = readToken();
//         }
//
//         if (expectedTokenType == TokenType.INT) {
//             if (peek() == 'o' || peek() == 'O') {
//                 curRadix = 8;
//                 read();
//             } else {
//                 curRadix = 10;
//             }
//         }
//         return true;
//     }
//
//     private boolean matchesExpectedToken(TokenType tokenType) {
//         if (expectedTokenType == TokenType.SEP) {
//             return sepBuf.toString().equals(lineSep);
//         }
//         return tokenType == expectedTokenType;
//     }
//
//     private String next() throws IOException {
//         if (expectedTokenType == TokenType.LINE) {
//             return tokenBuf.toString();
//         }
//         if (tokenBuf.length() == 0 && !hasNext(expectedTokenType)) {
//             throw new NoSuchElementException("Requested element does not exist");
//         }
//         return tokenBuf.toString();
//     }
//
//     private TokenType getTokenType(int character) throws IOException {
//         if (character == -1) {
//             return TokenType.EOF;
//         }
//         char c = (char)character;
//         if (c == '-' || c == '+') {
//             if (tokenBuf.length() > 0) {
//                 tokenBuf.setLength(0);
//                 return TokenType.UNKNOWN;
//             }
//             return TokenType.INT;
//         }
//         if (Character.isDigit(c)) {
//             return TokenType.INT;
//         }
//         if (Character.isLetter(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION) {
//             return TokenType.WORD;
//         }
//         if (lineSep.indexOf(c) == sepBuf.length()) {
//             return TokenType.SEP;
//         }
//         return TokenType.UNKNOWN;
//     }
//
//     private void skipDelimiters() throws IOException {
//         while (Character.isWhitespace(peek())) {
//             read();
//         }
//     }
//
//     private void checkClosed() {
//         if (isClosed) {
//             throw new IllegalStateException();
//         }
//     }
//
//     private TokenType readToken() throws IOException {
//         read();
//         return getTokenType(peek());
//     }
//
//     private void update() throws IOException {
//         if (eof) return;
//         len = in.read(buf, 0, buf.length);
//         pos = 0;
//         if (len == -1) {
//             eof = true;
//             len = 0;
//         }
//     }
//
//     private int read() throws IOException {
//         if (pos >= len) {
//             update();
//             if (eof) return -1;
//         }
//         return buf[pos++];
//     }
//
//     private int peek() throws IOException {
//         if (pos >= len) {
//             update();
//             if (eof) return -1;
//         }
//         return buf[pos];
//     }
// }
