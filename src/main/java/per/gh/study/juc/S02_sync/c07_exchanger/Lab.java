package per.gh.study.juc.S02_sync.c07_exchanger;

import java.util.concurrent.Exchanger;

public class Lab {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            String s = "t1";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);

        }, "T1").start();

        new Thread(() -> {
            String s = "t2";
            try {
                Thread.sleep(1000);
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);

        }, "T2").start();
    }
}
