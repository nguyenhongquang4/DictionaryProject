import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class DictionaryManagement implements DictionarySetting {
    DictionaryManagement() {
    }

    public void insertFromCommandline(Word word, Dictionary dictionary) {
            dictionary.addWord(word, dictionary);
    }
    public void insertFromFile(Dictionary dictionary) {
        try {
            File newFile = new File("C:/Users/Admin/OneDrive/Documents/importDictionary.txt");
            BufferedReader bufferedReader = Files.newBufferedReader(newFile.toPath(), StandardCharsets.UTF_8);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 2) {
                    String eWords = parts[0].trim();
                    String pronun = parts[1].trim();
                    String vWords = "";
                    for (int i = 2; i < parts.length; i++) {
                        vWords += parts[i].trim();
                    }
                    Word word = new Word(eWords, pronun, vWords);
                    insertFromCommandline(word, dictionary);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportDictionaryToFile(Dictionary dictionary) {
        try {
            FileWriter fw = new FileWriter("C:/Users/Admin/OneDrive/Documents/exportDictionary.txt");
            BufferedWriter writer = new BufferedWriter(fw);
            for (HashMap.Entry<HashMap<String, String>, String> entry : dictionary.getVocab().entrySet()) {
                HashMap<String, String> key = entry.getKey();
                String value = entry.getValue();

                StringBuilder entryString = new StringBuilder();
                for (HashMap.Entry<String, String> subEntry : key.entrySet()) {
                    String subKey = subEntry.getKey();
                    String subValue = subEntry.getValue();
                    entryString.append(subKey).append(":").append(subValue).append(", ");
                }
                entryString.delete(entryString.length() - 2, entryString.length());

                writer.write(entryString + ": " + value);
                writer.newLine();
            }
            writer.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Lỗi khi xuất dữ liệu từ điển ra tệp.");
        }
    }

    public List<String> dictionarySearch(String search, Dictionary dictionary) {
        List<String> result = new ArrayList<>();
        for (HashMap<String, String> eachWord: dictionary.getVocab().keySet()) {
            for (String eWords: eachWord.keySet()) {
                if (eWords.startsWith(search)) {
                    StringBuilder s = new StringBuilder();
                    s.append(eWords).append(": ").append(eachWord.get(eWords)).append(": ").append(dictionary.getVocab().get(eachWord));
                    result.add(s.toString());
                }
            }
        }
        return result;
    }
    public void dictionaryLookup(String eWords, Dictionary dictionary) {
            for (HashMap<String, String> vocab : dictionary.getVocab().keySet()) {
                if (vocab.containsKey(eWords)) {
                    String pronun = vocab.get(eWords);
                    String meaning = dictionary.getVocab().get(vocab);
                    System.out.println(eWords + ": " + pronun + ": " + meaning);
                }
            }
    }
    public void addWord(Word word, Dictionary dictionary) {
        dictionary.addWord(word, dictionary);
    }
    public void removeWord(String eWords, Dictionary dictionary) {
            dictionary.removeWord(eWords, dictionary);
    }

    public void updateWord(String eWords, String newVWords, Dictionary dictionary) {
        dictionary.updateWord(eWords, newVWords, dictionary);
    }

    public void print(Dictionary dictionary) {
        dictionary.print(dictionary);
    }
}

