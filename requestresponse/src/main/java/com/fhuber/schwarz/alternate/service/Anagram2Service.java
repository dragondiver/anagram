package com.fhuber.schwarz.alternate.service;

import java.util.stream.Stream;

import com.fhuber.schwarz.solution.model.Anagram;

public interface Anagram2Service {
    public Stream<Anagram> process(String identifier);
}
