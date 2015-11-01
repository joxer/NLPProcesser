package com.candido.storage.structure;

import java.util.HashMap;

/**
 * Created by joxer on 31/10/15.
 */
public class WordsGroup extends HashMap<String, Word> {
    public String property;

    public WordsGroup(String property) {
        super();
        this.property = property;
    }
}