import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Reverse {
    private static void reverse() throws IOException {
        MyScanner sStr = new MyScanner(System.in, StandardCharsets.UTF_8, 8192);
        int[][] nums = new int[4][];
        int j = 0;

        while (sStr.hasNextLine()) {
            String str = sStr.nextLine();
            MyScanner sNum = new MyScanner(str, 8192);
            int[] arr = new int[4];
            int i = 0;

            while (sNum.hasNextInt()) {
                if (i == arr.length) {
                    arr = Arrays.copyOf(arr, arr.length * 4);
                }
                arr[i++] = sNum.nextInt();
            }
            arr = Arrays.copyOf(arr, i);

            if (j == nums.length) {
                nums = Arrays.copyOf(nums, nums.length * 4);
            }
            nums[j++] = arr;

            sNum.close();
        }

        for (j = j - 1; j >= 0; j--) {
            for (int i = nums[j].length - 1; i >= 0; i--) {
                System.out.print(nums[j][i] + " ");
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

