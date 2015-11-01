package com.candido.storage.StepLinkCollection;

import com.candido.storage.Const;

/**
 * Created by joxer on 01/11/15.
 */
public class Token {
    public String token;
    public Const.PHRASE_PARTS part;

    public Token(String token, Const.PHRASE_PARTS part) {
        this.token = token;
        this.part = part;
    }

    public String getToken() {
        return token;
    }
}