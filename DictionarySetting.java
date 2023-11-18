public interface DictionarySetting {
    public void print(Dictionary dictionary);
    public void addWord(Word word, Dictionary dictionary);
    public void removeWord(String word, Dictionary dictionary);
    public void updateWord(String word, String newVWord, Dictionary dictionary);
}
