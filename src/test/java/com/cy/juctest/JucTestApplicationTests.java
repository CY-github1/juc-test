package com.cy.juctest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JucTestApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("mysql".charAt(0) + 1);
    }

}
