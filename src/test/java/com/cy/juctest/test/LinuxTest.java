package com.cy.juctest.test;

public class LinuxTest {
    public static void main(String[] args) {

        new Thread(new Runnable() {
            Integer count = 5;
            @Override
            public void run() {
                while (count-- > 0){
                    try {
                        Thread.sleep(500);
                        System.out.println("nihao");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        },"AA").start();

        new Thread(new Runnable() {
            Integer count = 5;
            @Override
            public void run() {
                while (count-- > 0){
                    try {
                        Thread.sleep(500);
                        System.out.println("nihao");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        },"BB").start();

    }
}
