// import java.io.IOException;
// import java.io.Reader;
// import java.util.NoSuchElementException;
//
// class MyScanner {
//     private final Reader in;
//
//     private final char[] buf;
//     private StringBuilder tokenBuf = new StringBuilder();
//     private StringBuilder sepBuf = new StringBuilder();
//
//     private int pos = 0;
//     private int len = 0;
//     private boolean eof = false;
//
//     public MyScanner(Reader in, int bufferSize) {
//         this.in = in;
//         this.buf = new char[bufferSize];
//     }
//
//     public boolean hasNextInt() throws IOException {
//         return hasNext('i');
//     }
//
//     public boolean hasNextWord() throws IOException {
//         return hasNext('w');
//     }
//
//     public int nextInt() throws IOException {
//         return Integer.parseInt(next('i'));
//     }
//
//     public String nextWord() throws IOException {
//         return next('w');
//     }
//
//     // private boolean hasNext(char type) throws IOException {
//     //     while (true) {
//     //         int character = read(); 
//     //         if (character == -1) {
//     //             return false;
//     //         }
//     //         if (isPartOf((char)character, type)) {
//     //             tokenBuf.append((char)character);
//     //         } else {
//     //             if (tokenBuf.length() > 0) {
//     //                 return true;
//     //             }
//     //         }
//     //     }
//     // }
//
//     private boolean hasNext(char type) throws IOException {
//         while (true) {
//             if (!processToken(type)) {
//
//             }
//         }
//     }
//     private String next(char type) throws IOException {
//         if (tokenBuf.length() == 0 && !hasNext(type)) {
//             throw new NoSuchElementException("Requested element does not exist");
//         }
//         return tokenBuf.toString();
//     }
//
//     private boolean processToken(char type) throws IOException {
//         int character = read();
//         if (character == -1) {
//             return false;
//         }
//         if (type == 'i') {
//             if (character == '-' || character == '+') {
//                 if (tokenBuf.length() == 0) {
//                     return processToken(type);
//                 }
//             } else if (Character.isDigit(character)) {
//                 int len = tokenBuf.length();
//                 char last = tokenBuf.charAt(len - 1);
//                 if (len == 0 || len > 0 && (Character.isDigit(last) || last == '-' || last == '+')) {
//
//                 }
//             }
//         }
//         if (type == 'w') {
//             return Character.isLetter(symbol) || symbol == '\'' || Character.getType(symbol) == Character.DASH_PUNCTUATION;
//         }
//         if (type == 'l') {
//             if (sepBuf.length() == 0) {
//                 if 
//             }
//         }
//         return false;
//     }
//     // private boolean isPartOf(char symbol, char type) throws IOException {
//     //     if (type == 'i') {
//     //         if (symbol == '-' || symbol == '+') {
//     //             tokenBuf.append(symbol);
//     //             int character = peek();
//     //             if (character == -1) {
//     //                 return false;
//     //             }
//     //             // int posSave = pos;
//     //             // read();
//     //             // int character = peek();
//     //             // if (character == -1) {
//     //             //     return false;
//     //             // }
//     //             // if (Character.isDigit(character)) {
//     //             //     return true;
//     //             // }
//     //             // pos = posSave;
//     //         }
//     //         return Character.isDigit(symbol) && (pos == 0 || pos > 0 && !Character.isLetter(symbol));
//     //     }
//     //     if (type == 'w') {
//     //         return Character.isLetter(symbol) || symbol == '\'' || Character.getType(symbol) == Character.DASH_PUNCTUATION;
//     //     }
//     //     if (type == 'l') {
//     //         if (sepBuf.length() == 0) {
//     //             if 
//     //         }
//     //     }
//     //     return false;
//     // }
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
//
//
// }
