package com.candido.storage.steplinkcollection;

import com.candido.storage.Const;

/**
 * Created by joxer on 01/11/15.
 */
public class Token {
    public String token;
    public Const.PHRASE_PARTS part;
    public Const.MODIFIERS modifier;

    public Token(String token, Const.PHRASE_PARTS part) {
        this.token = token;
        this.part = part;
    }

    public Token(String token, Const.PHRASE_PARTS part, Const.MODIFIERS modifier) {
        this(token, part);
        this.modifier = modifier;
    }

    public String getToken() {
        return token;
    }

    public String toString() {
        return "[token: " + token + ", phrase part: " + part.name() + "]";
    }
}