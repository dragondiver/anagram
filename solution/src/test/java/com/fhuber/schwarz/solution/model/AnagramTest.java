package com.fhuber.schwarz.solution.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class AnagramTest {
    @Test
    public void shouldHaveAnagrams() {
        Anagram a = new Anagram();
        a.setAnagrams(getWords());
        assertTrue(a.hasAnagrams());
    }

    @Test
    public void shouldEqualsAnagramString() {
        Anagram a = new Anagram();
        a.setAnagrams(getWords());
        assertEquals("cat tac",a.getAnagramsAsString());
    }

    private List<Word> getWords() {
        Word one = new Word("cat");
        Word two = new Word("tac");
        List<Word> words = new ArrayList<>();
        words.add(one);
        words.add(two);
        return words;
    }

}
