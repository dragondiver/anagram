package com.fhuber.schwarz.exploration.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fhuber.schwarz.LogUtil;

/**
 * expects a filename with a word in every single line
 * will create hashKeys frm each word
 * write to System.out
 * uses sorted charArray as key
 *
 */
public class HashMapExploration {
    private static Logger logger = LogUtil.getLogger(HashMapExploration.class);

    private static Map<String, AnagramCollection> anaMap = new HashMap<>();

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
            addLines(file);
            printAnagrams();
        }
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        logger.log(Level.INFO, () -> "File was read and written to FileDescriptor.out in ms " + timeElapsed);
    }

    private static void printAnagrams() {
        try {
            try (FileWriter out = new FileWriter(java.io.FileDescriptor.out)) {
                for (AnagramCollection value : anaMap.values()) {
                    if (value != null && value.getAnagrams().size() > 1) {
                        out.write(value.getAnagramsAsString() + System.getProperty("line.separator"));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static void addLines(File file) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            String line;
            while ((line = reader.readLine()) != null) {
                Anagram anagram = new Anagram(line);
                addToMap(anagram);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Stopped because of ", e);
            throw new RuntimeException(e);
        }
    }

    private static void addToMap(Anagram anagram) {
        String key = anagram.getKey();
        anaMap.computeIfAbsent(key, k -> new AnagramCollection()).getAnagrams().add(anagram);

    }

}
