package com.cy.juctest.test;

import java.util.concurrent.*;

public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        test1();
//        test2();
        test3();
    }

    private static void test3() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        System.out.println("开始执行。。。");
        long start = System.currentTimeMillis();
        FutureTask<String> futureTask1 = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(5);
                return "线程:【" + Thread.currentThread().getName() + "】执行task1";
            }
        });
        threadPool.submit(new Thread(futureTask1,"AA"));

//        FutureTask<String> futureTask2 = new FutureTask<String>(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                TimeUnit.SECONDS.sleep(2);
//                return "线程:【" + Thread.currentThread().getName() + "】执行task2";
//            }
//        });
//        threadPool.submit(new Thread(futureTask2,"BB"));


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        int count = 5;
        while (true){
            if(futureTask1.isDone()){
                System.out.println(count);
                System.out.println(futureTask1.get());
                break;
            }else if (count > 0){
                System.out.println(count--);
                System.out.println("休息一会...");
                TimeUnit.MILLISECONDS.sleep(500);
            }else {
                throw new TimeoutException();
            }
        }
        System.out.println("--------------");
//        System.out.println(futureTask2.get());


        long end = System.currentTimeMillis();
        System.out.println("花费时长:\t" + (end - start) + "ms" );
        System.out.println("执行结束。。。");

        threadPool.shutdown();
    }

    private static void test2() {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        System.out.println("开始执行。。。");
        long start = System.currentTimeMillis();
        threadPool.submit(new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"AA"));

        threadPool.submit(new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"BB"));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long end = System.currentTimeMillis();
        System.out.println("花费时长:\t" + (end - start) + "ms" );
        System.out.println("执行结束。。。");

        threadPool.shutdown();
    }

    private static void test1() {

        System.out.println("开始执行。。。");
        long start = System.currentTimeMillis();

        try {
            Thread.sleep(1000);
            Thread.sleep(1000);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long end = System.currentTimeMillis();
        System.out.println("花费时长:\t" + (end - start) + "ms" );
        System.out.println("执行结束。。。");
    }
}
