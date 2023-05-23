package utils;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *  The <code>DateConverter</code> converts time stored as string to time objects
 */
public class DateConverter {

    /**
     * Converts the passed date string to a LocalDate Object
     * @param date
     * @return
     */
    public static LocalDate convertStringToLocalDate(String date) {
        String[] array = date.split("-");
        LocalDate result = LocalDate.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]),
                Integer.parseInt(array[2]));
        return result;
    }

    /**
     * Converts the passed time string to a LocalTime Object
     * @param time
     * @return
     */
    public static LocalTime convertStringToLocalTime(String time) {
        String[] array = time.split(":");
        LocalTime result = LocalTime.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
        return result;
    }
}
