package com.test.lock;

/**
 * 死锁的案例，相互持有
 * @author hsir
 * @ClassName ThreadLock
 * @date 2021-05-14 18:57
 */
public class ThreadLock {
    public static void main(String[] args) {

        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();


        new Thread() {
            @Override
            public void run() {
                synchronized (s1) {
                    s1.append("a");
                    s2.append(1);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (s2) {
                        s1.append("b");
                        s2.append(2);
                        System.out.println("THREAD " + s1);
                        System.out.println("THREAD " + s2);
                    }
                }
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (s2) {
                    s1.append("c");
                    s2.append(3);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (s1) {
                        s1.append("d");
                        s2.append(4);
                        System.out.println("RUN " + s1);
                        System.out.println("RUN " + s2);
                    }
                }
            }
        }).start();

    }
}
