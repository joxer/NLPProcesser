package com.candido.criteria.steplinkcollection;

import com.candido.Const;
import com.candido.criteria.SimpleAnalyzerInformation;
import com.candido.criteria.structure.WordsGroup;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by joxer on 01/11/15.
 */
public class TokenizeString extends StepLinks {

    SimpleAnalyzerInformation info;

    public TokenizeString(SimpleAnalyzerInformation info) {
        this.info = info;
    }

    @Override
    public boolean handle() {

        String original = info.getOriginalString().toLowerCase();

        Iterator<Const.PHRASE_PARTS> phraseParts = info.getPhrasePart().keySet().iterator();

        while (phraseParts.hasNext()) {
            Const.PHRASE_PARTS phrasePart = phraseParts.next();
            WordsGroup group = info.getPhrasePart().get(phrasePart);
            Iterator<String> wordsIterator = group.keySet().iterator();
            while (wordsIterator.hasNext()) {
                String currentString = wordsIterator.next();
                String pattern = "\\s?(" + currentString + ")\\s?";
                Pattern patternObject = Pattern.compile(pattern);
                Matcher match = patternObject.matcher(original);

                /*
                SHOULD LOOKUP ON NEGATION BEFORE STRING
                 */

                if (match.find()) {
                    if (match.group(1).equals(currentString)) {
                        info.getTokens().add(new Token(currentString, phrasePart));
                    }
                }
                }
            }

        return false;
    }


}
