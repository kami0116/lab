package per.gh.study.juc.S02_sync.c07_exchanger;


public class MyExchanger<T> {
    T data = null;
    volatile int count = 0;

    public T exchange(T t) {
        synchronized (this) {
            count++;
            if (data == null) {
                data = t;
            } else {
                T tmp = data;
                data = t;
                return tmp;
            }
        }
        while (count != 2) ;
        synchronized (this) {
            return data;
        }
    }

    public static void main(String[] args) {
        MyExchanger<String> exchanger = new MyExchanger<>();

        new Thread(() -> {
            String s = "t1";
            s = exchanger.exchange(s);

            System.out.println(Thread.currentThread().getName() + " " + s);

        }, "T1").start();

        new Thread(() -> {
            String s = "t2";
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            s = exchanger.exchange(s);
            System.out.println(Thread.currentThread().getName() + " " + s);

        }, "T2").start();
    }
}
