import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class DictionaryManagement {
    DictionaryManagement() {

    }
    public void insertFromCommandline(String eWords, String pronun, String vWords, Dictionary dictionary) {
            dictionary.addWord(eWords, pronun ,vWords);
    }
    public void insertFromFile(Dictionary dictionary) {
        try {
            File newFile = new File("C:/Users/Admin/OneDrive/Documents/dictionary.txt");
            BufferedReader bufferedReader = Files.newBufferedReader(newFile.toPath(), StandardCharsets.UTF_8);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 3) {
                    String eWords = parts[0].trim();
                    String pronun = parts[1].trim();
                    String vWords = parts[2].trim();
                    insertFromCommandline(eWords, pronun, vWords, dictionary);
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
        for (HashMap<String, String> vocab: dictionary.getVocab().keySet()) {
            if (vocab.containsKey(eWords)) {
                String pronun = vocab.get(eWords);
                String meaning = dictionary.getVocab().get(vocab);
                System.out.println(eWords + ": " + pronun + ": " + meaning);
                return;
            }
        }
    }
    public void insertWord(String eWords, String pronun, String vWords, Dictionary dictionary) {
        dictionary.addWord(eWords, pronun, vWords);
    }
    public void deleteWord(String eWords, Dictionary dictionary) {
        for (HashMap<String, String> vocab : dictionary.getVocab().keySet()) {
            dictionary.removeWord(eWords);
            return;
        }
    }

    public void updateWord(String eWords, String newVWords, Dictionary dictionary) {
        for (HashMap<String, String> vocab: dictionary.getVocab().keySet()) {
            if (vocab.containsKey(eWords)) {
                String pronun = vocab.get(eWords);
                String meaning = dictionary.getVocab().get(vocab);
                System.out.println(eWords + ": " + pronun + ": " + meaning);
                dictionary.getVocab().replace(vocab, meaning, newVWords);
                System.out.println(eWords + ": " + pronun + ": " + newVWords);
            }
        }
    }
}

