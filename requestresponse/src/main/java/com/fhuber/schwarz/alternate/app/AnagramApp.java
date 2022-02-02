package com.fhuber.schwarz.alternate.app;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.fhuber.schwarz.alternate.service.Anagram2Service;
import com.fhuber.schwarz.solution.service.AnagramService;

import org.jboss.weld.context.RequestContext;
import org.jboss.weld.context.unbound.UnboundLiteral;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;

/**
 * expects a filename with a word in every single line
 * <p>
 * will create hashKeys frm each word
 * <p>
 * write to System.out
 * <p>
 * uses sorted charArray as key
 * 
 * @author Florian Huber
 *
 */
public class AnagramApp {
    private static Logger logger = Logger.getLogger(AnagramApp.class.getName());

    @Inject
    AnagramService anagramService;

    public static void main(String[] args) {

        // check if there is an argument
        if (args.length == 0 || args[0].length() <= 0) {
            System.err.println("Proper Usage is: java program filename");
            System.exit(1);
        }
        // use argument 1 as filename
        String fileName = args[0];
        logger.log(Level.INFO, () -> "File " + fileName);
        long start = System.nanoTime();

        // initialize the JavaSE Container, implementation is provided by WELD SE (see
        // maven)
        SeContainerInitializer containerInitializer = SeContainerInitializer.newInstance();
        try (SeContainer container = containerInitializer.initialize()) {
            RequestContext requestContext = container.select(RequestContext.class, UnboundLiteral.INSTANCE).get();
            requestContext.activate();
            container.select(Anagram2Service.class).get().process(fileName)
                    .map(value -> value.getAnagramsAsString() + System.getProperty("line.separator"))
                    .forEach(System.out::println);
        }

        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        logger.log(Level.INFO, () -> "File was read and written to FileDescriptor.out in ms " + timeElapsed);
    }

}
