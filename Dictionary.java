import java.util.HashMap;
public class Dictionary {
    private HashMap<HashMap<String, String>, String> new_words;
    private HashMap<String, String> EnglishWords;
    private int numberOfWords = 0;

    public Dictionary() {
        new_words = new HashMap<>();
        EnglishWords = new HashMap<>();
    }

    public void addWord(String eWords, String pronun, String vWords) {
        EnglishWords = new HashMap<>();
        EnglishWords.put(eWords, pronun);
        new_words.put(EnglishWords, vWords);
    }

    public boolean getWord(String word) {
        if (EnglishWords.containsKey(word)) {
            return true;
        }
        return false;
    }

    public void removeWord(String word) {
        for (HashMap<String, String> englishWords : new_words.keySet()) {
            if (getWord(word)) {
                new_words.remove(englishWords);
                return;
            }
        }
        numberOfWords--;
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
    public void print() {
        for (HashMap<String, String> eachWord: new_words.keySet()) {
            String meaning = new_words.get(eachWord);
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
