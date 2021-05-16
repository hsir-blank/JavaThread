package com.test.lock;

import java.util.concurrent.locks.ReentrantLock;

import static com.sun.tools.doclint.Entity.ne;
import static com.sun.tools.doclint.Entity.or;

/**
 * 一个账户练习，两个人往账户存钱
 *
 * @author hsir
 * @ClassName AccountTest
 * @date 2021-05-15 13:07
 */
public class AccountTest {

    public static void main(String[] args) {
        Account a = new Account(0);

        Customer c1 = new Customer(a);
        Customer c2 = new Customer(a);

        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);

        t1.start();
        t2.start();

    }
}

class Account {

    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    synchronized void saveMoney(double money) {
        if (money > 0) {
            balance += money;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "存钱：" + money + "余额：" + balance);
        }

    }

}


class Customer implements Runnable {

    private Account account;

    public Customer(Account account) {
        this.account = account;
    }


    @Override
    public void run() {

        for (int i = 0; i < 3; i++) {
            account.saveMoney(1000);
        }

    }
}