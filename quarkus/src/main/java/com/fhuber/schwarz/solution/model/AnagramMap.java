package com.fhuber.schwarz.solution.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import javax.enterprise.context.RequestScoped;

/**
 * wraps ConcurrentHashMap to better support Anagram storage<p>
 * 
 * eg the put creates a new Anagram autmatically if absent
 * @see Anagram
 * 
 * the method getAnagrams does the check if there are more then two words in the Anagram
 * @see Anagram.hasAnagrams
 */
@RequestScoped
public class AnagramMap {
    private Map<String, Anagram> anaMap = new ConcurrentHashMap<>();

    
    /** 
     * @return Map<String, Anagram>
     */
    public Map<String, Anagram> getAnaMap() {
        return anaMap;
    }

    
    /** 
     * @param anaMap
     */
    public void setAnaMap(Map<String, Anagram> anaMap) {
        this.anaMap = anaMap;
    }
    
    
    /** 
     * @param key
     * @param word
     */
    public void put(String key, Word word) {
        anaMap.computeIfAbsent(key, k -> new Anagram()).getAnagrams().add(word);
    }

    
    /** 
     * @return Stream<Anagram>
     */
    public Stream<Anagram> getAnagrams() {
        return anaMap.values().stream().filter(value -> value != null && value.hasAnagrams());
    }

}
