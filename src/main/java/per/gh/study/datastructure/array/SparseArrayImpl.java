package per.gh.study.datastructure.array;

import java.util.Arrays;

public class SparseArrayImpl<T> implements SparseArray<T> {
    private Object[][] array;

    public SparseArrayImpl(T[][] arr, T defaultValue) {
        int rows = arr.length;
        int cols = arr[0].length;
        int count = 0;

        // count
        for (T[] ts : arr) {
            for (int j = 0; j < cols; j++) {
                if (ts[j] != defaultValue) {
                    count++;
                }
            }
        }
        array = new Object[count + 1][3];
        array[0][0] = rows;
        array[0][1] = cols;
        array[0][2] = defaultValue;

        // fill
        int index = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (arr[i][j] != defaultValue) {
                    array[index][0] = i;
                    array[index][1] = j;
                    array[index][2] = arr[i][j];
                    index++;
                }
            }
        }

    }

    public SparseArrayImpl(T[][] arr) {
        this(arr, null);
    }

    @Override
    public void set(int x, int y, T t) {
        int index;
        if ((index = getIndex(x, y)) == -1) {
            Object[][] arr = Arrays.copyOf(array, array.length + 1);
            arr[arr.length - 1][0] = x;
            arr[arr.length - 1][1] = y;
            arr[arr.length - 1][2] = t;
            this.array = arr;
        } else {
            array[index][2] = t;
        }
    }

    @Override
    public T get(int x, int y) {
        int index;
        return (index = getIndex(x, y)) == -1 ? null : (T) array[index][2];
    }

    private int getIndex(int x, int y) {
        for (int i = 1; i < array.length; i++) {
            if ((int) array[i][0] == x && (int) array[i][1] == y) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void remove(int x, int y) {
        int index;
        if ((index = getIndex(x, y)) != -1) {
            Object[][] arr = new Object[array.length - 1][3];
            for (int i = 0; i < index; i++) {
                arr[i][0] = array[i][0];
                arr[i][1] = array[i][1];
                arr[i][2] = array[i][2];
            }
            for (int i = index + 1; i < array.length; i++) {
                arr[i - 1][0] = array[i][0];
                arr[i - 1][1] = array[i][1];
                arr[i - 1][2] = array[i][2];
            }
            this.array = arr;
        }
    }

    @Override
    public T[][] decode() {
        int rows = (int) array[0][0];
        int cols = (int) array[0][1];
        Object defaultValue = array[0][2];

        Object[][] arr = new Object[rows][cols];
        // fill default value
        if (defaultValue != null) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    arr[i][j] = defaultValue;
                }
            }
        }
        // fill non default value
        for (int i = 1; i < array.length; i++) {
            arr[(int) array[i][0]][(int) array[i][1]] = array[i][2];
        }
        return (T[][]) arr;
    }
}
