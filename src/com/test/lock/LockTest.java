package com.test.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock
 *
 * @author hsir
 * @ClassName LockTest
 * @date 2021-05-14 19:42
 */
public class LockTest {

    public static void main(String[] args) {

        Windows w = new Windows();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("线程1");
        t2.setName("线程2");
        t3.setName("线程3");

        t1.start();
        t2.start();
        t3.start();
    }


}


class Windows implements Runnable {

    private int ticket = 100;

    /**
     * 使用lock
     * ReentrantLock 实现了 Lock
     */
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {


        while (true) {

            // 加锁
            lock.lock();
            try {
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "卖票：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            } finally {
                // 手动释放锁
                lock.unlock();
            }
        }

    }
}