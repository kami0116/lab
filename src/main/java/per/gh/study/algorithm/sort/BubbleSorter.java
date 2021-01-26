package per.gh.study.algorithm.sort;

public class BubbleSorter implements Sorter {

    @Override
    public Comparable[] sort(Comparable[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    Comparable t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                    flag = false;
                }
            }
            if (flag)
                return arr;
        }
        return arr;
    }
}
