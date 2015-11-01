package com.candido.storage.structure;

import java.util.Comparator;

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
        this.frequency = frequency;

    }

    public void increaseFrequency() {
        this.frequency += 1;
    }

    public int getFrequency() {
        return this.frequency;
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