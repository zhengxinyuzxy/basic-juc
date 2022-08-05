package com.zhengxinyu;

import java.util.concurrent.TimeUnit;

/**
 * @author Ian Zheng
 * @date 2022/07/26 15:06
 */
public class ThreadSynchronizedSleep {

    public static void main(String[] args) {
        // 创建锁对象
        Object lock = new Object();

        // thread a
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " get lock");
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName() + " sleep end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " release lock");
            }
            System.out.println(Thread.currentThread().getName() + " over");
        }, "thread a").start();

        // 保证thread a先启动
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // thread b
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " begin");
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " get lock");
            }
            System.out.println(Thread.currentThread().getName() + " end");
        }, "thread b").start();

    }
}
