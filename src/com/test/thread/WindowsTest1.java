package com.test.thread;


/**
 *
 * 窗口卖票 Runnable
 * 这种implements可以多实现或继承其他类
 *
 */
class Windows1 implements Runnable {

    /**
     * 有线程安全问题,待解决
     * ticket 100张
     */
    private int ticket = 100;

    @Override
    public void run() {

        while (true) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "卖票 " + ticket);
                ticket--;
            } else {
                System.out.println("票卖完了");
                break;
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
public class WindowsTest1 {
    public static void main(String[] args) {

        //此处只创建了一个Windows对象，所以ticket不用static
        Windows1 windows = new Windows1();

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
