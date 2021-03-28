package per.gh.study.juc.S02_sync.spinlock;

public class Warehouse {
    public static final int MAXSIZE = 100;
    public Spinlock lock = new Spinlock();
    public int stock;
}
