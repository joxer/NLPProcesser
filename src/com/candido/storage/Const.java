package com.candido.storage;

/**
 * Created by joxer on 31/10/15.
 */
public class Const {

    public static final double DEFAULT_PRIORITY = 0.5;
    public static final int DEBUG = 3;
    public static final int VERBOSE = 2;
    public static final int INFO = 1;
    public static final double NO_POINTS = -2;
    public static double POSITIVE_ADJECTIVE_MIN = 0;

    public enum PHRASE_PARTS {
        ADJECTIVE, NOUN, VERB, OTHER;

        public static PHRASE_PARTS findPhrasePart(String key) {

            if (key.contains("noun"))
                return NOUN;
            else if (key.contains("adjective"))
                return ADJECTIVE;
            else if (key.contains("verb"))
                return VERB;
            else {
                return OTHER;

            }
        }
    }
}

