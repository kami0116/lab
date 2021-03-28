package per.gh.study.juc.threadpool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

public class TreadPoolExecutorTest {
    ThreadPoolExecutor pool;

    @Before
    public void init() {
        pool = new ThreadPoolExecutor(
                1,
                2,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                new MyThreadFactory(),
                new MyReject());
    }

    @Test
    public void test() {
        for (int i = 0; i < 4; i++) {
            pool.execute(new MyTask("task" + i) {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " start to execute " + taskName);
                    sleepWait(1000);
                    System.out.println(Thread.currentThread().getName() + " finish executing " + taskName);
                }
            });
        }
    }

    @After
    public void finish() throws InterruptedException {
        pool.shutdown();
        pool.awaitTermination(10,TimeUnit.SECONDS);
    }

    void noSleepWait(long millis){
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis()-start < millis);
    }

    void sleepWait(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static abstract class MyTask implements Runnable {
        String taskName;

        public MyTask(String taskName) {
            this.taskName = taskName;
        }

    }

    static class MyThreadFactory implements ThreadFactory {
        private int count = 0;

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, getName());
        }

        String getName() {
            return "my-thread-" + count++;
        }
    }

    static class MyReject implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if (r instanceof MyTask) {
                System.out.println("log: " + ((MyTask) r).taskName+"因为没有接待的worker和queue，被拒绝执行了");
                System.out.println(executor.getActiveCount());

            }
        }
    }
}
