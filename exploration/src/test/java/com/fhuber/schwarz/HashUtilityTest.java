package com.fhuber.schwarz;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HashUtilityTest {
    
    static HashUtility hashu;

    @BeforeAll
    static void setup() {
        hashu = new HashUtility(30);
    }

    @Test
    void checkPrime() {
        assertTrue(hashu.isPrime(3));
    }
}
