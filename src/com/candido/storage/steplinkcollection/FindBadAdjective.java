package com.candido.storage.steplinkcollection;

import com.candido.Logger;
import com.candido.storage.Const;
import com.candido.storage.SimpleAnalyzerInformation;
import com.candido.storage.structure.Word;

import java.util.List;

/**
 * Created by joxer on 01/11/15.
 */
public class FindBadAdjective extends StepLinks {

    SimpleAnalyzerInformation info;

    public FindBadAdjective(SimpleAnalyzerInformation info) {
        this.info = info;
    }


    @Override
    public boolean handle() {
        List<Token> tokens = info.getTokens();

        for (int i = 0; i < tokens.size(); i++) {

            Token currentToken = tokens.get(i);
            if (currentToken.part == Const.PHRASE_PARTS.ADJECTIVE) {
                Word wd = this.info.getAdjectiveDefinition(currentToken.token);
                if (info.isBadAdjective(wd)) {
                    Logger.debug("found negative adjective:" + currentToken);


                    info.addBadAdjective(wd);
                }

            }
        }
        return false;
    }
}
