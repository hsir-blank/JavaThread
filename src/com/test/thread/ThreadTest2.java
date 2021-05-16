package com.test.thread;

/**
 * @author hsir
 */
public class ThreadTest2 {

    public static void main(String[] args) {


        //创建两线程,不用创建两类来实现
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + "： " + i);
                    }
                }
            }

        }.start();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 != 0) {
                        System.out.println(Thread.currentThread().getName() + "： " + i);
                    }
                }
            }

        }.start();
    }

}

