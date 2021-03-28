package per.gh.study.juc.S00_jmm;

public class OutOfOrderExecution {
    static int x = 0, y = 0, a = 0, b = 0;

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 1000000000; i++) {
            x = y = a = b = 0;
            Thread t1 = new Thread(() -> {
                Thread.yield();
                a = 1;
                x = b;
            });
            Thread t2 = new Thread(() -> {
                b = 1;
                y = a;
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            if (x == 0 && y == 0) {
                System.out.println("在第" + i + "次的时候，发生了指令重排序");
                System.out.println(x + " " + y);
                return;
            }
        }
        System.out.println("没有发生指令重排序");
    }
}
