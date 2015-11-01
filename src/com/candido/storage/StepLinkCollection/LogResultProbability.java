package com.candido.storage.StepLinkCollection;

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

        Logger.info("Topic of the phrase are:");
        while (info.getWords().size() > 0) {
            PhraseConcept concept = info.getWords().poll();
            Logger.info(concept.getConcept());
            Logger.info("Because it talk about: " + concept.printWords());
        }


        Logger.info("Positive adjective of the phrase are:");
        Logger.info(Arrays.toString(info.getPositiveAdjective().toArray()));

        Logger.info("Negative adjective of the phrase are:");
        Logger.info(Arrays.toString(info.getNegativeAdjective().toArray()));

        Logger.info("Final Result:" + this.info.result);
        return false;
    }
}
