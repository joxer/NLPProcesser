package com.candido.storage.steplinkcollection;

/**
 * Created by joxer on 01/11/15.
 */


public abstract class StepLinks {

    StepLinks next;

    public abstract boolean handle();

    public void setNext(StepLinks next) {
        this.next = next;
    }

    public boolean hasNext() {
        return next != null;
    }

    public StepLinks next() {
        return next;
    }

}


