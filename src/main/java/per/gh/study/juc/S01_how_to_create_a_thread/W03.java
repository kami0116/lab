package per.gh.study.juc.S01_how_to_create_a_thread;

import java.util.concurrent.Callable;

public class W03 implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "通过实现Callable接口，线程结束后可以返回一个对象";
    }
}
