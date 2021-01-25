package per.gh.study.algorithm.sort;

import org.junit.After;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class SortTest {
    Sorter sorter;
    Integer[] arr;
    int size = 100000;

    @Test
    public void bubble() {
        sorter = new BubbleSorter();
    }

    @Test
    public void select() {
        sorter = new SelectionSorter();
    }

    @Test
    public void insert() {
        sorter = new InsertionSorter();
    }

    @Test
    public void shell() {
        sorter = new ShellSorter();
    }

    @Test
    public void quick() {
        sorter = new QuickSorter();
    }

    @Test
    public void merge() {
        sorter = new MergeSorter();
    }

    @Test
    public void radix() {
        sorter = new RadixSorter();
    }

    @Test
    public void heap() {
        sorter = new HeapSorter();
    }

    @Test
    public void bucket() {
        sorter = new BucketSorter();
    }

    @After
    public void test() {
        arr = new Integer[size];
        testSchedule();
    }

    void testSchedule() {
        sortedArrayTest();
        randomArrayTest();
        reversedArrayTest();
    }

    void sortedArrayTest() {
        for (Integer i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        Instant start = Instant.now();
        sorter.sort(arr);
        System.out.println(sorter.getClass().getSimpleName() + "\tsorted array\t: " + Duration.between(start, Instant.now()).toMillis());
        for (Integer i = 0; i < arr.length; i++) {
            assert arr[i].compareTo(i) == 0;
        }
    }

    void randomArrayTest() {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(arr.length);
        }
        int sum = 0;
        for (Comparable i : arr) {
            sum += (int) i;
        }
        Instant start = Instant.now();
        sorter.sort(arr);
        System.out.println(sorter.getClass().getSimpleName() + "\trandom array\t: " + Duration.between(start, Instant.now()).toMillis());
        for (Comparable i : arr) {
            sum -= (int) i;
        }
        assert sum == 0;
    }

    void reversedArrayTest() {
        for (Integer i = arr.length - 1; i >= 0; i--) {
            arr[i] = i;
        }
        Instant start = Instant.now();
        sorter.sort(arr);
        System.out.println(sorter.getClass().getSimpleName() + "\tsorted array\t: " + Duration.between(start, Instant.now()).toMillis());
        for (Integer i = 0; i < arr.length; i++) {
            assert arr[i].compareTo(i) == 0;
        }
    }
}
