package com.candido.storage.structure;

import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by joxer on 31/10/15.
 */


public class PhraseConcept extends DictionaryConcept {

    private int frequency;

    public PhraseConcept(String concept, int frequency) {
        super(concept);
        this.frequency = frequency;

    }

    public PhraseConcept(DictionaryConcept concept, int frequency) {
        super(concept);
        this.setWords(new HashMap<String, Word>());
        this.frequency = frequency;

    }

    public String toString() {
        return super.toString();
    }

    public void increaseFrequency() {
        this.frequency += 1;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void setWords(HashMap<String, Word> words) {
        this.words = words;
    }

    public static class ConceptComparator implements Comparator<PhraseConcept> {

        @Override
        public int compare(PhraseConcept t1, PhraseConcept t2) {
            if (t1.frequency < t2.frequency) {
                return 1;
            } else if (t1.frequency > t2.frequency) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}