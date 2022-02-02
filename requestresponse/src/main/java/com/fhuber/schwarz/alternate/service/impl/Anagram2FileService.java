package com.fhuber.schwarz.alternate.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import com.fhuber.schwarz.alternate.service.Anagram2Service;
import com.fhuber.schwarz.solution.exception.AnagramException;
import com.fhuber.schwarz.solution.model.Anagram;
import com.fhuber.schwarz.solution.model.Word;
import com.fhuber.schwarz.solution.service.AnagramStorage;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

/**
 * Implementation of the AnagramService that processes files
 * other Implementations might read from DB
 * 
 * @author Florian Huber
 */
@RequestScoped
public class Anagram2FileService implements Anagram2Service {

    private static Logger logger = Logger.getLogger(Anagram2FileService.class.getName());

    @Inject
    AnagramStorage storage;

    /**
     * @param fileName
     * @return String
     */
    public Stream<Anagram> process(String fileName) {
        try {
            File file = new File(fileName);
            if (file.exists() && !file.isDirectory()) // ! mean not/complement operator
            {
                // yes it's a file
                addLines(file);
                return storage.getAnagramMap().getAnagrams();
            } else {
                throw new AnagramException("File not found", null);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Stopped because of ", e);
            throw new AnagramException("Exception while reading from File", e);
        }
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
