package com.candido.storage;

import com.candido.storage.StepLinkCollection.Token;
import com.candido.storage.structure.*;

import java.util.*;

/**
 * Created by joxer on 01/11/15.
 */


public class SimpleAnalyzerInformation {
    public double result;
    PriorityQueue<PhraseConcept> words;
    Set<Word> positiveAdjective;
    Set<Word> negativeAdjective;
    WordsStorage wordStorage;
    List<Token> tokens;
    String originalString;

    public SimpleAnalyzerInformation(String originalString, WordsStorage wordStorage) {
        this.words = new PriorityQueue<>(10, new PhraseConcept.ConceptComparator());
        this.positiveAdjective = new HashSet<>();
        this.negativeAdjective = new HashSet<>();
        this.wordStorage = wordStorage;
        this.originalString = originalString;
        tokens = new ArrayList<>();
    }

    public double getAdjectivePoints(String currentToken) {
        Word wd = wordStorage.getWord(currentToken, Const.PHRASE_PARTS.ADJECTIVE);
        return wd.getPoints();
    }

    public void addPositiveAdjective(Word wd) {
        this.positiveAdjective.add(wd);
    }

    public boolean isBadAdjective(Word currentToken) {

        double value = currentToken.getPoints();
        return value != Const.NO_POINTS && value < Const.POSITIVE_ADJECTIVE_MIN;
    }

    public boolean isGoodAdjective(Word currentToken) {
        double value = currentToken.getPoints();
        return value != Const.NO_POINTS && value > Const.POSITIVE_ADJECTIVE_MIN;
    }

    public void addBadAdjective(Word wd) {
        this.negativeAdjective.add(wd);
    }

    public Word getAdjectiveDefinition(String currentToken) {
        return wordStorage.getWord(currentToken, Const.PHRASE_PARTS.ADJECTIVE);

    }

    public PriorityQueue<PhraseConcept> getWords() {
        return words;
    }

    public Set<Word> getPositiveAdjective() {
        return positiveAdjective;
    }

    public Set<Word> getNegativeAdjective() {
        return negativeAdjective;
    }

    public String getOriginalString() {
        return originalString;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public DictionaryConcept findConceptWithWord(Word wd) {
        return this.wordStorage.findConceptWithWord(wd);
    }

    public Word findWord(String token) {
        return this.wordStorage.findWord(token);
    }

    public Word getWord(String token, Const.PHRASE_PARTS noun) {
        return this.wordStorage.getWord(token, noun);
    }

    public Map<Const.PHRASE_PARTS, WordsGroup> getPhrasePart() {
        return this.wordStorage.getPhrasePart();
    }
}

