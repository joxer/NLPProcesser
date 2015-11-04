package com.candido;

import com.candido.criteria.Criteria;
import com.candido.criteria.SimpleAnalyzer;
import com.candido.criteria.ThirdPartyBayes;
import org.apache.commons.cli.*;
import org.json.JSONException;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
/**
 * Created by joxer on 31/10/15.
 */
public class MainProgram {

    private String json;
    private String phrases;
    private Options options;
    private Criteria criterium;
    private WordsStorage storage;

    public MainProgram() {

        options = new Options();
        addOptions();

    }

    public static void main(String args[]) {
        MainProgram mainProgram = new MainProgram();
        try {
            mainProgram.parseInput(args);
            mainProgram.run();
        } catch (ArgumentsFewException e) {
            System.out.println("Too few arguments");
            mainProgram.printHelp();
        } catch (ArgumentHelpException e) {
            mainProgram.printHelp();
        } catch (ParseException e) {
            mainProgram.printHelp();
        } catch (NoSuchFileException file) {
            System.out.println("File doesn't exist: " + file.getFile() + "\n" +
                    "Provide a valid input");
        } catch (JSONException e) {
            System.err.println("the JSON input is not properly formatted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addOptions() {
        options.addOption("d", "dictionary", true, "Dictionary of the analyzer");
        options.addOption("r", "reviews", true, "Reviews of the analyzer");
        options.addOption("b", "bayes", true, "Use a bayes filter");
        options.addOption("f", "frequency", true, "use frequency of words");

    }

    public void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("NLPProcessor", options);
    }

    private void run() throws IOException {

        Path inputTxt = Paths.get(this.phrases);

        List<String> loadInput = FileLoader.loadFile(inputTxt);

        for (String str : loadInput) {
            criterium.setInput(str);
            criterium.process();
        }

    }

    private void parseInput(String[] args) throws ArgumentsFewException, ArgumentHelpException, ParseException, IOException {

        CommandLineParser parser = new BasicParser();
        CommandLine cmd = null;

            cmd = parser.parse(options, args);

            if (cmd.hasOption("d") && cmd.hasOption("r")) {
                this.json = cmd.getOptionValue("d");
                this.phrases = cmd.getOptionValue("r");
            } else {
                throw new ArgumentsFewException();
            }

        loadStorage();


        if (cmd.hasOption("b")) {
            Logger.info("Program started in bayes mode!");
            ThirdPartyBayes simpleAnalyzer = new ThirdPartyBayes(this.storage.getAdjectives());
            criterium = simpleAnalyzer;
        } else {
            Logger.info("Program started in frequentistic mode!");
            SimpleAnalyzer simpleAnalyzer = new SimpleAnalyzer(this.storage);
            criterium = simpleAnalyzer;
        }


    }

    private void loadStorage() throws IOException {
        Path ph = Paths.get(this.json);
        this.storage = WordsStorage.loadFromPath(ph);
    }
}
