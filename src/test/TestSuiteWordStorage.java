package test;

import com.candido.WordsStorage;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static junit.framework.Assert.assertEquals;

/**
 * Created by joxer on 03/11/15.
 */
public class TestSuiteWordStorage {

    @Test
    public void loadWordsFromStorage() throws IOException {
        String testPath = getClass().getResource("dictionary.json").getPath();
        Path ph = Paths.get(testPath);
        WordsStorage wd = WordsStorage.loadFromPath(ph);

    }

    @Test
    public void loadWordsFromStorageAndAssertValuesAreLoaded() throws IOException {
        String testPath = getClass().getResource("dictionary.json").getPath();
        Path ph = Paths.get(testPath);
        WordsStorage wd = WordsStorage.loadFromPath(ph);
        assert (wd.getConcepts().size() > 0);
        assert (wd.getPhrasePart().size() > 0);
    }


    @Test
    public void loadWordsFromStorageNotExistShouldFail() {

        WordsStorage wd = null;
        try {
            String testPath = getClass().getResource("trivago_not_exist.json").getPath();
            Path ph = Paths.get(testPath);
            wd = WordsStorage.loadFromPath(ph);
        } catch (Exception e) {

        } finally {
            assertEquals(wd, null);
        }

    }
}
