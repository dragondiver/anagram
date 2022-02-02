package com.fhuber.schwarz.solution.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fhuber.schwarz.solution.events.AnagramOutputEvent;
import com.fhuber.schwarz.solution.exception.AnagramException;
import com.fhuber.schwarz.solution.model.Word;
import com.fhuber.schwarz.solution.service.AnagramService;
import com.fhuber.schwarz.solution.service.AnagramStorage;

import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.inject.Inject;

/**
 * Implementation of the AnagramService that processes files
 * other Implementations might read from DB
 * 
 * @author Florian Huber
 */
public class AnagramFileService implements AnagramService {

    private static Logger logger = Logger.getLogger(AnagramFileService.class.getName());

    @Inject
    AnagramStorage storage;

    @Inject
    BeanManager beanManager;

    /**
     * @param fileName
     * @return String
     */
    public String process(String fileName) {
        try {
            File file = new File(fileName);
            if (file.exists() && !file.isDirectory()) // ! mean not/complement operator
            {
                // yes it's a file
                addLines(file);
                beanManager.fireEvent(new AnagramOutputEvent(storage.getAnagramMap()));
            } else {
                throw new AnagramException("File not found", null);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Stopped because of ", e);
            throw new AnagramException("Exception while reading from File", e);
        }
        return "Finished";
    }

    /**
     * @param file
     * @throws IOException
     */
    private void addLines(File file) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            String line;
            while ((line = reader.readLine()) != null) {
                Word anagram = new Word(line);
                storage.save(anagram);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Stopped because of ", e);
            throw new AnagramException("Exception while saveing words from File", e);
        }
    }

}
