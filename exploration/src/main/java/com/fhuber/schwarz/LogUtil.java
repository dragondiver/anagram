package com.fhuber.schwarz;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogUtil {
    private LogUtil() {
    }

    public static Logger getLogger(Class clazz) {
        Logger logger = Logger.getLogger(clazz.getName());
        try {
            logger.setUseParentHandlers(false);
            logger.addHandler(getFileHandler(clazz));
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return logger;
    }

    private static FileHandler getFileHandler(Class clazz) throws IOException {
        FileHandler fileHandler = new FileHandler(
                "results/" + clazz.getPackageName() + "-results.log", true);
        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
        return fileHandler;
    }
}
