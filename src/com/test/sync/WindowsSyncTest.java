package com.test.sync;

/**
 *
 * 窗口卖票 Runnable
 * 这种implements可以多实现或继承其他类
 * 加synchronized，解决线程安全问题
 *
 */
class Windows implements Runnable {

    /**
     * ticket 100张
     */
    private int ticket = 100;

    Boolean flag = true;

    @Override
    public void run() {

        while (true) {
            // 加synchronize，flag必须为共同对象，多个线程共用一把锁
            // 不加synchronize会出现线程安全问题
            // 缺点：这样同步代码块相当于单线程
            // synchronized (flag) {
            synchronized (this) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "卖票 " + ticket);
                    ticket--;
                } else {
                    System.out.println(Thread.currentThread().getName()  + "票卖完了");
                    break;
                }
            }
        }
    }
}


/**
 * 卖票测试 Runnable
 * @author hsir
 * @ClassName WindowsTest1
 * @date 2021-05-13 22:29
 */
public class WindowsSyncTest {
    public static void main(String[] args) {

        //此处只创建了一个Windows对象，所以ticket不用static
        Windows windows = new Windows();

        Thread t1 = new Thread(windows);
        Thread t2 = new Thread(windows);
        Thread t3 = new Thread(windows);


        t1.setName("线程1");
        t2.setName("线程2");
        t3.setName("线程3");

        t1.start();
        t2.start();
        t3.start();
    }
}
