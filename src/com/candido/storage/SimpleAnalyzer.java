package com.candido.storage;

import com.candido.WordsStorage;
import com.candido.storage.steplinkcollection.*;

/**
 * Created by joxer on 31/10/15.
 */
public class SimpleAnalyzer implements Criteria {

    Double cachedResult = null;
    SimpleAnalyzerInformation infos;
    WordsStorage wordStorage;
    String input;

    public SimpleAnalyzer(String input, WordsStorage wordStorage) {
        this.infos = new SimpleAnalyzerInformation(input, wordStorage);
        this.wordStorage = wordStorage;
    }


    @Override
    public double score() {

        if (cachedResult == null) {
            process();
        }

        return cachedResult;
    }

    @Override
    public void process() {
        processChain(this.input);
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void processChain(String inputString) {
        TokenizeString tokenizer = new TokenizeString(infos);
        FindSubject subject = new FindSubject(infos);
        FindGoodAdjective goodAdjective = new FindGoodAdjective(infos);
        FindBadAdjective badAdjective = new FindBadAdjective(infos);
        ComputeResult compute = new ComputeResult(infos);
        LogResultProbability log = new LogResultProbability(infos);

        tokenizer.setNext(subject);
        subject.setNext(goodAdjective);
        goodAdjective.setNext(badAdjective);
        badAdjective.setNext(compute);
        compute.setNext(log);
        StepLinks step = tokenizer;

        while (step != null) {
            step.handle();
            step = step.next();
        }
    }
}
