package per.gh.study.juc.s03_threadlocal;

import java.lang.ref.WeakReference;

public class S03_WeakReference {
    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<String> tl=new ThreadLocal<>();
        tl.set("hello");
        System.out.println(tl.get());

        System.gc();
        Thread.sleep(1000);

        System.out.println(tl.get());
    }

    public static void lab1() throws InterruptedException {
        WeakReference<Object> m = new WeakReference<>(new Object());
        System.out.println(m.get());
        System.gc();
        Thread.sleep(1000);
        System.out.println(m.get());
    }
}
