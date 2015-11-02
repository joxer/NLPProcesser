package com.candido.storage.steplinkcollection;

import com.candido.Logger;
import com.candido.storage.SimpleAnalyzerInformation;
import com.candido.storage.structure.PhraseConcept;

import java.util.Arrays;

/**
 * Created by joxer on 01/11/15.
 */
public class LogResultProbability extends StepLinks {

    SimpleAnalyzerInformation info;

    public LogResultProbability(SimpleAnalyzerInformation info) {
        this.info = info;
    }


    @Override
    public boolean handle() {
        Logger.info("Current phrase computed: " + info.getOriginalString());

        Logger.debug("Topic of the phrase are:");
        while (info.getWords().size() > 0) {
            PhraseConcept concept = info.getWords().poll();
            Logger.debug(concept.getConcept());
            Logger.debug("Because it talk about: " + concept.printWords());
        }


        Logger.debug("Positive adjective of the phrase are:");
        Logger.debug(Arrays.toString(info.getPositiveAdjective().toArray()));

        Logger.debug("Negative adjective of the phrase are:");
        Logger.debug(Arrays.toString(info.getNegativeAdjective().toArray()));

        Logger.info("Final Result:" + this.info.getResult());
        return false;
    }
}
