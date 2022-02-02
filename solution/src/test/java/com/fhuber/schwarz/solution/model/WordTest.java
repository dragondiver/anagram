package com.fhuber.schwarz.solution.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class WordTest {

    @Test
    public void shouldGetKey() {
        Word word = new Word("cat");
        assertEquals("act", word.getKey());
    }

}
