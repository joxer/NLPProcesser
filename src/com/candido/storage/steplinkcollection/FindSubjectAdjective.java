package com.candido.storage.steplinkcollection;

import com.candido.Logger;
import com.candido.Utils;
import com.candido.storage.SimpleAnalyzerInformation;
import com.candido.storage.structure.FuzzyConcept;
import com.candido.storage.structure.PhraseConcept;
import com.candido.storage.structure.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by joxer on 02/11/15.
 */
public class FindSubjectAdjective extends StepLinks {

    private final SimpleAnalyzerInformation info;

    public FindSubjectAdjective(SimpleAnalyzerInformation info) {
        this.info = info;
    }

    @Override
    public boolean handle() {

        // Horrible performance :)
        // Who cares, it's just fun as it's an example

        List<String> originalPhrases = Utils.normalizeTestAndSplit(info.getOriginalString());

        List<Word> negativeAdjective = new ArrayList<>();
        negativeAdjective.addAll(info.getNegativeAdjective());

        List<Word> positiveAdjective = new ArrayList<>();
        positiveAdjective.addAll(info.getPositiveAdjective());

        List<PhraseConcept> concepts = new ArrayList<>();
        concepts.addAll(this.info.getPhraseConcepts());
        List<Word> words = new ArrayList<>();

        Logger.debug("--- word aggregator ---");

        for (PhraseConcept concept : concepts) {
            words.addAll(new ArrayList<Word>(Arrays.asList(concept.getWords().values().toArray(new Word[0]))));
        }

        for (String phrase : originalPhrases) {

            FuzzyConcept exConcept = new FuzzyConcept();

            for (int i = 0; i < words.size(); i++) {

                String pattern = "\\s+(" + words.get(i).getValue() + ")(\\w+|\\s+)";
                Pattern patternObject = Pattern.compile(pattern);
                Matcher match = patternObject.matcher(phrase);

                if (match.find()) {
                    exConcept.getNouns().put(words.get(i).getValue(), words.get(i));
                    words.remove(i);
                }
            }
            for (int k = 0; k < positiveAdjective.size(); k++) {
                String pattern = "\\s+(" + positiveAdjective.get(k).getValue() + ")(\\w+|\\s+)";
                Pattern patternObject = Pattern.compile(pattern);
                Matcher match = patternObject.matcher(phrase);


                if (match.find()) {
                    exConcept.getAdjectives().put(positiveAdjective.get(k).getValue(), positiveAdjective.get(k));
                    positiveAdjective.remove(k);
                }
            }
            for (int k = 0; k < negativeAdjective.size(); k++) {
                String pattern = "\\s+(" + negativeAdjective.get(k).getValue() + ")(\\w+|\\s+)";
                Pattern patternObject = Pattern.compile(pattern);
                Matcher match = patternObject.matcher(phrase);

                if (match.find()) {
                    exConcept.getAdjectives().put(negativeAdjective.get(k).getValue(), negativeAdjective.get(k));
                    negativeAdjective.remove(k);
                }
            }

            if (exConcept.getAdjectives().size() > 0 && exConcept.getNouns().size() > 0) {
                Logger.debug("phrase: " + phrase);
                Logger.debug("nouns:");
                Logger.debug(exConcept.getNouns().toString());
                Logger.debug("adjective:");
                Logger.debug(exConcept.getAdjectives().toString());

                this.info.addNounConnectedWithAdjectives(exConcept);

            }
        }
        Logger.debug("--- word aggregator end ---");

        return false;
    }
}
