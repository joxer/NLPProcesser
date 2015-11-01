package com.candido.storage.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joxer on 31/10/15.
 */


public class DictionaryConcept {

    String concept;

    Map<String, Word> words;

    public DictionaryConcept(String concept) {
        this.concept = concept;
        this.words = new HashMap<>();
    }

    public DictionaryConcept(DictionaryConcept dc) {
        this.concept = dc.concept;
        this.words = dc.words;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public boolean equals(DictionaryConcept cp) {

        return this.concept.equals(cp.concept);

    }

    public Map<String, Word> getWords() {
        return words;
    }

    public String printWords() {
        StringBuilder buffer = new StringBuilder("");

        for (Word wd : words.values()) {
            buffer.append(wd.getValue() + " ; ");
        }

        return buffer.toString();
    }

    public boolean find(Word currentToken) {

        return words.get(currentToken.getValue()) != null;

    }
}
