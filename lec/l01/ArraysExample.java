// import java.util.Arrays;
import java.util.Scanner;

public class ArraysExample {
    public static void fill(int[] array, int value) {
        // array = new int[3];
        for (int i = 0; i < array.length; i++) {
            array[i] = value;
        }
    }

    public static int[] create(int length, int value) {
        int[] result = new int[length];
        fill(result, value); 
        return result;
    }

    public static void dump(String name, int[] array) {
        System.out.print(name + " =");
        for (int i : array) {
            System.out.print(" " + i);
        }
        System.out.println();
    }

    static void copy(int[] as, int aOffset, int[] bs, int bOffset, int length) {
        for (int i = length - 1; i >= 0; i--) {
            bs[i + bOffset] = as[i + aOffset];
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        if (s.hasNextInt()) {
            System.out.println(s.nextInt() + s.nextInt());
        } else {
            System.out.println("not int");
        }

        s.close();



        // int[] as = create(10, 5);
        // int[] bs = new int[]{10, 20, 30, 40, 50, 60};
        //
        // System.arraycopy(bs, 0, bs, 2, 4);
        // System.out.println(Arrays.deepToString(
        //     new int[][]{as, bs}
        // ));



        // copy(as, 2, bs, 3, 2);

        // dump("as", as);
        // dump("bs", bs);




        // int sum = 0;
        //
        // int[][] save = new int[10_000][];
        // for (int i = 0; i < save.length; i++) {
        //     int[] arr = new int[1000_000];
        //     sum += arr[0];
        //     save[i] = arr;
        //     if (i % 1000 == 0) {
        //         System.out.println(i);
        //     }
        // }
        // System.out.println("done");




        // int[] as = new int[10];
        // int[] bs = as;
        // // as = new int[10];
        // fill(as, 30);
        // dump("as", as);
        // dump("bs", bs);

        // System.out.println(as == bs);
        // System.out.println(new int[3] == new int[3]);




        // String strings[] = new String[]{"hello", "vsndrg", null};
        // int[][] ints2d = new int[][]{
        //     {10, 30},
        //     null,
        //     {20}
        // };
        // System.out.println(ints2d[0]);
        //
        // int[][][] ints3d = new int[][][]{ints2d, ints2d};
        // System.out.println(ints3d);
        // // for (int i = 1; i < ints2d.length; i++) {
        // //     ints2d[i] = new int[i + 2];
        // // }
        //
        // // ints2d[0] = ints2d[1];
        // // ints2d[1][1] = 10;
        //
        // for (int[] row : ints2d) {
        //     if (row == null) {
        //         System.out.println("null");
        //     }
        //     else {
        //         for (int i : row) {
        //             System.out.print(i + " ");
        //         }
        //         System.out.println();
        //     }
        // }





        // double[] doubles;
        // char[] chars;
        // boolean[] booleans = new boolean[0];
        //
        // ints = new int[3];
        // doubles = new double[3];
        //
        // System.out.println(ints.length);
        // for (int i = 0; i < ints.length; i++) {
        //     System.out.println(ints[i]);
        // }
        // System.out.println(doubles.length);
        // for (int i = 0; i < doubles.length; i++) {
        //     System.out.println(doubles[i]);
        // }
        // System.out.println(booleans.length);
        // for (int i = 0; i < booleans.length; i++) {
        //     System.out.println(booleans[i]);
        // }
        // System.out.println(null == "null");
        //
        // for (String string : strings) {
        //     System.out.println(string + " " + (string == null));
        // }
        //
        // for (int i = 0; i < ints.length; i++) {
        //     ints[i] = 10 + i * 100;
        // }
        // for (double i : ints) {
        //     System.out.println(i);
        // }
        // ints = new int[args.length * 2];
        // System.out.println(args.length);
        // System.out.println(ints.length);
    }
}
