package com.test.thread;

/**
 * 测试join
 * @author hsir
 * @ClassName TestJoin
 * @date 2021-05-16 22:53
 */
public class TestJoin {
    public static void main(String[] args) {

        T1 t1 = new T1();
        T2 t2 = new T2();
        t1.setName("T1 ");
        t2.setName("T2 ");

        t1.start();
        t2.start();
        try {
            // 休息10ms，暂停t1,阻塞
            Thread.sleep(1);
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


class T1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + i);
            if (i == 50) {
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class T2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println(getName() + i);
        }
    }
}