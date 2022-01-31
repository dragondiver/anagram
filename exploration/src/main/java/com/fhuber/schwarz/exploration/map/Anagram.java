package com.fhuber.schwarz.exploration.map;

import java.util.Arrays;

public class Anagram {
    private String word;

    public Anagram(String word) {
        this.word = word;
    }

    
    public String getWord() {
        return word;
    }


    public String getKey() {
        char[] c = word.toLowerCase().replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}]", "").toCharArray();
        Arrays.sort(c);
        return new String(c);
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((word == null) ? 0 : word.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Anagram other = (Anagram) obj;
        if (word == null) {
            if (other.word != null)
                return false;
        } else if (!word.equals(other.word))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "Anagram [word=" + word + "]";
    }
    
    
}
