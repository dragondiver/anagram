package com.fhuber.schwarz.solution.app;

import java.util.logging.Logger;

import com.fhuber.schwarz.solution.model.AnagramMap;
import com.fhuber.schwarz.solution.service.AnagramOutputService;

import jakarta.enterprise.inject.Alternative;

@Alternative
public class AnagramSystemWriterService implements AnagramOutputService {

    private static Logger logger = Logger.getLogger(AnagramSystemWriterService.class.getName());

    
    /** 
     * 
     * Print all of the 
     * 
     * @param storage
     */
    @Override
    public void print(AnagramMap map) {
            map.getAnagrams().map(value -> value.getAnagramsAsString() + System.getProperty("line.separator"))
                    .forEach(System.out::println);
    }

}
