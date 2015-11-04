package com.candido.criteria.steplinkcollection;

import com.candido.Const;
import com.candido.criteria.SimpleAnalyzerInformation;
import com.candido.criteria.structure.Word;

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

        /*
        If positive adjective are more than bad ones, we should multiply for a costant
         */

        if (info.getPositiveAdjective().size() > info.getNegativeAdjective().size()) {
            positiveSum *= Const.MULTIPLY_COSTANT;
        } else {
            negativeSum *= Const.MULTIPLY_COSTANT;
        }
        if (positiveSum == 0.0 && negativeSum == 0.0) {
            finalResult = 0.0;
        } else {
            finalResult = (positiveSum - negativeSum) / sum;
        }

        if (finalResult > 1.0) {
            finalResult = 1.0;
        } else if (finalResult < -1.0) {
            finalResult = -1.0;
        }


        this.info.setResult(finalResult);

        return false;
    }
}
