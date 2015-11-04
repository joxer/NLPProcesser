package com.candido.criteria;

import com.candido.Const;
import com.candido.Logger;
import com.candido.criteria.structure.Word;
import com.candido.criteria.structure.WordsGroup;
import com.datumbox.opensource.classifiers.NaiveBayes;
import com.datumbox.opensource.dataobjects.NaiveBayesKnowledgeBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joxer on 04/11/15.
 */
public class ThirdPartyBayes implements Criteria {
    static NaiveBayesKnowledgeBase knowledgeBase;
    private String input;
    private double score = 0.0;
    private Map<String, String[]> adjectives;

    public ThirdPartyBayes(WordsGroup wds) {
        adjectives = new HashMap<>();

        discernGoodAndBadAdjectives(wds);
        if (knowledgeBase == null) {
            createBayesianFilter();
        }
    }

    private void discernGoodAndBadAdjectives(WordsGroup wds) {

        List<String> good = new ArrayList<String>();
        List<String> bad = new ArrayList<String>();

        for (Word wd : wds.values()) {
            if (wd.getPoints() >= Const.POSITIVE_ADJECTIVE_MIN) {
                good.add(wd.getValue());
            } else {
                bad.add(wd.getValue());
            }
        }

        this.adjectives.put("good", good.toArray(new String[0]));
        this.adjectives.put("bad", bad.toArray(new String[0]));
    }

    public void setInput(String input) {
        this.input = input;
    }


    @Override
    public Double score() {
        return score;
    }

    private synchronized void createBayesianFilter() {
        NaiveBayes n = new NaiveBayes();
        NaiveBayes nb = new NaiveBayes();
        nb.setChisquareCriticalValue(1.0);
        nb.train(adjectives);
        knowledgeBase = nb.getKnowledgeBase();
    }

    @Override
    public void process() {
        NaiveBayes naiveBayes = new NaiveBayes(knowledgeBase);
        this.score = Const.BAYES_NEUTRAL;
        if (this.input.equals("")) {

            Logger.info("phrase: " + this.input);
            Logger.info("phrase is empty!");
            this.score = Const.BAYES_NEUTRAL;
        } else {
            String output = naiveBayes.predict(this.input);


            if (output.equals("bad")) {
                Logger.info("phrase: " + this.input);
                Logger.info("considered bad");
                this.score = Const.BAYES_BAD;
            } else {
                Logger.info("phrase: " + this.input);
                Logger.info("considered good");
                this.score = Const.BAYES_GOOD;
            }
        }
    }
}
