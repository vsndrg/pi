import java.util.Arrays;
import java.util.Scanner;

public class Reverse {

    public static void main(String[] args) {
        Scanner sStr = new Scanner(System.in);
        int[][] nums = new int[4][];
        int j = 0;

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
}
