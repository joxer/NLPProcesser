package com.candido.criteria.steplinkcollection;

import com.candido.Const;
import com.candido.Logger;
import com.candido.criteria.SimpleAnalyzerInformation;
import com.candido.criteria.structure.PhraseConcept;

import java.util.Arrays;
import java.util.PriorityQueue;

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

        PriorityQueue<PhraseConcept> concepts = new PriorityQueue<>(info.getPhraseConcepts());
        while (concepts.size() > 0) {
            PhraseConcept concept = concepts.poll();
            Logger.debug(concept.getConcept());
            Logger.debug("Because it talk about: " + concept.printWords());
        }

        Logger.debug("Positive adjective of the phrase are:");
        Logger.debug(Arrays.toString(info.getPositiveAdjective().toArray()));

        Logger.debug("Negative adjective of the phrase are:");
        Logger.debug(Arrays.toString(info.getNegativeAdjective().toArray()));

        Logger.info("Final Result:" + this.info.getResult());
        if (this.info.getResult() >= Const.POSITIVE_ADJECTIVE_MIN) {
            Logger.info("phrase is considered good");
        } else {
            Logger.info("phrase is considered bad");
        }
        return false;
    }
}
