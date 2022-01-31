package com.fhuber.schwarz.solution.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class AnagramMap {
    private Map<String, Anagram> anaMap = new ConcurrentHashMap<>();

    public Map<String, Anagram> getAnaMap() {
        return anaMap;
    }

    public void setAnaMap(Map<String, Anagram> anaMap) {
        this.anaMap = anaMap;
    }
    
    public void put(String key, Word word) {
        anaMap.computeIfAbsent(key, k -> new Anagram()).getAnagrams().add(word);
    }

    public Stream<Anagram> getAnagrams() {
        return anaMap.values().stream().filter(value -> value != null && value.hasAnagrams());
    }

}
