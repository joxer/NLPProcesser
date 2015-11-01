package com.candido.storage.structure;

import com.candido.storage.Const;

/**
 * Created by joxer on 31/10/15.
 */

public class Word {
    private String value;
    private double points;
    private Const.PHRASE_PARTS phrasePart;

    public Word(String value, double points, Const.PHRASE_PARTS part) {
        this.value = value;
        this.points = points;
        this.phrasePart = part;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public Const.PHRASE_PARTS getPhrasePart() {
        return phrasePart;
    }

    public void setPhrasePart(Const.PHRASE_PARTS phrasePart) {
        this.phrasePart = phrasePart;
    }

    public boolean equalsValue(Word other) {
        return this.value.equals(other.value);
    }

    public String toString() {
        return "[value: " + value + ", points: " + points + ", part: " + phrasePart + "]";
    }
}