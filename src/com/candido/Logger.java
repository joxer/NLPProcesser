package com.candido;

import com.candido.storage.Const;

/**
 * Created by joxer on 31/10/15.
 */
public class Logger {

    public static final int DEBUG_LEVEL = Const.INFO;

    public static void debug(String str) {
        if (DEBUG_LEVEL == Const.DEBUG) {
            System.out.println("[DEBUG] " + str);
        }
    }

    public static void info(String str) {

        if (DEBUG_LEVEL >= Const.INFO) {
            System.out.println("[INFO] " + str);
        }
    }

    public static void verbose(String str) {
        if (DEBUG_LEVEL >= Const.VERBOSE) {
            System.out.println("[VERBOSE] " + str);
        }
    }

    public static void error(String s) {
        System.err.println("[ERR] " + s);
    }
}
