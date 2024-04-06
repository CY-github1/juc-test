package com.cy.juctest.test;


import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j(topic = "futuretask")
public class MyFutureTask {

    public static void main(String[] args) {
        FutureTask<Integer> task =  new FutureTask<Integer>(new Callable<Integer>(){
            @Override
            public Integer call() throws Exception {
                log.info("call方法执行了");
                System.out.println(Thread.currentThread().getName());
                return 100;
            }
        });

        new Thread(new FutureTask<Integer>(new Callable<Integer>(){
            @Override
            public Integer call() throws Exception {
                log.info("call方法执行了");
                System.out.println(Thread.currentThread().getName());
                return 100;
            }
        }),"BB").start();

        new Thread(task,"AA").start();

        try {
            Integer integer = task.get();
        } catch (Exception e) {
            log.error("错误结果为：{}", JSON.toJSONString(e));
        }
    }



}
