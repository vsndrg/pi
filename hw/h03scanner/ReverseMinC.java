import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ReverseMinC {
    private static void reverse() throws IOException {
        MyScanner sStr = new MyScanner(System.in, StandardCharsets.UTF_8, 8192);

        int[][] nums = new int[2][];
        int j = 0;
        int maxRowLen = 0;

        while (sStr.hasNextLine()) {
            String str = sStr.nextLine();
            MyScanner sNum = new MyScanner(str, 8192);
            int[] arr = new int[2];
            int i = 0;

            while (sNum.hasNextInt()) {
                if (i == arr.length) {
                    arr = Arrays.copyOf(arr, arr.length * 2);
                }
                arr[i++] = sNum.nextInt();
            }
            arr = Arrays.copyOf(arr, i);

            if (j == nums.length) {
                nums = Arrays.copyOf(nums, nums.length * 2);
            }
            nums[j++] = arr;

            maxRowLen = Integer.max(maxRowLen, i);

            sNum.close();
        }

        int[] mins = new int[maxRowLen];
        Arrays.fill(mins, Integer.MAX_VALUE);

        for (int i = j - 1; i >= 0; i--) {
            for (int k = 0; k < nums[i].length; k++) {
                if (nums[i][k] < mins[k]) {
                    mins[k] = nums[i][k];
                } else {
                    nums[i][k] = mins[k];
                }
            }
        }

        for (int k = 0; k < j; k++) {
            for (int i = 0; i < nums[k].length; i++) {
                System.out.print(nums[k][i] + " ");
            }
            System.out.println();
        }
        sStr.close();

    }

    public static void main(String[] args) {
        try {
            reverse();
        } catch (IOException e) {
            System.err.println("Input/Output error: " + e);
        }
    }
}

