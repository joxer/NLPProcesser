package com.candido.test;

import com.candido.FileLoader;
import com.candido.WordsStorage;
import com.candido.storage.SimpleAnalyzer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static junit.framework.Assert.*;

/**
 * Created by joxer on 03/11/15.
 */
public class TestSuiteStepLinks {

    private WordsStorage wd;

    @Before
    public void BeforeAction() throws IOException {
        String testPath = getClass().getResource("dictionary.json").getPath();
        Path ph = Paths.get(testPath);
        wd = WordsStorage.loadFromPath(ph);
    }

    @Test
    public void testTotalProcess() {
        System.out.println("testTotalProcess");
        String input = "The hotel was good";
        SimpleAnalyzer simpleAnalyzer = new SimpleAnalyzer(wd);
        simpleAnalyzer.setInput(input);
        simpleAnalyzer.process();
        assertTrue(!simpleAnalyzer.score().equals(0.0));
    }

    @Test
    public void testTotalProcessIfNoStringInputProvided() {
        System.out.println("testTotalProcessIfNoStringInputProvided");
        String input = "";
        SimpleAnalyzer simpleAnalyzer = new SimpleAnalyzer(wd);
        simpleAnalyzer.setInput(input);
        simpleAnalyzer.process();
        assertEquals(simpleAnalyzer.score(), 0.0);
    }

    @Test
    public void testTotalProcessShouldNotGiveError() {
        System.out.println("testTotalProcessShouldNotGiveError");
        String input = "good amazing fantastic awesome clean";
        SimpleAnalyzer simpleAnalyzer = new SimpleAnalyzer(wd);
        simpleAnalyzer.setInput(input);
        simpleAnalyzer.process();
        assertTrue(!simpleAnalyzer.score().equals(0.0));
    }

    @Test
    public void testTotalProcessShouldNotGiveErrorWithEmptyWordStorage() {
        System.out.println("testTotalProcessShouldNotGiveErrorWithEmptyWordStorage");
        WordsStorage tmpWd = new WordsStorage(wd);
        tmpWd.getConcepts().clear();
        tmpWd.getPhrasePart().clear();
        String input = "good amazing fantastic awesome clean";
        SimpleAnalyzer simpleAnalyzer = new SimpleAnalyzer(tmpWd);
        simpleAnalyzer.setInput(input);
        simpleAnalyzer.process();
        assertTrue(simpleAnalyzer.score().equals(0.0));
    }

    @Test
    public void testTotalProcessShouldHaveAdjectivesAtTheAnd() {
        System.out.println("testTotalProcessShouldHaveAdjectivesAtTheAnd");
        String input = "Room was good. Staff was really bad";
        SimpleAnalyzer simpleAnalyzer = new SimpleAnalyzer(wd);
        simpleAnalyzer.setInput(input);
        simpleAnalyzer.process();
        assertTrue(simpleAnalyzer.getInfos().getNegativeAdjective().size() != 0);
        assertTrue(simpleAnalyzer.getInfos().getPositiveAdjective().size() != 0);
        assertTrue(simpleAnalyzer.getInfos().getPhraseConcepts().size() != 0);
        assertTrue(simpleAnalyzer.getInfos().getPhrasePart().size() != 0);
    }

    @Test
    public void testTwoProcessShouldntHaveSimilarScoreAfterExecution() throws IOException {
        System.out.println("testTwoProcessShouldntHaveSimilarScoreAfterExecution");
        String testPath = getClass().getResource("reviews.json").getPath();
        List<String> loadInput = FileLoader.loadFile(Paths.get(testPath));

        SimpleAnalyzer simpleAnalyzer = new SimpleAnalyzer(wd);
        simpleAnalyzer.setInput(loadInput.get(0));
        simpleAnalyzer.process();
        Double before = simpleAnalyzer.score();
        simpleAnalyzer.setInput(loadInput.get(1));
        simpleAnalyzer.process();
        Double then = simpleAnalyzer.score();
        assertNotSame(before, then);
        simpleAnalyzer.setInput(loadInput.get(0));
        simpleAnalyzer.process();
        Double valueAfterBefore = before;
        assertTrue(before.equals(before));
    }


}
