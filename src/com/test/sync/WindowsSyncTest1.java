package com.test.sync;


/**
 *
 * 窗口卖票
 * extends 了 Thread 则不能继承其他类，所以不推荐用这种
 *
 */
class Windows1 extends Thread {

    /**
     * 解决线程安全问题
     * 此处得为static否则回创建多个
     * ticket 100张
     */
    private static int ticket = 100;

    @Override
    public void run() {

        while (true) {
            // 继承Thread 可以用类.class充当锁，但是不能用this
            synchronized (Windows1.class) {
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (ticket > 0) {
                    System.out.println(getName() + "卖票 " + ticket);
                    ticket--;
                } else {
                    System.out.println(getName() + "票卖完了");
                    break;
                }
            }
        }
    }
}


/**
 * 卖票测试 Thread
 * @author hsir
 * @ClassName WindowsSyncTest1
 * @date 2021-05-13 22:29
 */
public class WindowsSyncTest1 {
    public static void main(String[] args) {
        //此处创建了三个对象，所以ticket得为static
        Windows1 t1 = new Windows1();
        Windows1 t2 = new Windows1();
        Windows1 t3 = new Windows1();

        t1.setName("线程1");
        t2.setName("线程2");
        t3.setName("线程3");

        t1.start();
        t2.start();
        t3.start();
    }
}
