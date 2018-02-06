package util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class Logger {

    public static void writeLog(Exception ex, String className) {
            java.util.logging.Logger logger = java.util.logging.Logger.getLogger(className);
            FileHandler fh;

            try {
                fh = new FileHandler("assets/logs/"+getDate()+".txt", true);
                logger.addHandler(fh);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                
            } catch (SecurityException | IOException e) {
                e.printStackTrace();
            }
            
            //logger.setUseParentHandlers(false);
            logger.log(Level.SEVERE, null, ex);
    }

    private static String getDate() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getMonthValue() + "-" + localDate.getYear();
    }
}
