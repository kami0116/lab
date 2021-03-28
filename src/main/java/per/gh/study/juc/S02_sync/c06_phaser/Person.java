package per.gh.study.juc.S02_sync.c06_phaser;


import java.util.Random;

public class Person extends Thread {
    private Random random;
    private String name;
    private MarriagePhaser phaser;

    public Person(String name, MarriagePhaser phaser) {
        random = new Random();
        this.name = name;
        this.phaser = phaser;
//        System.out.println(name + " 注册了phaser: " + phaser.register());
    }

    public void arrived() {
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " 到达了...");
        phaser.arriveAndAwaitAdvance();
    }

    public void eat() {
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " 吃完了...");
        phaser.arriveAndAwaitAdvance();

    }

    public void leave() {
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if ("新郎".equals(name) || "新娘".equals(name)) {
            phaser.arriveAndAwaitAdvance();
        } else {
            System.out.println(name + " 离开了...");
            phaser.arriveAndDeregister();
        }

    }

    public void sleep() {
        if ("新郎".equals(name) || "新娘".equals(name)) {
            System.out.println(name + " 入洞房了...");
            phaser.arriveAndAwaitAdvance();
        }
    }

    @Override
    public void run() {
        arrived();

        eat();

        leave();

        sleep();
    }
}
