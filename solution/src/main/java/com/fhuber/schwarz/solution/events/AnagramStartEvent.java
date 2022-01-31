package com.fhuber.schwarz.solution.events;

import com.fhuber.schwarz.solution.app.AnagramApp;
import com.fhuber.schwarz.solution.service.AnagramService;

/**
 * Event send from AnagramApp to trigger processing of a file with words
 * 
 * @see AnagramApp
 * @see AnagramService
 * 
 * @Author Florian Huber
 * 
 */
public class AnagramStartEvent {

    private String filename;

    public AnagramStartEvent(String filename) {
        this.filename = filename;
    }

    /**
     * 
     * 
     * @return the filename of the file that should be parsed for anagrams
     */
    public String getFilename() {
        return filename;
    }
}
