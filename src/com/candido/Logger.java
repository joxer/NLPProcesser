package com.candido;

import com.candido.storage.Const;

/**
 * Created by joxer on 31/10/15.
 */
public class Logger {

    public static final int DEBUG_LEVEL = Const.DEBUG;

    public static void debug(String str) {
        System.out.println("[DEBUG] " + str);
    }

    public static void info(String str) {
        System.out.println("[INFO] " + str);
    }

    public static void verbose(String str) {
        System.out.println("[VERBOSE] " + str);
    }


}
