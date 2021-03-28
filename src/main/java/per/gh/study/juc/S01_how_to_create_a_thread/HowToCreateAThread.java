package per.gh.study.juc.S01_how_to_create_a_thread;

import java.util.concurrent.*;

public class HowToCreateAThread {
    public static void main(String[] args) throws Exception {
        //way 1 直接继承Thread，实现，运行一条龙
        W01 w01 = new W01();
        w01.start();

        //way 2 实现Runnable，再交由Thread执行
        W02 w02 = new W02();
        new Thread(w02).start();

        //way 3 实现Callable，再交给FutureTask，FutureTask是通过第一种方法实现的，在它的run方法中会调用Callable的call方法，并将结果、异常、状态存在变量中等待调取。
        W03 w03 = new W03();
        FutureTask<String> futureTask = new FutureTask<>(w03);
        Thread t03 = new Thread(futureTask);
        t03.start();
        t03.join();
        System.out.println(futureTask.get());

        //way 4
        ExecutorService executorService = new ThreadPoolExecutor(3, 5, 1, TimeUnit.SECONDS, new LinkedBlockingQueue(3), new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 9; i++) {
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName());
            });
        }
        executorService.shutdown();
    }
}
