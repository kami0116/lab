package per.gh.study.juc.S02_sync.spinlock;

public class Factory extends Thread {
    private Warehouse warehouse;

    public Factory(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (true) {
            warehouse.lock.lock();
            if (warehouse.stock < Warehouse.MAXSIZE) {
                warehouse.stock++;
                System.out.println("工厂生产了1件的商品, 仓库现有" + warehouse.stock);
            } else {
                System.out.println("仓库已满，啥也没生产");
            }
            warehouse.lock.unlock();


        }
    }
}
