package com.cy.juctest.test;

import java.io.Serializable;

public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 票的数量
     */
    private Integer ticketCount = 100;
    /**
     * 票剩余数量
     */
    private Integer saleCount = 0;


    /**
     * 售票
     *
     * @author CY
     * @date 2024/03/30
     */
    public synchronized void sale(){
        if(ticketCount > 0){
            System.out.println("线程【" + Thread.currentThread().getName() +"】出售第：" + ++saleCount + " 张票，剩余：" + --ticketCount + " 张票" );
        }
    }
}
