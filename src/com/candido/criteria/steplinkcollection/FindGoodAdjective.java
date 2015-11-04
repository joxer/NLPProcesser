package com.candido.criteria.steplinkcollection;

import com.candido.Const;
import com.candido.Logger;
import com.candido.criteria.SimpleAnalyzerInformation;
import com.candido.criteria.structure.Word;

import java.util.List;

/**
 * Created by joxer on 01/11/15.
 */
public class FindGoodAdjective extends StepLinks {

    SimpleAnalyzerInformation info;

    public FindGoodAdjective(SimpleAnalyzerInformation info) {
        this.info = info;
    }


    @Override
    public boolean handle() {

        List<Token> tokens = info.getTokens();

        for (Token currentToken : tokens) {
            if (currentToken.part == Const.PHRASE_PARTS.ADJECTIVE) {
                Word wd = this.info.getAdjectiveDefinition(currentToken.token);

                if (info.isGoodAdjective(wd) && currentToken.modifier != Const.MODIFIERS.NEGATION) {
                    Logger.debug("found good adjective:" + currentToken);
                    info.addPositiveAdjective(wd);
                }
            }
        }
        return false;
    }
}
