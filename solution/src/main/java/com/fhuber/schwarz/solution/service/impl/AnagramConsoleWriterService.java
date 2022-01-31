package com.fhuber.schwarz.solution.service.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fhuber.schwarz.solution.exception.AnagramException;
import com.fhuber.schwarz.solution.model.AnagramMap;
import com.fhuber.schwarz.solution.service.AnagramOutputService;

public class AnagramConsoleWriterService implements AnagramOutputService {

    private static Logger logger = Logger.getLogger(AnagramConsoleWriterService.class.getName());

    
    /** 
     * 
     * Print all of the 
     * 
     * @param storage
     */
    @Override
    public void print(AnagramMap map) {
        try (FileWriter out = new FileWriter(java.io.FileDescriptor.out)) {
            map.getAnagrams().map(value -> value.getAnagramsAsString() + System.getProperty("line.separator"))
                    .forEach(t -> {
                        try {
                            out.write(t);
                        } catch (IOException e) {
                            logger.log(Level.SEVERE, "Stopped because of ", e);
                            throw new AnagramException("Exception while writing word to FileDesriptor.out", e);
                        }
                    });
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Stopped because of ", e);
            throw new AnagramException("Exception while writing to FileDesriptor.out", e);
        }
    }

}
