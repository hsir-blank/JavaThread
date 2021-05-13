package com.test.thread;


/**
 *
 * 窗口卖票
 * extends 了 Thread 则不能继承其他类，所以不推荐用这种
 *
 */
class Windows extends Thread {

    /**
     * 有线程安全问题，待解决
     * 此处得为static否则回创建多个
     * ticket 100张
     */
    private static int ticket = 100;

    @Override
    public void run() {

        while (true) {
            if (ticket > 0) {
                System.out.println(getName() + "卖票 " + ticket);
                ticket--;
            } else {
                System.out.println("票卖完了");
                break;
            }
        }
    }
}



/**
 * 卖票测试 Thread
 * @author hsir
 * @ClassName WindowsTest
 * @date 2021-05-13 22:29
 */
public class WindowsTest {
    public static void main(String[] args) {
        //此处创建了三个对象，所以ticket得为static
        Windows t1 = new Windows();
        Windows t2 = new Windows();
        Windows t3 = new Windows();

        t1.setName("线程1");
        t2.setName("线程2");
        t3.setName("线程3");

        t1.start();
        t2.start();
        t3.start();
    }
}
