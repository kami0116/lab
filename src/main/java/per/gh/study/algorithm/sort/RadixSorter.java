package per.gh.study.algorithm.sort;

public class RadixSorter implements Sorter<Integer> {
    @Override
    public Integer[] sort(Integer[] arr) {
        int radixSize = 16;
        int[] sizes = new int[radixSize];
        Integer[][] radixes = new Integer[radixSize][arr.length];
        Integer max = max(arr);
        Integer min = min(arr);
        if (min < 0) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] -= min;
            }
        }
        int m = 0;
        for (; max > 0; m++) {
            max >>= 4;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < arr.length; j++) {
                int index = (arr[j] >> (4 * i)) & 15;
                radixes[index][sizes[index]++] = arr[j];
            }
            for (int j = 0, k = 0, l = 0; j < arr.length; j++) {
                while (l == sizes[k]) {
                    l = 0;
                    k++;
                }
                arr[j] = radixes[k][l++];
            }
            for (int j = 0; j < 16; j++) {
                sizes[j] = 0;
            }
        }

        if (min < 0) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] += min;
            }
        }
        return arr;
    }

    private Integer max(Integer[] arr) {
        Integer max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    private Integer min(Integer[] arr) {
        Integer min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }


}
