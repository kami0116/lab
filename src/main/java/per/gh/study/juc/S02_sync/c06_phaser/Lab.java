package per.gh.study.juc.S02_sync.c06_phaser;

public class Lab {
    public static void main(String[] args) {
        MarriagePhaser marriagePhaser = new MarriagePhaser();
        marriagePhaser.bulkRegister(7);
        new Person("刘德华", marriagePhaser).start();
        new Person("刘若英", marriagePhaser).start();
        new Person("周慧敏", marriagePhaser).start();
        new Person("成龙", marriagePhaser).start();
        new Person("梁朝伟", marriagePhaser).start();
        new Person("新郎", marriagePhaser).start();
        new Person("新娘", marriagePhaser).start();
    }
}
