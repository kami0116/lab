package per.gh.study.juc.problems;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 写一个固定容量同步器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 */
public class P2_container_for_producer_and_consumer {
    Thread[] producers;
    Thread[] consumers;
    int producerCount = 2;
    int consumerCount = 10;
    MySyncContainer c;

    @Before
    public void init() {
        producers = new Thread[producerCount];
        consumers = new Thread[consumerCount];
    }

    @Test
    public void test_MySyncContainer1() {
        c = new MySyncContainer1(100);
    }

    @Test
    public void test_MySyncContainer2() {
        c = new MySyncContainer2(100);
    }

    @After
    public void test() {
        //生产者
        for (int i = 0; i < producerCount; i++) {
            producers[i] = new Thread(() -> {
                Random r = new Random();
                for (int j = 0; j < 20 * consumerCount; j++) {
                    c.put(r.nextInt(100));
                }
            }, "生产者" + i);
        }
        //消费者
        for (int i = 0; i < consumerCount; i++) {
            consumers[i] = new Thread(() -> {
                for (int j = 0; j < 20 * producerCount; j++) {
                    c.get();
                }
            }, "消费者" + i);
        }

        Arrays.stream(producers).forEach(Thread::start);
        Arrays.stream(consumers).forEach(Thread::start);
        Arrays.stream(producers).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Arrays.stream(consumers).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    interface MySyncContainer {
        Object get();

        void put(Object o);

        int getCount();
    }

    class MySyncContainer1 implements MySyncContainer {
        private final Object[] array;
        private final int maxSize;
        private int size;


        public MySyncContainer1(int maxSize) {
            size = 0;
            this.maxSize = maxSize;
            array = new Object[maxSize];
        }

        public synchronized Object get() {
            while (size == 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Object o = array[--size];
            System.out.println(Thread.currentThread().getName() + "\t:\tget " + o + "\tsize: " + size);
            this.notifyAll();
            return o;
        }

        public synchronized void put(Object o) {
            while (size == maxSize) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            array[size++] = o;
            System.out.println(Thread.currentThread().getName() + "\t:\tput " + o + "\tsize: " + size);
            this.notifyAll();
        }

        public synchronized int getCount() {
            return size;
        }
    }

    class MySyncContainer2 implements MySyncContainer {
        private final Object[] array;
        private final int maxSize;
        private int size;
        private Lock lock = new ReentrantLock();
        private Condition producer = lock.newCondition();
        private Condition consumer = lock.newCondition();

        public MySyncContainer2(int maxSize) {
            size = 0;
            this.maxSize = maxSize;
            array = new Object[maxSize];
        }

        public Object get() {
            try {
                lock.lock();
                while (size == 0) {
                    consumer.await();
                }
                Object o = array[--size];
                System.out.println(Thread.currentThread().getName() + "\t:\tget " + o + "\tsize: " + size);
                producer.signalAll();
                return o;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            throw new RuntimeException();
        }

        public void put(Object o) {
            try {
                lock.lock();
                while (size == maxSize) {
                    producer.await();
                }
                array[size++] = o;
                System.out.println(Thread.currentThread().getName() + "\t:\tput " + o + "\tsize: " + size);
                consumer.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        public synchronized int getCount() {
            return size;
        }
    }

}
