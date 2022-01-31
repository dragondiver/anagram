package com.fhuber.schwarz.solution.app;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.fhuber.schwarz.solution.events.AnagramStartEvent;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

/**
 * expects a filename with a word in every single line
 * will create hashKeys frm each word
 * write to System.out
 * uses sorted charArray as key
 *
 */
public class AnagramApp {
    private static Logger logger = Logger.getLogger(AnagramApp.class.getName());

    public static void main(String[] args) {

        if (args.length == 0) {
            logger.log(Level.SEVERE, "Proper Usage is: java program filename");
            System.exit(0);
        }
        String fileName = args[0];
        logger.log(Level.INFO, () -> "File " + fileName);
        long start = System.nanoTime();

        SeContainerInitializer containerInitializer = SeContainerInitializer.newInstance();
        try (SeContainer container = containerInitializer.initialize()) {
            container.getBeanManager().fireEvent(new AnagramStartEvent(fileName));
        }

        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        logger.log(Level.INFO, () -> "File was read and written to FileDescriptor.out in ms " + timeElapsed);
    }


}
