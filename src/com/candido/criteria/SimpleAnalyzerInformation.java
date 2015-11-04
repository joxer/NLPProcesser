package com.candido.criteria;

import com.candido.Const;
import com.candido.WordsStorage;
import com.candido.criteria.steplinkcollection.Token;
import com.candido.criteria.structure.*;

import java.util.*;

/**
 * Created by joxer on 01/11/15.
 */


public class SimpleAnalyzerInformation {
    private double result;
    private PriorityQueue<PhraseConcept> words;
    private Set<Word> positiveAdjective;
    private Set<Word> negativeAdjective;
    private WordsStorage wordStorage;
    private List<Token> tokens;
    private String originalString;
    private List<FuzzyConcept> fuzzyConcepts;

    public SimpleAnalyzerInformation(String originalString, WordsStorage wordStorage) {
        this.words = new PriorityQueue<>(10, new PhraseConcept.ConceptComparator());
        this.positiveAdjective = new HashSet<>();
        this.negativeAdjective = new HashSet<>();
        this.wordStorage = wordStorage;
        this.originalString = originalString;
        this.fuzzyConcepts = new ArrayList<>();
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

    public PriorityQueue<PhraseConcept> getPhraseConcepts() {
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

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public void addNounConnectedWithAdjectives(FuzzyConcept exConcept) {
        this.fuzzyConcepts.add(exConcept);
    }


    public void clear() {
        this.result = 0.0;
        this.words.clear();
        this.positiveAdjective.clear();
        this.negativeAdjective.clear();
        this.tokens.clear();
        this.originalString = null;
        this.fuzzyConcepts.clear();

    }

    public void setInput(String input) {
        this.originalString = input;
    }
}

