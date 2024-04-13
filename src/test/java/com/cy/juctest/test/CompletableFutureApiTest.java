package com.cy.juctest.test;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Slf4j
public class CompletableFutureApiTest {
    public static void main(String[] args) {
//        test1();
        test2();

    }

    private static void test2() {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
                System.out.println("执行1");
                return 1;
            },threadPool).handle((f,e) -> {
                int i = 1/0;
                System.out.println("执行2");
                return f + 2;
            }).handle((f,e) -> {
                System.out.println("上一步结果为："  + f);
                log.error("错误结果为：{}",JSON.toJSONString(e));
                System.out.println("执行3");
                return f + 3;
            }).whenComplete((r, e) -> {
                System.out.println("上一步结果为："  + r);
                log.error("错误结果为：{}",JSON.toJSONString(e));
                if (e == null) {
                    System.out.println(r);
                }
            }).exceptionally((e) -> {
                e.printStackTrace();
                return null;
            });
            System.out.println(completableFuture.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.shutdown();
        }
    }

    private static void test1() {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
                System.out.println("执行1");
                return 1;
            },threadPool).thenApply((f) -> {
                System.out.println("执行2");
                return f + 2;
            }).thenApply((f) -> {
                System.out.println("执行3");
                return f + 3;
            }).whenComplete((r, e) -> {
                if (e == null) {
                    System.out.println(r);
                }
            }).exceptionally((e) -> {
                e.printStackTrace();
                return null;
            });
            System.out.println(completableFuture.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.shutdown();
        }
    }
}
