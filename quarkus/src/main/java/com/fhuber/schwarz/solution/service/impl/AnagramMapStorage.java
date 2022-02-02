package com.fhuber.schwarz.solution.service.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.fhuber.schwarz.solution.model.AnagramMap;
import com.fhuber.schwarz.solution.model.Word;
import com.fhuber.schwarz.solution.service.AnagramStorage;

@RequestScoped
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
