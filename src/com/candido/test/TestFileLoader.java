package com.candido.test;

import com.candido.FileLoader;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by joxer on 03/11/15.
 */
public class TestFileLoader {

    @Test
    public void canLoadFile() throws IOException {
        String testPath = getClass().getResource("reviews.json").getPath();
        List<String> loadInput = FileLoader.loadFile(Paths.get(testPath));
        assertTrue(loadInput.size() > 0);
    }

}
