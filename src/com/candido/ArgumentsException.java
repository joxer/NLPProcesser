package com.candido;

import com.candido.storage.Const;

/**
 * Created by joxer on 02/11/15.
 */
public class ArgumentsException extends Throwable {
    public ArgumentsException(String s) {
        super(s);
    }

    public void printHelp() {
        System.err.println("Options:\n" +
                Const.DICTIONARY_ARGUMENT_STRING + "/path/to/dictionary\n" +
                Const.REVIEWS_ARGUMENT_STRING + "=/path/to/reviews");
    }
}

class ArgumentsFewException extends ArgumentsException {
    public ArgumentsFewException(String s) {
        super(s);
    }

    public void printHelp() {
        System.err.println("Too few arguments");
        super.printHelp();
    }
}


class ArgumentHelpException extends ArgumentsException {
    public ArgumentHelpException(String s) {
        super(s);
    }
}