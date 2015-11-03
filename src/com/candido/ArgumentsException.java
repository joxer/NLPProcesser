package com.candido;

/**
 * Created by joxer on 02/11/15.
 */
public class ArgumentsException extends Throwable {
    public ArgumentsException() {

    }

}

class ArgumentsFewException extends ArgumentsException {
    public ArgumentsFewException() {

    }

}


class ArgumentHelpException extends ArgumentsException {
    public ArgumentHelpException() {

    }
}