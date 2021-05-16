package com.test.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用线程池
 * @author hsir
 * @ClassName ThreadPool
 * @date 2021-05-16 20:26
 */
public class ThreadPool {
    public static void main(String[] args) {

        Thread5 thread5 = new Thread5();

        /**
         * 推荐使用EThreadPoolExecutor创建线程池
         * CorePoolSize: 核心池的大小
         * MaximumPoolSize: 最大线程数
         * KeepAliveTime: 线程没有任务时最多保持多长时间后会终止
         */
        /*ThreadFactory namedThreadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        };
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());*/

        // 使用Executors创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //推荐使用EThreadPoolExecutor创建线程池
        ThreadPoolExecutor service = (ThreadPoolExecutor) executorService;
        // 核心池的大小
        service.setCorePoolSize(10);
        // 最大线程数
        service.setMaximumPoolSize(20);
        // 线程没有任务时最多保持多长时间后会终止
        service.setKeepAliveTime(1000L, TimeUnit.MILLISECONDS);

        // execute 适用于实现Runnable接口的线程
        executorService.execute(thread5);

        // submit 适用于实现Callable接口的线程
        // 此种方式可以带返回值，使用FutureTask 接收
        Future res = executorService.submit(thread5);
        try {
            Object o = res.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 关闭线程池
        executorService.shutdown();

    }
}


class Thread5 implements Runnable {

    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + i);
            }
        }
    }
}