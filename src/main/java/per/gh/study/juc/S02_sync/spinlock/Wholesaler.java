package per.gh.study.juc.S02_sync.spinlock;

import java.util.Random;

public class Wholesaler extends Thread {
    private Warehouse warehouse;
    private Random random;
    private int next;

    public Wholesaler(Warehouse warehouse) {
        this.warehouse = warehouse;
        random = new Random();
        next = random.nextInt(100) + 1;
    }

    @Override
    public void run() {
        while (true) {
            warehouse.lock.lock();
            if (warehouse.stock >= next) {
                warehouse.stock -= next;
                System.out.println("批发商批发了" + next + "件的商品，仓库还剩" + warehouse.stock);
                next = random.nextInt(100) + 1;
            } else {
                System.out.println("仓库货不足，需要" + next + "，只有" + warehouse.stock);
            }
            warehouse.lock.unlock();
        }
    }
}
