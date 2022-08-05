package com.zhengxinyu;

import java.util.concurrent.TimeUnit;

/**
 * @author Ian Zheng
 * @date 2022/07/27 9:58
 */
public class Demo01SynchronizedMethod {

    public static void main
            (String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendMessage();
        }, "thread-a").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone.call();
        }, "thread-b").start();
    }


    static class Phone{
        public synchronized void sendMessage() {
            System.out.println(Thread.currentThread().getName() + " 发信息");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public synchronized void call() {
            System.out.println(Thread.currentThread().getName() + " 打电话");
        }
    }
}
