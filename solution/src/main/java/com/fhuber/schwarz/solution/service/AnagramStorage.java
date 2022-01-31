package com.fhuber.schwarz.solution.service;

import com.fhuber.schwarz.solution.model.AnagramMap;
import com.fhuber.schwarz.solution.model.Word;

public interface AnagramStorage {

    public void save(Word anagram);

    public AnagramMap getAnagramMap();
}
