package com.test.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 实现Callable方式
 * @author hsir
 * @ClassName ThreadTest4
 * @date 2021-05-16 19:52
 */
public class ThreadTest4 {
    public static void main(String[] args) {

        Thread4 thread = new Thread4();

        // 借助FutureTask类实现，将实现Callable的类传入FutureTask构造器
        FutureTask futureTask = new FutureTask(thread);

        // 将FutureTask对象传入Thread类启动
        new Thread(futureTask).start();

        try {
            // 调用get获取返回值
            Object sum = futureTask.get();
            System.out.println("返回值总和：" + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // futureTask.run();

    }
}


/**
 * 线程创建方法三：实现Callable
 * 可以有返回值
 * 可以抛异常
 */
class Thread4 implements Callable {

    /**
     * 打印奇数计算总和
     * @return
     * @throws Exception
     */
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 1) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}