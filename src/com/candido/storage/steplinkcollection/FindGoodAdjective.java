package com.candido.storage.steplinkcollection;

import com.candido.Logger;
import com.candido.storage.Const;
import com.candido.storage.SimpleAnalyzerInformation;
import com.candido.storage.structure.Word;

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

        for (int i = 0; i < tokens.size(); i++) {

            Token currentToken = tokens.get(i);
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
