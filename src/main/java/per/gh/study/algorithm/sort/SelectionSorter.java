package per.gh.study.algorithm.sort;

public class SelectionSorter implements Sorter {
    @Override
    public Comparable[] sort(Comparable[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int index = 0;
            Comparable max = arr[0];
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j].compareTo(max) > 0) {
                    max = arr[j];
                    index = j;
                }
            }
            Comparable t = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = max;
            arr[index] = t;
        }
        return arr;
    }
}
