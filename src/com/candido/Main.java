package com.candido;

import com.candido.storage.SimpleAnalyzer;
import com.candido.storage.structure.WordsStorage;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by joxer on 31/10/15.
 */
public class Main {

    public static void main(String args[]) {

        String file = "/home/joxer/trivago.json";
        Path ph = Paths.get(file);
        String input =
                "Got here with last min booking for westlife concert very unimpressed with exterior but proximity to croke park made us brave on. Receptionist nice room basic but clean. Indian restaurant in hotel was fantastic was unusually located in two rooms in middle of hotel. Hotel is real old dublin friendly and good humoured would recommend revamp of outside immediately before scaring off potential patrons as we easily could have been we would return for events in croke park.";
        try {
            WordsStorage wd = WordsStorage.loadFromPath(ph);
            SimpleAnalyzer sp = new SimpleAnalyzer(input, wd);
            sp.setInput(input);
            sp.process();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
