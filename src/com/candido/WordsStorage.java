package com.candido;

import com.candido.criteria.structure.DictionaryConcept;
import com.candido.criteria.structure.Word;
import com.candido.criteria.structure.WordsGroup;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by joxer on 31/10/15.
 */
public class WordsStorage {

    private Map<Const.PHRASE_PARTS, WordsGroup> phraseParts;
    private Map<String, DictionaryConcept> concepts;

    public WordsStorage() {
        phraseParts = new HashMap<>();
        concepts = new HashMap<>();
    }

    public WordsStorage(WordsStorage wd) {
        this.phraseParts = new HashMap<>(wd.getPhrasePart());
        this.concepts = new HashMap<>(wd.getConcepts());
    }

    public static WordsStorage loadFromPath(Path path) throws IOException, JSONException {

        String readText = Utils.joinArrayString(Files.readAllLines(path, Charset.defaultCharset()));
        JSONObject object = new JSONObject(readText);
        WordsStorage storage = new WordsStorage();

        Iterator<String> jsonKeys = object.keys();

        while (jsonKeys.hasNext()) {

            String key = jsonKeys.next();
            WordsGroup wordGroup = new WordsGroup(key);
            JSONArray jsonValue = object.getJSONArray(key);
            Const.PHRASE_PARTS phrasePartKey = Const.PHRASE_PARTS.findPhrasePart(key);

            parseJSONAndPutInWordsGroupAndConcept(phrasePartKey, jsonValue, storage, wordGroup);

            storage.phraseParts.put(phrasePartKey, wordGroup);
        }

        return storage;
    }

    private static void parseJSONAndPutInWordsGroupAndConcept(Const.PHRASE_PARTS phrasePartKey, JSONArray jsonValues, WordsStorage storage, WordsGroup wordGroup) {

        if (phrasePartKey.equals(Const.PHRASE_PARTS.NOUN)) {
            parseNouns(jsonValues, storage, wordGroup);
        } else if (phrasePartKey.equals(Const.PHRASE_PARTS.ADJECTIVE)) {
            parseAdjective(jsonValues, storage, wordGroup);
        } else if (phrasePartKey.equals(Const.PHRASE_PARTS.VERB)) {
            //
        } else if (phrasePartKey.equals(Const.PHRASE_PARTS.OTHER)) {
            //
        }
    }

    private static void parseNouns(JSONArray jsonValues, WordsStorage storage, WordsGroup wordGroup) {
        for (int i = 0; i < jsonValues.length(); i++) {
            try {
                JSONObject obj = jsonValues.getJSONObject(i);
                String value = obj.getString("value");
                String topic = obj.getString("topic");

                DictionaryConcept concept = new DictionaryConcept(topic);
                Word wd = new Word(value, Const.DEFAULT_PRIORITY, Const.PHRASE_PARTS.NOUN);

                if (storage.conceptExist(concept)) {
                    storage.increaseConceptValueInConceptList(topic, wd);
                } else {
                    storage.addWordToConceptList(concept, wd);
                }

                wordGroup.put(value, wd);
            } catch (JSONException e) {
                Logger.error("Error parsing json nouns");
            }
        }
    }

    private static void parseAdjective(JSONArray jsonValues, WordsStorage storage, WordsGroup wordGroup) {
        for (int i = 0; i < jsonValues.length(); i++) {
            try {
                JSONObject obj = jsonValues.getJSONObject(i);
                String value = obj.getString("value");
                Double rate = obj.optDouble("rate");

                Word wd = new Word(value, rate, Const.PHRASE_PARTS.ADJECTIVE);
                wordGroup.put(value, wd);
            } catch (JSONException e) {
                Logger.error("Error parsing json adjectives");
            }
        }
    }

    private void addWordToConceptList(DictionaryConcept concept, Word wd) {
        concept.getWords().put(wd.getValue(), wd);
        this.concepts.put(concept.getConcept(), concept);
    }

    private void increaseConceptValueInConceptList(String concept, Word wd) {
        DictionaryConcept cp = this.concepts.get(concept);
        cp.getWords().put(wd.getValue(), wd);
    }

    private boolean conceptExist(DictionaryConcept cp) {

        return concepts.get(cp.getConcept()) != null;
    }

    public Map<String, DictionaryConcept> getConcepts() {
        return concepts;
    }


    public Map<Const.PHRASE_PARTS, WordsGroup> getPhrasePart() {
        return phraseParts;
    }


    public Word getWord(String token, Const.PHRASE_PARTS noun) {

        WordsGroup group = phraseParts.get(noun);
        if (group != null) {
            return group.get(token);
        }

        return null;
    }

    public Word findWord(String str) {
        Iterator<Const.PHRASE_PARTS> it = this.phraseParts.keySet().iterator();
        while (it.hasNext()) {

            Const.PHRASE_PARTS key = it.next();
            Word tmpWord = this.phraseParts.get(key).get(str);
            if (tmpWord != null)
                return tmpWord;

        }
        return null;
    }

    public DictionaryConcept findConceptWithWord(Word wd) {
        Iterator<String> it = this.concepts.keySet().iterator();
        while (it.hasNext()) {

            String key = it.next();
            Word tmpWord = this.concepts.get(key).getWords().get(wd.getValue());
            if (tmpWord != null)
                return this.concepts.get(key);

        }
        return null;
    }

    public WordsGroup getAdjectives() {
        return this.getPhrasePart().get(Const.PHRASE_PARTS.ADJECTIVE);
    }
}




