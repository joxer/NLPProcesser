package com.candido.storage.steplinkcollection;

import com.candido.storage.SimpleAnalyzerInformation;

/**
 * Created by joxer on 02/11/15.
 */
public class FindSubjectAdjective extends StepLinks {

    private final SimpleAnalyzerInformation info;

    FindSubjectAdjective(SimpleAnalyzerInformation info) {
        this.info = info;
    }

    @Override
    public boolean handle() {

        return false;
    }
}
