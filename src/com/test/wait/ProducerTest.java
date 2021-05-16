package com.test.wait;

/**
 *
 * 生产者消费者练习
 * @author hsir
 * @ClassName ProducerTest
 * @date 2021-05-15 16:40
 */
public class ProducerTest {
    public static void main(String[] args) {

        Product p = new Product();

        Producer p1 = new Producer(p);
        Consumer c1 = new Consumer(p);

        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(c1);

        t1.start();
        t2.start();

    }
}


/**
 * 定义一个机器用于生产和消费产品
 */
class Product {

    /**
     * 初始化库存
     */
    private int store = 0;


    /**
     * 生产产品
     */
    synchronized void produceProduct() {

        if (store < 20) {
            store++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "生产产品，已生产：" + store);
            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 消费产品
     */
    synchronized void consumeProduct() {

        if (store > 0) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "消费产品，当时有：" + store);
            store--;
            notify();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

}

/**
 * 生产者
 */
class Producer implements Runnable {

    private Product product;

    Producer(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        while (true) {
            product.produceProduct();
        }
    }
}

/**
 * 消费者
 */
class Consumer implements Runnable {

    private Product product;

    Consumer(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        while (true) {
            product.consumeProduct();
        }
    }
}