package com.test.wait;

/**
 * 测试wait
 * @author hsir
 * @ClassName WaitTesy
 * @date 2021-05-15 14:49
 */
public class WaitTest {
    public static void main(String[] args) {
        PrintNumber number = new PrintNumber();

        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);

        t1.start();
        t2.start();
    }
}


/**
 * 交替打印 0-100
 */
class PrintNumber implements Runnable {

    private int num = 1;

    @Override
    public void run() {

        while (true) {
            synchronized (this) {
                // 获得锁后唤醒阻塞线程
                notify();
                if (num < 100) {
                    System.out.println(Thread.currentThread().getName() + "打印：" + num);
                    num++;

                    try {
                        // 打印完让其阻塞wait
                        // 调用wait释放同步监视器（this），其他线程可以拿到锁
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }

            }
        }

    }
}
