package com.zhengxinyu;

import java.util.concurrent.TimeUnit;

/**
 * @author Ian Zheng
 * @date 2022/07/27 9:43
 */
public class ThreadSynchronizedWait {

    public static void main(String[] args) {
        Object o = new Object();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + " get lock");
                try {
                    o.wait(5);
                    System.out.println(Thread.currentThread().getName() + " sleep over");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " release lock");
            }
            System.out.println(Thread.currentThread().getName() + " end");
        }, "thread-a").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + " get lock");
            }
            System.out.println(Thread.currentThread().getName() + " end");
        }, "thread-b").start();
    }
}
