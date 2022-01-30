package com.fhuber.schwarz.output;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.fhuber.schwarz.LogUtil;

/**
 * expects a filename with a word in every single line
 * will create hashKeys frm each word
 * write to System.out
 * uses sorted charArray as key
 *
 */
public class WriteToSystemOut {
    private static Logger logger = LogUtil.getLogger(WriteToSystemOut.class);

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Proper Usage is: java program filename");
            System.exit(0);
        }
        long start = System.nanoTime();

        String fileName = args[0];
        logger.log(Level.INFO, () -> "File " + fileName);
        File file = new File(fileName);
        if (file.exists() && !file.isDirectory()) // ! mean not/complement operator
        {
            // yes it's a file
            printLines(file);
        }
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        logger.log(Level.INFO, () -> "File was read and written to System.out in ms " + timeElapsed);
    }

    private static void printLines(File file) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line + " hash: " + getAnagramKey(line));
            }
        }
    }

    /**
     * 
     * @param word
     * @return the hashKey for a possible anagram (word)
     */
    public static String getAnagramKey(String word) {
        char[] c = word.toLowerCase().replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}]", "").toCharArray();
        Arrays.sort(c);
        return new String(c);
    }

}
