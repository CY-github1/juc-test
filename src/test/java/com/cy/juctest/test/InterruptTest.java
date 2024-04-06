package com.cy.juctest.test;

import java.util.concurrent.locks.LockSupport;

public class InterruptTest {
    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            Integer count = 5;
            @Override
            public void run() {
                while (count-- > 0) {
                    try {
//                        LockSupport.park();
                        System.out.println(Thread.currentThread().isInterrupted());
                        System.out.println(Thread.interrupted());
//                        wait(500);
                        System.out.println("nihao");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, "AA");
        t1.start();
        t1.interrupt();

        Thread t2 = new Thread(new Runnable() {
            Integer count = 5;

            @Override
            public void run() {
                while (count-- > 0) {
                    try {
                        Thread.sleep(50);
                        System.out.println("nihao");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, "BB");
        t2.start();

    }
}
