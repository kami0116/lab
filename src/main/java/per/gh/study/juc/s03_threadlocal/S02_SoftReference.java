package per.gh.study.juc.s03_threadlocal;

import java.lang.ref.SoftReference;

public class S02_SoftReference {
    public static void main(String[] args) throws InterruptedException {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024 * 1024 * 10]);

        System.out.println(m.get());
        System.gc();
        Thread.sleep(1000);
        System.out.println(m.get());
        byte[] b = new byte[1024 * 1024 * 15];
        System.out.println(m.get());
    }
}
