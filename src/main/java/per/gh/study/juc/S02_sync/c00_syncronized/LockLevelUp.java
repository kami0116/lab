package per.gh.study.juc.S02_sync.c00_syncronized;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * 通过查看被锁对象的头信息来查看锁的状态
 */
public class LockLevelUp {
    public static synchronized void  main(String[] args) throws Exception{
        Object lock =LockLevelUp.class;
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("C:\\Users\\guhao\\Desktop\\hello2.txt"));
        //1. 无锁状态
        oos.writeObject(lock);
        oos.close();
    }
}
