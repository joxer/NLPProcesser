package com.candido.criteria.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joxer on 02/11/15.
 */
public class FuzzyConcept {

    Map<String, Word> nouns;
    Map<String, Word> adjectives;

    public FuzzyConcept() {
        this.nouns = new HashMap<>();
        this.adjectives = new HashMap<>();
    }

    public Map<String, Word> getNouns() {
        return nouns;
    }

    public void setConcepts(Map<String, Word> words) {
        this.nouns = words;
    }

    public Map<String, Word> getAdjectives() {
        return adjectives;
    }
}
