package per.gh.study.datastructure.array;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

public class SparseArrayTest {
    Integer[][] arr;
    Integer defaultValue = 0;

    @Before
    public void prepare() {
        arr = new Integer[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                arr[i][j] = defaultValue;
            }
        }
        arr[1][2] = 5;
        arr[3][3] = 8;
        arr[4][3] = 7;
    }

    @Test
    public void test() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("正常数组:");
        show(arr);

        System.out.println("sparse数组:");
        SparseArrayImpl<Integer> sparseArray = new SparseArrayImpl(arr, defaultValue);
        Field arrayField = SparseArrayImpl.class.getDeclaredField("array");
        arrayField.setAccessible(true);
        show((Object[][]) arrayField.get(sparseArray));

        System.out.println("解码后:");
        show(sparseArray.decode());

    }

    void show(Object[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

}
