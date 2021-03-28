package per.gh.study.juc.threadpool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolTest {
    int size = 1_000_000;
    int[] arr;
    Instant start;

    @Before
    public void init() {
        arr = new int[size];
        Random random = new Random(0);
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size);
        }
        start = Instant.now();
    }

    //    @Test
    public void singleThread() {
        insertSort(arr, 0, size);
        /*
        size=1000000, 用时：188273毫秒
         */
    }

    @Test
    public void forkJoin() throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool(8);
        RecursiveAction sortAction = new RecursiveAction() {
            @Override
            protected void compute() {
                RecursiveSortAction recursiveSortAction = new RecursiveSortAction(arr, 0, size);
                recursiveSortAction.join();
            }
        };
        pool.submit(sortAction);
        sortAction.get();
    }

    @After
    public void finish() {
        System.out.println("用时：" + Duration.between(start, Instant.now()).toMillis() + "毫秒");
        checkSort(arr);
    }

    public static void insertSort(int[] arr, int start, int length) {
        for (int i = start + 1; i < start + length; i++) {
            for (int j = start; j < i; j++) {
                if (arr[j] > arr[i]) {
                    int t = arr[i];
                    for (int k = i; k > j; k--) {
                        arr[k] = arr[k - 1];
                    }
                    arr[j] = t;
                    break;
                }
            }
        }
    }

    public static void merge(int[] arr, int start, int l1, int l2) {
        int[] arr1 = new int[l1], arr2 = new int[l2];
        System.arraycopy(arr, start, arr1, 0, l1);
        System.arraycopy(arr, start + l1, arr2, 0, l2);
        int i = 0, j = 0;
        while (i < l1 && j < l2) {
            if (arr1[i] <= arr[j]) {
                arr[start + i + j] = arr1[i++];
            } else {
                arr[start + i + j] = arr2[j++];
            }
        }
        if (i == l1) {
            System.arraycopy(arr2, j, arr, start + i + j, arr2.length - j);
        } else {
            System.arraycopy(arr1, i, arr, start + i + j, arr1.length - i);
        }

    }

    public void checkSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            assert arr[i] <= arr[i + 1];
        }
    }

    static class RecursiveSortAction extends RecursiveAction {
        private static final int minSize = 1000;
        private final int[] arr;
        private final int start;
        private final int length;

        RecursiveSortAction(int[] arr, int start, int length) {
            this.arr = arr;
            this.start = start;
            this.length = length;
        }

        @Override
        protected void compute() {
            if (length > minSize) {
                insertSort(arr, start, length);
            } else {
                int l1 = length / 2, l2 = length - l1;
                RecursiveSortAction sub1 = new RecursiveSortAction(arr, start, l1);
                RecursiveSortAction sub2 = new RecursiveSortAction(arr, start + l1, l2);
                sub1.fork();
                sub2.fork();
                sub1.join();
                sub2.join();
                merge(arr, start, l1, l2);
            }
        }
    }
}
