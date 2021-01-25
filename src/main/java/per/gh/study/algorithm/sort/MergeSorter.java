package per.gh.study.algorithm.sort;

public class MergeSorter implements Sorter {
    @Override
    public Comparable[] sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
        return arr;
    }

    private void sort(Comparable[] arr, int left, int right) {
        if (right - left > 1) {
            int mid = (left + right) / 2;
            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            Comparable[] ta = new Comparable[right - left + 1];
            int i = left, j = mid + 1, k = 0;

            while (i <= mid && j <= right) {
                if (arr[i].compareTo(arr[j]) < 0) {
                    ta[k++] = arr[i++];
                } else {
                    ta[k++] = arr[j++];
                }
            }
            while (i <= mid) {
                ta[k++] = arr[i++];
            }

            while (j <= right) {
                ta[k++] = arr[j++];
            }
            System.arraycopy(ta, 0, arr, left, ta.length);

        }

    }
}
