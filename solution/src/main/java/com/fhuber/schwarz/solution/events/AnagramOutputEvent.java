package com.fhuber.schwarz.solution.events;

import com.fhuber.schwarz.solution.model.AnagramMap;
import com.fhuber.schwarz.solution.observers.AnagramOutputEventObserver;
import com.fhuber.schwarz.solution.service.impl.AnagramFileService;

/**
 * Event send from AnagramFileService to trigger the output of the found
 * anagrams
 * <p>
 * hope it finds an Observer
 * 
 * @see AnagramFileService
 * @see AnagramOutputEventObserver
 * 
 * @Author Florian Huber
 */
public class AnagramOutputEvent {

    private AnagramMap map;

    public AnagramOutputEvent(AnagramMap map) {
        this.map = map;
    }

    /**
     * @return AnagramStorageService
     */
    public AnagramMap getAnagramMap() {
        return map;
    }
}
