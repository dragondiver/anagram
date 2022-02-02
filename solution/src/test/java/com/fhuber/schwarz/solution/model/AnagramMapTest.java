package com.fhuber.schwarz.solution.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AnagramMapTest {

    @Test
    public void shouldContainWord() {
        AnagramMap map = new AnagramMap();
        map.put("act", new Word("cat"));
        assertTrue(map.getAnaMap().containsKey("act"));
    }

    @Test
    public void shouldStreamAnagrams() {
        AnagramMap map = new AnagramMap();
        map.put("act", new Word("cat"));
        map.put("act", new Word("tac"));
        assertTrue(map.getAnagrams().allMatch(p -> p.getAnagramsAsString().equals("cat tac")));
    }
}
