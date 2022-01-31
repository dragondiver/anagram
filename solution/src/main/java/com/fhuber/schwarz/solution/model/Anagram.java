package com.fhuber.schwarz.solution.model;

import java.util.Arrays;
/**
 * the name is not quit correct, but i cannot find any better
 * this object can create a key build out of a sorted string of the underlying "word" string
 */
public class Anagram {
    private String word;

    public Anagram(String word) {
        this.word = word;
    }

    
    
    /** 
     * @return String
     */
    public String getWord() {
        return word;
    }


    
    /** 
     * this key is build from the word reduced to alphanumeric characters and sorted, thus all anagrams will have the same key
     * @return String
     */
    public String getKey() {
        char[] c = word.toLowerCase().replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}]", "").toCharArray();
        Arrays.sort(c);
        return new String(c);
    }


    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((word == null) ? 0 : word.hashCode());
        return result;
    }


    
    /** 
     * @param obj
     * @return boolean
     */
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


    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Anagram [word=" + word + "]";
    }
    
    
}
