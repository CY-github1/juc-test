package com.cy.juctest.test;

import cn.hutool.core.util.RandomUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CompletableFutureDemo {

    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("dangdang"),
            new NetMall("taobao"),
            new NetMall("pdd"),
            new NetMall("tmall")
    );

    public static void main(String[] args) {
        System.out.println("开始执行。。。");
        long start = System.currentTimeMillis();
        for (String item : getPriceStringByCommon(list, "mysql")) {
            System.out.println(item);
        }
        long end = System.currentTimeMillis();
        System.out.println("花费时长:\t" + (end - start) + "ms" );
        System.out.println("执行结束。。。");

        System.out.println("开始执行。。。");
        long start1 = System.currentTimeMillis();
        for (String item : getPriceStringByCompetableFuture(list, "mysql")) {
            System.out.println(item);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("花费时长:\t" + (end1 - start1) + "ms" );
        System.out.println("执行结束。。。");
    }

    public static List<String> getPriceStringByCompetableFuture(List<NetMall> list, String goodsName) {
        return list.stream().map(item -> CompletableFuture.supplyAsync(() -> String.format(goodsName + " in %s price is %.2f",
                item.getMallName(),
                item.getPrice(goodsName))))
                .toList()
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public static List<String> getPriceStringByCommon(List<NetMall> list,String goodsName){
        return list.stream().map(item -> {
            return String.format(goodsName + " in %s price is %.2f",
                    item.getMallName(),
                    item.getPrice(goodsName));
        }).collect(Collectors.toList());
    }


}

@Data
class NetMall{

    private String mallName;

    private BigDecimal price;

    public NetMall(String mallName) {
        this.mallName = mallName;
    }

    public BigDecimal getPrice(String goodsName){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        BigDecimal bigDecimal = (RandomUtil.randomBigDecimal(BigDecimal.valueOf(100))).add(new BigDecimal(goodsName.charAt(0)));
        return bigDecimal;
    }
}
