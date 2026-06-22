package ZomatoClone.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Timeutils {
    public static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss yyyy"));
    }
}