package com.candido.storage.StepLinkCollection;

import com.candido.storage.SimpleAnalyzerInformation;
import com.candido.storage.structure.Word;

/**
 * Created by joxer on 01/11/15.
 */
public class ComputeResult extends StepLinks {
    SimpleAnalyzerInformation info;

    public ComputeResult(SimpleAnalyzerInformation info) {
        this.info = info;
    }

    @Override
    public boolean handle() {
        double positiveSum = 0.0;
        double negativeSum = 0.0;

        double sum = info.getPositiveAdjective().size() + info.getNegativeAdjective().size();

        for (Word wd : info.getPositiveAdjective()) {
            positiveSum += wd.getPoints();
        }
        for (Word wd : info.getNegativeAdjective()) {
            negativeSum += Math.abs(wd.getPoints());
        }

        double finalResult = (positiveSum - negativeSum) / sum;
        this.info.result = finalResult;

        return false;
    }
}
