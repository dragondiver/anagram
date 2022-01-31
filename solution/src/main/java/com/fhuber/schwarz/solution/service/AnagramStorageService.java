package com.fhuber.schwarz.solution.service;

import java.util.stream.Stream;

import com.fhuber.schwarz.solution.model.Anagram;
import com.fhuber.schwarz.solution.model.AnagramContainer;

public interface AnagramStorageService {

    public void save(Anagram anagram);

    public Stream<AnagramContainer> getAnagrams();
}
