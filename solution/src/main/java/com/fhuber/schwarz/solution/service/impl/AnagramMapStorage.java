package com.fhuber.schwarz.solution.service.impl;

import com.fhuber.schwarz.solution.model.AnagramMap;
import com.fhuber.schwarz.solution.model.Word;
import com.fhuber.schwarz.solution.service.AnagramStorage;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AnagramMapStorage implements AnagramStorage {

    @Inject
    AnagramMap anagramMap;

    public void save(Word word) {
        String key = word.getKey();
        anagramMap.put(key,word);
    }

    @Override
    public AnagramMap getAnagramMap() {
        return anagramMap;
    }

    
}
