// import java.io.IOException;
// import java.io.Reader;
// import java.util.InputMismatchException;
//
// public class MyScanner {
//     private final Reader in;
//     private final char[] buf;
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
//     public boolean hasNextLine() throws IOException {
//         if (eof && pos >= len) {
//             return false;
//         }
//
//         if (pos < len) {
//             return true;
//         }
//         update();
//
//         return pos < len;
//     }
//
//     public String nextLine() throws IOException {
//         if (eof && pos >= len) return null;
//
//         StringBuilder lineBuf = new StringBuilder();
//
//         while (true) {
//             int ch = read();
//             if (ch == -1) {
//                 if (lineBuf.length() == 0) {
//                     return null;
//                 }
//                 return lineBuf.toString();
//             } else if (ch == '\n') {
//                 return lineBuf.toString();
//             } else if (ch == '\r') {
//                 int next = peek();
//                 if (next == '\n') {
//                     read();
//                 }
//                 return lineBuf.toString();
//             } else {
//                 lineBuf.append((char)ch);
//             }
//         }
//     }
//
//     public boolean hasNextInt() throws IOException {
//         while (true) {
//             int ch = peek();
//             if (ch == -1) {
//                 return false;
//             }
//             if (Character.isDigit((char)ch)) {
//                 return true;
//             }
//             if (ch == '+' || ch == '-') {
//                 int posSave = pos;
//                 read();
//                 int next = peek();
//                 pos = posSave;
//                 // if (next == -1) {
//                 //     return false;
//                 // }
//                 if (next != -1 && Character.isDigit((char)next)) {
//                     return true;
//                 }
//             }
//             read();
//         }
//     }
//
//     public Integer nextInt() throws IOException, InputMismatchException {
//         while (true) {
//             int ch = peek();
//             if (ch == -1) {
//                 return 0;
//             }
//             if (Character.isDigit((char)ch) || ch == '+' || ch == '-') {
//                 break;
//             }
//             read();
//         }
//
//         StringBuilder intBuf = new StringBuilder();
//         int ch = peek();
//         if (ch == '+' || ch == '-') {
//             int posSave = pos;
//             read();
//             int next = peek();
//             if (next != -1 && Character.isDigit((char)next)) {
//                 intBuf.append((char)ch);
//             } else {
//                 pos = posSave + 1;
//                 return nextInt();
//             }
//         }
//
//         while (true) {
//             int p = peek();
//             if (p == -1) {
//                 break;
//             }
//             if (!Character.isDigit((char)p)) {
//                 break;
//             }
//             intBuf.append((char)read());
//         }
//
//         return Integer.parseInt(intBuf.toString());
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
//
// }
//
