package com.fhuber.schwarz.solution.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import com.fhuber.schwarz.solution.model.Anagram;
import com.fhuber.schwarz.solution.model.AnagramContainer;
import com.fhuber.schwarz.solution.service.AnagramStorageService;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AnagramMapStorageService implements AnagramStorageService {
    private static Map<String, AnagramContainer> anaMap = new ConcurrentHashMap<>();

    public void save(Anagram anagram) {
        String key = anagram.getKey();
        anaMap.computeIfAbsent(key, k -> new AnagramContainer()).getAnagrams().add(anagram);
    }

    public Stream<AnagramContainer> getAnagrams() {
        return anaMap.values().stream().filter(value -> value != null && value.getAnagrams().size() > 1);
    }
}
