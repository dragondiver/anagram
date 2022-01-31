package com.fhuber.schwarz.solution.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * These objects will carry Words (class Anagram) if there are more than one
 * word, we have identified an anagram
 * 
 * 
 * @author Florian Huber
 */
public class Anagram {

    String key;
    List<Word> anagrams = new ArrayList<>();

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((anagrams == null) ? 0 : anagrams.hashCode());
        result = prime * result + ((key == null) ? 0 : key.hashCode());
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
        if (anagrams == null) {
            if (other.anagrams != null)
                return false;
        } else if (!anagrams.equals(other.anagrams))
            return false;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        return true;
    }

    /**
     * get the collected words
     * 
     * @return List<Anagram>
     */
    public List<Word> getAnagrams() {
        return anagrams;
    }

    /**
     * set a new list of anagrams
     * 
     * @param anagrams
     */
    public void setAnagrams(List<Word> anagrams) {
        this.anagrams = anagrams;
    }

    /**
     * true if we really found anagrams
     * 
     * @return boolean
     */
    public boolean hasAnagrams() {
        return getAnagrams().size() > 1;
    }

    /**
     * return a String, that has all anagram words separated by a space
     * 
     * @return String
     */
    public String getAnagramsAsString() {
        return anagrams.stream().map(Word::getWord).collect(Collectors.joining(" "));
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "AnagramCollection [key=" + key + ", anagrams=" + anagrams + "]";
    }

}
