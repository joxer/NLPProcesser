package com.candido.storage.steplinkcollection;

import com.candido.storage.Const;
import com.candido.storage.SimpleAnalyzerInformation;
import com.candido.storage.structure.DictionaryConcept;
import com.candido.storage.structure.PhraseConcept;
import com.candido.storage.structure.Word;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joxer on 01/11/15.
 */

public class FindSubject extends StepLinks {

    SimpleAnalyzerInformation info;
    Map<String, PhraseConcept> phraseConcept;

    public FindSubject(SimpleAnalyzerInformation info) {
        this.info = info;
        this.phraseConcept = new HashMap<>();
    }

    @Override
    public boolean handle() {

        List<Token> tokens = info.getTokens();


        for (int i = 0; i < tokens.size(); i++) {

            Token currentToken = tokens.get(i);

            Word wd = info.findWord(currentToken.token);
            if (wd != null && wd.getPhrasePart() == Const.PHRASE_PARTS.NOUN) {
                DictionaryConcept cp = info.findConceptWithWord(wd);
                PhraseConcept ph = new PhraseConcept(cp, 1);
                addWordToConceptOrIncreaseValueOfConcept(ph, currentToken);
            }
        }

        for (PhraseConcept ph : phraseConcept.values()) {
            info.getPhraseConcepts().offer(ph);
        }

        return false;
    }

    private void addWordToConceptOrIncreaseValueOfConcept(PhraseConcept ph, Token currentToken) {
        if (this.info.getPhraseConcepts().contains(ph) == false) {
            Word word = info.getWord(currentToken.getToken(), Const.PHRASE_PARTS.NOUN);
            if (phraseConcept.containsKey(ph.getConcept())) {
                phraseConcept.get(ph.getConcept()).getWords().put(currentToken.getToken(), word);
                phraseConcept.get(ph.getConcept()).increaseFrequency();
            } else {
                phraseConcept.put(ph.getConcept(), ph);
                phraseConcept.get(ph.getConcept()).getWords().put(currentToken.getToken(), word);
            }
        } else {
            phraseConcept.get(ph.getConcept()).increaseFrequency();
        }
    }
}