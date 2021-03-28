package per.gh.study.unsafe;

import org.junit.Before;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTest {
    private Unsafe U;

    @Before
    public void prepare() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            U = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void putOrderedObject() {
        int length = 8;
        int index = 3;
        UnsafeTest[] arr = new UnsafeTest[length];
        int offset = (index + 4) << 2;
        U.putOrderedObject(arr, offset, this);
        for (int i = 0; i < length; i++) {
            if (i == index) {
                assert arr[i] == this;
            } else {
                assert arr[i] == null;
            }
        }
    }
}
