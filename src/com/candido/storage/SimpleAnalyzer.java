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

    public SimpleAnalyzer(WordsStorage wordStorage) {
        this.infos = new SimpleAnalyzerInformation(input, wordStorage);
        this.wordStorage = wordStorage;
    }


    @Override
    public Double score() {

        return this.infos.getResult();
    }

    @Override
    public void process() {
        if (this.input != null) {
            processChain(this.input);
        }
    }

    public void setInput(String input) {
        this.infos.setInput(input);
        this.input = input;
    }

    public void processChain(String inputString) {


        this.infos.clear();
        this.infos.setInput(inputString);
        TokenizeString tokenizer = new TokenizeString(this.infos);
        FindSubject subject = new FindSubject(this.infos);
        FindGoodAdjective goodAdjective = new FindGoodAdjective(this.infos);
        FindBadAdjective badAdjective = new FindBadAdjective(this.infos);
        FindSubjectAdjective subjectAdj = new FindSubjectAdjective(this.infos);
        ComputeResult compute = new ComputeResult(this.infos);
        LogResultProbability log = new LogResultProbability(this.infos);

        tokenizer.setNext(subject);
        subject.setNext(goodAdjective);
        goodAdjective.setNext(badAdjective);
        badAdjective.setNext(subjectAdj);
        subjectAdj.setNext(compute);
        compute.setNext(log);
        StepLinks step = tokenizer;

        while (step != null) {
            step.handle();
            step = step.next();
        }

        this.cachedResult = this.infos.getResult();
    }

    public SimpleAnalyzerInformation getInfos() {
        return infos;
    }
}
