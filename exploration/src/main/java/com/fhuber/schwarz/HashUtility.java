package com.fhuber.schwarz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class is based on a piece of code i found in stackoverflow.
 * I had to fix it and adjust to my needs
 * 
 * @author Florian Huber
 */
class HashUtility {
    private int n;
    private Map<Character, Integer> primeMap;

    public HashUtility(int n) {
        this.n = n;
        this.primeMap = new HashMap<>();
        constructPrimeMap();
    }

    /**
     * Utility to check if the passed {@code number} is a prime.
     *
     * @param number The number which is checked to be prime.
     * @return {@link boolean} value representing the prime nature of the number.
     */
    boolean isPrime(int number) {
        // Corner case
        if (number <= 1)
            return false;

        // Check from 2 to n-1
        for (int i = 2; i < number; i++)
            if (number % i == 0)
                return false;

        return true;
    }

    /**
     * Maps all first {@code n} primes to the letters of the given language.
     */
    private void constructPrimeMap() {
        List<Integer> primes = IntStream.range(2, Integer.MAX_VALUE)
                .filter(this::isPrime)
                .limit(this.n) // Limit the number of primes here
                .boxed()
                .collect(Collectors.toList());

        int curAlphabet = 0;
        for (int i : primes) {
            this.primeMap.put((char) ('a' + curAlphabet++), i);
        }
    }

    /**
     * We calculate the hashcode of a word by calculating the product of each
     * character mapping prime. This works since
     * the product of 2 primes is unique from the products of any other primes.
     * <p>
     * Since the hashcode can be huge, we return it modulo a large prime.
     *
     * @param word The {@link String} to be hashed. str.replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}]", "");
     * @return {@link int} representing the prime hashcode associated with the
     *         {@code word}
     */
    public String hashCode(String word) {
        try {
            long primeProduct = 1;
            long mod = 100000007;
            for (char currentCharacter : word.toCharArray()) {
                primeProduct *= this.primeMap.get(currentCharacter) % mod;
            }

            return Long.toString(primeProduct);
        } catch (Exception e) {
            System.out.println("Error in word: " + word);
            throw e;
        }
    }
}
