package com.fhuber.schwarz.hashkey;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HashUtilityTest {
    
    static HashUtility hashu;

    @BeforeAll
    static void setup() {
        hashu = new HashUtility();
    }

    @Test
    void checkPrime() {
        assertTrue(hashu.isPrime(3));
    }

    @Test
    void checkUmlaute() {
        String hash = hashu.hashCode("aalähnlich");
        System.out.println(hash);
        assertNotNull(hash);
    }
}
