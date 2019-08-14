package com.lzt.operate.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lzt
 */
public class Convert {
    private static final Logger log = LoggerFactory.getLogger(Convert.class);

    public static String serializeObject(Object target) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(target);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        }
    }

    public static int stringToInt(String v) {
        return Integer.parseInt(v);
    }

    public static <T> List<T> iterableToList(Iterable<T> source) {
        ArrayList<T> list = new ArrayList<>();

        source.forEach(list::add);

        return list;
    }

    public static String dataToString(Date data, String format) {
        return new SimpleDateFormat(format).format(data);
    }

    public static String localDateTimeToString(LocalDateTime dateTime, DateTimeFormatter dateTimeFormatter) {
        return dateTime.format(dateTimeFormatter);
    }

    public static Date stringToData(String s, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(s);
    }

    public static LocalDateTime stringToLocalDateTime(String s) {
        return LocalDateTime.parse(s);

    }
}
