package com.cp.util;

import net.datafaker.providers.base.App;
import org.junit.jupiter.api.DisplayName;


import static org.junit.jupiter.api.Assertions.*;



class GenIdTest {



    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.AfterAll
    static void fin() {
        AppLog.getInstance().info("Final do teste em GenIdTest");
    }

    @DisplayName("um teste simples")
    @org.junit.jupiter.api.Test
    void getIdPrimo() {
        GenId geid = new GenId();
        assertTrue(isPrimo(geid.getIdPrimo()));
        assertTrue(isPrimo(geid.getIdPrimo()));
        assertTrue(isPrimo(geid.getIdPrimo()));
        assertTrue(isPrimo(geid.getIdPrimo()));
        assertFalse(isPrimo(4));
        assertFalse(isPrimo(26));
        assertFalse(isPrimo(150));

        AppLog.getInstance().info("Fim do teste em getIdPrimo");

    }

    private boolean isPrimo(int num) {
        for(int i = num-1; i > 1; i--) {
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }
}