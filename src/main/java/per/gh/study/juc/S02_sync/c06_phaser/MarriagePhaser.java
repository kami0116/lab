package per.gh.study.juc.S02_sync.c06_phaser;

import java.util.concurrent.Phaser;

public class MarriagePhaser extends Phaser {
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {

        switch (phase) {
            case 0:
                System.out.println("所有人都到齐了" + registeredParties);
                System.out.println();
                return false;
            case 1:
                System.out.println("所有人都吃完了" + registeredParties);
                System.out.println();
                return false;
            case 2:
                System.out.println("宾客们都离开了" + registeredParties);
                System.out.println();
                return false;
            case 3:
                System.out.println("新郎新娘入完洞房了" + registeredParties);
                System.out.println();
                return true;
            default:
                return true;

        }
    }
}
