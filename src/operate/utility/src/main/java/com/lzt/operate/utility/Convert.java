package com.lzt.operate.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzt
 */
public class Convert {
    private static final Logger log = LoggerFactory.getLogger(Convert.class);

    public static String serialize(Object target) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(target);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        }
    }

    public static int toInt(String v) {
        return Integer.parseInt(v);
    }

    public static <T> List<T> toList(Iterable<T> source) {
        ArrayList<T> list = new ArrayList<>();

        source.forEach(list::add);

        return list;
    }
}
