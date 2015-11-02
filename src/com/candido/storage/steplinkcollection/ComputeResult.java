package com.candido.storage.steplinkcollection;

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
        double finalResult = 0.0;
        double sum = info.getPositiveAdjective().size() + info.getNegativeAdjective().size();

        for (Word wd : info.getPositiveAdjective()) {
            positiveSum += wd.getPoints();
        }
        for (Word wd : info.getNegativeAdjective()) {
            negativeSum += Math.abs(wd.getPoints());
        }

        if (info.getPositiveAdjective().size() > info.getNegativeAdjective().size()) {
            positiveSum *= 1.25;
        } else {
            negativeSum *= 1.25;
        }
        if (positiveSum == 0.0 && negativeSum == 0.0) {
            finalResult = 0.0;
        } else {
            finalResult = (positiveSum - negativeSum) / sum;
        }

        this.info.setResult(finalResult);

        return false;
    }
}
