package com.fhuber.schwarz;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App {
    
    private static HashUtility hashu;
    public static void main(String[] args) throws IOException {
        hashu = new HashUtility(30);
        if (args.length == 0) {
            System.out.println("Proper Usage is: java program filename");
            System.exit(0);
        }
        Logger logger = Logger.getLogger(App.class.getName());
        long start = System.nanoTime();
        // ...
        String fileName = args[0];
        File file = new File(fileName);
        if (file.exists() && !file.isDirectory()) // ! mean not/complement operator
        {
            // yes it's a file
            try (Stream<String> linesStream = Files.lines(file.toPath())) {
                linesStream.forEach(line -> {
                    System.out.println(line + " hash: " + getAnagramKey(line));
                });
            }
        }
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        logger.log(Level.INFO, () -> "File was read and written to System.out in ms " + timeElapsed );
    }

    public static String getAnagramKey(String word) {
        char[] c = word.toLowerCase().replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}]", "").toCharArray();
        Arrays.sort(c);
        return new String(c);
        // return hashu.hashCode(word.toLowerCase().replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}]", ""));
        //return word;
    }
}
