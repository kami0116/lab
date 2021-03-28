package per.gh.study.juc.s03_threadlocal;

public class S01_ThreadLocal {
    public static void main(String[] args) {
        ThreadLocal<String> tl1 = new ThreadLocal<>();
        ThreadLocal<Integer> tl2 = new ThreadLocal<>();
        Thread t1 = new Thread(() -> {

            tl1.set("hello i am t1");
            tl2.set(19);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("t1: "+tl1.get());
            System.out.println("t1: "+tl2.get());

        }, "t1");

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(tl1.get());

        }, "t2");
        t1.start();
        t2.start();
    }
}
