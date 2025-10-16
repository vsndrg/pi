public class Sum {
    private static int sum(String[] values) {
        int s = 0;

        for (String arg : values) {
            int n = 0;
            boolean isNeg = false;

            for (char c : arg.toCharArray()) {
                if (Character.isDigit(c)) {
                    n *= 10;
                    n += Character.getNumericValue(c);
                } else if (c == '-' && !isNeg) {
                    isNeg = true;
                } else {
                    s += isNeg ? -n : n;
                    n = 0;
                    isNeg = false;
                }

            }
            s += isNeg ? -n : n;
       }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(sum(args));
    }
}

