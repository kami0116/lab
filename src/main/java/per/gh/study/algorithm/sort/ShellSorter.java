package per.gh.study.algorithm.sort;

public class ShellSorter implements Sorter {
    @Override
    public Comparable[] sort(Comparable[] arr) {
        for (int i = 1, group = arr.length >> i; group > 0; i++, group = arr.length >> i) {
            //insert sort
            for (int j = group; j < arr.length; j++) {
                for (int k = j-group; k >=0 ; k-=group) {
                    if (arr[j].compareTo(arr[k])<=0){
                        Comparable t=arr[j];
                        for (int l = j-group; l >=k ; l-=group) {
                            arr[l+group]=arr[l];
                        }
                        arr[k+group]=t;
                    }
                }
            }
        }
        return arr;
    }
}
