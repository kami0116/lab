package per.gh.study.juc.S01_how_to_create_a_thread;

public class W01 extends Thread {
    @Override
    public void run() {
        System.out.println("继承thread...");
    }
}
