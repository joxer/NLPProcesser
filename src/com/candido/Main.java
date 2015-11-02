package com.candido;

import com.candido.storage.Const;
import com.candido.storage.SimpleAnalyzer;
import org.json.JSONException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by joxer on 31/10/15.
 */
public class Main {

    private String json;
    private String phrases;

    public static void main(String args[]) {

        try {

            Main m = new Main();
            m.parseInput(args);
            m.run();

        } catch (ArgumentsFewException e) {
            e.printHelp();
        } catch (ArgumentHelpException e) {
            e.printHelp();
        } catch (JSONException e) {
            System.err.println("the JSON input is not properly formatted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run() throws IOException {

        Path ph = Paths.get(this.json);
        Path inputTxt = Paths.get(this.phrases);


        List<String> loadInput = FileLoader.loadFile(inputTxt);
        WordsStorage wd = WordsStorage.loadFromPath(ph);

        for (String str : loadInput) {
            SimpleAnalyzer sp = new SimpleAnalyzer(str, wd);
            sp.setInput(str);
            sp.process();
        }
    }

    private void parseInput(String[] args) throws ArgumentsFewException, ArgumentHelpException {

        if (args.length < 2) {
            throw new ArgumentsFewException("Too few arguments");
        }

        if (args[0].contains(Const.DICTIONARY_ARGUMENT_STRING) && args[1].contains(Const.REVIEWS_ARGUMENT_STRING)) {
            this.json = args[0].replace(Const.DICTIONARY_ARGUMENT_STRING, "");
            this.phrases = args[1].replace(Const.REVIEWS_ARGUMENT_STRING, "");
        } else if (args[1].contains(Const.DICTIONARY_ARGUMENT_STRING) && args[0].contains(Const.REVIEWS_ARGUMENT_STRING)) {
            this.json = args[1].replace(Const.DICTIONARY_ARGUMENT_STRING, "");
            this.phrases = args[0].replace(Const.REVIEWS_ARGUMENT_STRING, "");
        } else {
            throw new ArgumentHelpException("Arguments parse failed");
        }

    }


}
