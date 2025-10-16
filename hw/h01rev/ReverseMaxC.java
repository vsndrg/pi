import java.util.Arrays;
import java.util.Scanner;

public class ReverseMaxC {

    public static void main(String[] args) {
        Scanner sStr = new Scanner(System.in);
        int[][] nums = new int[4][];
        int j = 0;
        int maxRowLen = 0;

        while (sStr.hasNextLine()) {
            String str = sStr.nextLine();
            Scanner sNum = new Scanner(str);
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

            maxRowLen = Integer.max(maxRowLen, i);

            sNum.close();
        }

        for (int k = 0; k < maxRowLen; k++) {
            int curMax = Integer.MIN_VALUE;
            for (int i = j - 1; i >= 0; i--) {
                if (k < nums[i].length) {
                    curMax = Integer.max(curMax, nums[i][k]);
                    nums[i][k] = curMax;
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
}
