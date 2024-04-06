package com.cy.juctest.test;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.*;

@Slf4j
public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test1();
        test2();
    }

    private static void test2() throws InterruptedException, ExecutionException {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try {
            CompletableFuture.supplyAsync(() -> {
                System.out.println("线程【" + Thread.currentThread().getName() + "】执行有返回值异步线程");
                String result = UUID.randomUUID().toString();
                int randomNumber = RandomUtil.randomInt(10);
                System.out.println("产生的随机数为：" + randomNumber);
                if(randomNumber > 3){
                    int e = randomNumber / 0;
                }
                return result;
            },threadPool).whenComplete((r,e)->{
                log.info("上一步执行结果为：{}，异常信息为：{}",JSON.toJSONString(r),JSON.toJSONString(e));
                if(e == null){
                    System.out.println("上一步执行结果为：" + r);
                }
            }).exceptionally((e)->{
                System.out.println(e);
                log.error("出异常了：{}", JSON.toJSONString(e));
                return "出异常了";
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.shutdown();
        }
    }

    private static void test1() throws InterruptedException, ExecutionException {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("线程【" + Thread.currentThread().getName() + "】执行无返回值异步线程");
        });
        System.out.println(voidCompletableFuture.get());

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("线程【" + Thread.currentThread().getName() + "】执行有返回值异步线程");
            return "hello supplyAsync";
        });

        System.out.println(stringCompletableFuture.get());
    }
}
