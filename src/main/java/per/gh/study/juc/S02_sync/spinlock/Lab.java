package per.gh.study.juc.S02_sync.spinlock;

public class Lab {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        Factory factory = new Factory(warehouse);
        Wholesaler wholesaler = new Wholesaler(warehouse);
        factory.start();
        wholesaler.start();
    }
}
