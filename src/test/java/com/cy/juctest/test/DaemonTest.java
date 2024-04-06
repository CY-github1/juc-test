package com.cy.juctest.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class DaemonTest {
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("t1线程结束了");
                        break;
                    }
                }
            }
        }, "AA");
//        t1.setDaemon(true);
        t1.start();
        Thread.sleep(500);
        t1.interrupt();
        System.out.println(t1.isInterrupted());

        TimeUnit.SECONDS.sleep(2);

        System.out.println("主线程结束了");

        TimeUnit.SECONDS.sleep(2);

        t1.interrupt();


    }
}
