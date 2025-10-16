public class SumDoubleHex {
    private static double parse(String buf) {
        if ((buf.startsWith("0x") || buf.startsWith("0X")) && !buf.contains("p") && !buf.contains("P")) {
            buf += "p0";
        }
        return Double.parseDouble(buf);
    }

    private static double sum(String[] values) {
        double s = 0;

        for (String arg : values) {
            int start = 0;
            int len = arg.length();
            for (int i = 0; i < len; i++) {
                char cur = arg.charAt(i);
                if (Character.isWhitespace(cur) || i + 1 == len) {
                    if (i + 1 == len) {
                        i++;
                    }
                    if (start != i) {
                        s += parse(arg.substring(start, i));
                    }
                    start = i + 1;
                }
            }
        }
        return s;
    }
    public static void main(String[] args) {
        System.out.println(sum(args));
    }
}

