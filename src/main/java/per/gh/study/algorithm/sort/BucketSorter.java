package per.gh.study.algorithm.sort;

public class BucketSorter implements Sorter {
    @Override
    public Comparable[] sort(Comparable[] arr) {
        int bucketCount = 16;
        int bucketSize = arr.length / bucketCount;
        Comparable[][] buckets = new Comparable[bucketCount + 1][bucketSize];
        int[] sizes = new int[bucketCount + 1];
        for (int i = 0; i < bucketCount; i++) {
            sizes[i] = bucketSize;
        }
        sizes[bucketCount] = arr.length % bucketCount;

        //copy data from arr to buckets
        for (int i = 0; i < buckets.length; i++) {
            System.arraycopy(arr, i * bucketSize, buckets[i], 0, sizes[i]);
            insertionSort(buckets[i], sizes[i]);
        }
        int[] indexes = new int[bucketCount + 1];
        for (int i = 0; i < arr.length; i++) {
            int minBucketIndex = -1;
            for (int j = 0; j < buckets.length; j++) {
                if (indexes[j] < sizes[j]){
                    if (minBucketIndex==-1){
                        minBucketIndex=j;
                    }else if(buckets[minBucketIndex][indexes[minBucketIndex]].compareTo(buckets[j][indexes[j]]) > 0){
                        minBucketIndex = j;
                    }
                }
            }
            arr[i] = buckets[minBucketIndex][indexes[minBucketIndex]++];
        }


        return arr;
    }

    private void insertionSort(Comparable[] arr, int length) {
        for (int i = 1; i < length; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (arr[i].compareTo(arr[j]) <= 0) {
                    Comparable t = arr[i];
                    for (int k = i; k > j + 1; k--) {
                        arr[k] = arr[k - 1];
                    }
                    arr[j + 1] = t;
                    break;
                }
            }
        }
    }

}
