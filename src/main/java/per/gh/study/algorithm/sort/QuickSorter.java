package per.gh.study.algorithm.sort;

public class QuickSorter implements Sorter {
    @Override
    public Comparable[] sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
        return arr;
    }

    private void sort(Comparable[] arr, int left, int right) {
        if (left>=right-1)
            return;
        Comparable pivot = arr[(left+right)/2];
        int l=left,r=right;
        while (l<r){
            while(arr[l].compareTo(pivot)<0){
                l++;
            }

            while(arr[r].compareTo(pivot)>0){
                r--;
            }
            if (l>=r){
                break;
            }else if (arr[l].compareTo(pivot)==0 && arr[r].compareTo(pivot)==0){
                r--;
                continue;
            }
            Comparable t=arr[l];
            arr[l]=arr[r];
            arr[r]=t;
        }
        if (l==r){
            sort(arr,left,r-1);
            sort(arr,l+1,right);
        }else{
            sort(arr,left,r);
            sort(arr,l,right);
        }
    }
}
