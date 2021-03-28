package per.gh.study.juc.S02_sync.c03_readwritelock;

public class MyFile {
    private String context = "啥也没有";

    public void write(String context) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+" 开始写入...");
        Thread.sleep(1000);
        this.context = context;
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName()+" 写入完毕...");
    }

    public String read() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+" 开始读取...");
        Thread.sleep(200);
        System.out.println(Thread.currentThread().getName()+" 读取成功...");
        return context;
    }
}
