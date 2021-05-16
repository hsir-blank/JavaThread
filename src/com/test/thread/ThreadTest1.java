package com.test.thread;

/**
 * 创建线程方法一：继承Thread
 * 1：继承Thread
 * 2：重写run方法
 * 3：创建子类
 * 4：start
 *
 * @author hsir
 */

class ThreadTest extends Thread {

    @Override
    public void run() {

        /*
          1：获取当前线程的优先级
          2：最大10
          3：最小1
          4：默认5
         */
        System.out.println(MAX_PRIORITY);
        System.out.println(MIN_PRIORITY);
        System.out.println(currentThread().getPriority());
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }

            if (i % 20 == 0) {

                // 交出当前cpu
                yield();
            }

            if (i % 30 == 0) {
                // 中断当前线程，已弃用
                stop();
            }
        }
    }


}


/**
 * 启动线程
 * 1：start启动当前线程，调用当前线程的run
 * 2：重写Thread run
 *
 * @author hsir
 */
public class ThreadTest1 {
    public static void main(String[] args) {

        Thread t1 = new ThreadTest();
        // 启动当前线程，只能start一次
        t1.start();
        // 可以再创建一个线程
        Thread t2 = new ThreadTest();
        // t2.start();

        //如果用run就会顺序执行
        // t.run();

        ThreadTest1 threadTest1 = new ThreadTest1();
        threadTest1.thread1();
    }

    private void thread1() {

        ThreadTest threadTest = new ThreadTest();
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }

            if (i == 5) {
                try {
                    //join 阻塞当前线程，等待其他线程结束
                    threadTest.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
