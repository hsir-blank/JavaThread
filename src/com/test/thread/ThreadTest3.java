package com.test.thread;


/**
 * 线程创建方法二：实现Runnable
 * 重写run
 */
class Thread3 implements Runnable {

    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 1) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}


/**
 * 用实现Runnable实现多线程
 * @author hsir
 * @ClassName ThreadTest3
 * @date 2021-05-13 22:51
 */
public class ThreadTest3 {
    public static void main(String[] args) {

        Thread3 thread3 = new Thread3();

        // 将实现Runnable的类传入Thread
        Thread t1 = new Thread(thread3);
        t1.setName("Runnable");
        t1.start();
    }
}
