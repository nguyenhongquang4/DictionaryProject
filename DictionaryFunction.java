import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.List;
public class DictionaryFunction extends DictionaryManagement {
    public static void importDictionary(DictionaryManagement dm, Dictionary dictionary) {
        dm.insertFromFile(dictionary);
    }

    public static void dictionaryBasic(Dictionary dictionary) {
        DictionaryCommandline.showAllWord(dictionary);
    }

    public static void main(String[] args) throws IOException {
        DictionaryManagement dm = new DictionaryManagement();
        Dictionary d = new Dictionary();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of step: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
           System.out.print("Enter your choice: ");
           int choice = sc.nextInt();
           switch (choice) {
               case 0 -> System.exit(0);
               case 1 -> {
                   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                   System.out.print("Enter the word: ");
                   String eWords = sc.next();
                   System.out.print("Enter the pronunciation: ");
                   String pronun = br.readLine();
                   System.out.print("Enter the meaning: ");
                   String vWords = br.readLine();
                   dm.insertWord(eWords, pronun, vWords, d);
               }
               case 2 -> {
                   System.out.print("Enter the word: ");
                   String eWords = sc.next();
                   dm.deleteWord(eWords, d);
               }
               case 3 -> {
                   System.out.print("Enter the word: ");
                   String eWords = sc.next();
                   System.out.print("Enter new meaning: ");
                   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                   String vWords = br.readLine();
                   dm.updateWord(eWords, vWords, d);
               }
               case 4 -> dictionaryBasic(d);
               case 5 -> {
                   System.out.print("Enter the word: ");
                   String eWords = sc.next();
                   dm.dictionaryLookup(eWords, d);

               }
               case 6 -> importDictionary(dm, d);
               case 7 -> {
                   System.out.print("Enter the word: ");
                   String word = sc.next();
                   List<String> result = dm.dictionarySearch(word, d);
                   for (String ans: result) {
                       System.out.println(ans);
                   }
               }
               case 8 -> {
                   dm.exportDictionaryToFile(d);
               }
           }
       }
   }
}
