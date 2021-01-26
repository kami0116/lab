package per.gh.study.algorithm.sort;

public class InsertionSorter implements Sorter{

    @Override
    public Comparable[] sort(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i-1; j >=0; j--) {
                if (arr[j].compareTo(arr[i])>=0){
                    //insert to j+1
                    Comparable t = arr[i];
                    for (int k = i; k > j+1 ; k--) {
                        arr[k]=arr[k-1];
                    }
                    arr[j+1]=t;
                    break;
                }
            }
        }
        return arr;
    }
}
