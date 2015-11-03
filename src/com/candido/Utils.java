package com.candido;

import java.util.Arrays;
import java.util.List;

/**
 * Created by joxer on 31/10/15.
 */
public class Utils {
    public static List<String> normalizeTestAndSplit(String text) {


        return Arrays.asList(text.split("(\\.|;|!|\\?|,)+"));


    }

    public static String joinArrayString(List<String> values) {

        StringBuilder buffer = new StringBuilder("");

        for (String value : values) {
            buffer.append(value);
        }

        return buffer.toString();
    }
}
