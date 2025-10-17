import java.util.Arrays;

public class IntList {
    public int length = 0;

    final int growFactor = 2;
    private int capacity = 2;
    private int arr[] = new int[capacity];

    public IntList() {
    }

    public IntList(int length) {
        this.length = length;
        this.capacity = length;

        resize(length);
    }

    public int get(int index) {
        checkLength(index);
        return arr[index];
    }

    public void set(int index, int value) {
        checkLength(index);
        arr[index] = value;
    }

    public void append(int value) {
        if (length >= capacity) {
            resize();
        }
        arr[length++] = value;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public String toString() {
        StringBuilder strBuf = new StringBuilder();
        for (int i = 0; i < length; i++) {
            strBuf.append(Integer.toString(arr[i]) + (i < length - 1 ? " " : ""));
        }
        return strBuf.toString();
    }

    private void resize() {
        capacity *= growFactor;
        arr = Arrays.copyOf(arr, capacity);
    }

    private void resize(int newLength) {
        capacity = newLength;
        arr = Arrays.copyOf(arr, newLength);
    }

    private void checkLength(int index) {
        if (index >= length) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
