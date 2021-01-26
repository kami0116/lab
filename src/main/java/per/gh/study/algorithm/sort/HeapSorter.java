package per.gh.study.algorithm.sort;

public class HeapSorter implements Sorter {
    @Override
    public Comparable[] sort(Comparable[] arr) {
        int length = arr.length;
        for (int i = length / 2 - 1; i > 0; i--) {
            adjust(arr, i, length);
        }

        for (; length > 0; length--) {
            adjust(arr, 0, length);
            swap(arr, 0, length - 1);
        }

        return arr;
    }

    private void adjust(Comparable[] arr, int i, int length) {
        for (int left = i * 2 + 1, right = left + 1; left < length; left = i * 2 + 1, right = left + 1) {
            if (right < length && arr[left].compareTo(arr[right]) < 0 && arr[i].compareTo(arr[right]) < 0) {
                swap(arr, i, right);
                i = right;
            } else if (arr[i].compareTo(arr[left]) < 0) {
                swap(arr, i, left);
                i = left;
            } else {
                return;
            }
        }

    }

    private void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private void max(Comparable[] arr, int i, int j, int k) {

    }
}
