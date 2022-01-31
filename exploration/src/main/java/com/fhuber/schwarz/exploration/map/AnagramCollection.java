package com.fhuber.schwarz.exploration.map;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnagramCollection {

    String key;
    List<Anagram> anagrams = new ArrayList<>();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((anagrams == null) ? 0 : anagrams.hashCode());
        result = prime * result + ((key == null) ? 0 : key.hashCode());
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
        AnagramCollection other = (AnagramCollection) obj;
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

    public List<Anagram> getAnagrams() {
        return anagrams;
    }

    public void setAnagrams(List<Anagram> anagrams) {
        this.anagrams = anagrams;
    }

    public String getAnagramsAsString() {
        return anagrams.stream().map(Anagram::getWord).collect(Collectors.joining(" "));
    }

    @Override
    public String toString() {
        return "AnagramCollection [key=" + key + ", anagrams=" + anagrams + "]";
    }
    
}
