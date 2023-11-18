
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Dictionary implements DictionarySetting {
    private HashMap<HashMap<String, String>, String> new_words;
    private HashMap<String, String> EnglishWords;
    private int numberOfWords = 0;

    public Dictionary() {
        new_words = new HashMap<>();
        EnglishWords = new HashMap<>();
    }

    public int getNumberOfWords() {
        return numberOfWords;
    }

    public HashMap<String, String> getEnglishWords() {
        return EnglishWords;
    }
    public HashMap<HashMap<String, String>, String> getVocab() {
        return new_words;
    }

    public void addWord(Word word, Dictionary dictionary) {
        dictionary.EnglishWords = new HashMap<>();
        dictionary.EnglishWords.put(word.getWord_target(), word.getPronunciation());
        dictionary.new_words.put(dictionary.EnglishWords, word.getWord_explain());
    }

    public void removeWord(String word, Dictionary dictionary) {
        Iterator<Map.Entry<HashMap<String, String>, String>> iterator = dictionary.new_words.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<HashMap<String, String>, String> entry = iterator.next();
            HashMap<String, String> englishWords = entry.getKey();

            if (englishWords.containsKey(word)) {
                iterator.remove();
            }
        }
    }

    public void updateWord(String eWords, String newVWords, Dictionary dictionary) {
        for (HashMap<String, String> vocab: dictionary.new_words.keySet()) {
            if (vocab.containsKey(eWords)) {
                String pronun = vocab.get(eWords);
                String meaning = dictionary.new_words.get(vocab);
                System.out.println(eWords + ": " + pronun + ": " + meaning);
                dictionary.new_words.replace(vocab, meaning, newVWords);
                System.out.println(eWords + ": " + pronun + ": " + newVWords);
            }
        }
    }
    public void print(Dictionary dictionary) {
        for (HashMap<String, String> eachWord: dictionary.new_words.keySet()) {
            String meaning = dictionary.new_words.get(eachWord);
            for (HashMap.Entry<String, String> word: eachWord.entrySet()) {
                String eWords = word.getKey();
                String pronun = word.getValue();
                System.out.println(eWords + ": " + pronun + ": " + meaning);
            }
            numberOfWords++;
        }
        System.out.println("Number of words: " + numberOfWords);
    }
}
