package com.candido;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joxer on 02/11/15.
 */
public class FileLoader {


    public static List<String> loadFile(Path inputTxt) throws IOException {

        List<String> ret = new ArrayList<>();

        String readText = Utils.joinArrayString(Files.readAllLines(inputTxt, Charset.defaultCharset()));
        JSONObject object = new JSONObject(readText);
        JSONArray array = object.optJSONArray("phrases");
        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                ret.add(array.getString(i).toLowerCase());
            }
        }
        return ret;
    }
}
