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
               /**
                * Exit
                */
               case 0 -> System.exit(0);

               /**
                * add new word
                */
               case 1 -> {
                   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                   System.out.print("Enter the word: ");
                   String eWords = sc.next();
                   System.out.print("Enter the pronunciation: ");
                   String pronun = br.readLine();
                   System.out.print("Enter the meaning: ");
                   String vWords = br.readLine();
                   Word word = new Word(eWords, pronun, vWords);
                   dm.addWord(word, d);
               }

               /**
                * remove a word
                */
               case 2 -> {
                   System.out.print("Enter the word: ");
                   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                   String eWords = br.readLine();
                   dm.removeWord(eWords, d);
               }

               /**
                * update a word
                */
               case 3 -> {
                   System.out.print("Enter the word: ");
                   String eWords = sc.next();
                   System.out.print("Enter new meaning: ");
                   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                   String vWords = br.readLine();
                   dm.updateWord(eWords, vWords, d);
               }

               /**
                * Print the dictionary
                */
               case 4 -> dictionaryBasic(d);
               case 5 -> {
                   System.out.print("Enter the word: ");
                   String eWords = sc.next();
                   dm.dictionaryLookup(eWords, d);

               }
               /**
                * import the dictionary data from file
                */
               case 6 -> importDictionary(dm, d);

               /**
                * Return the list of dictionary search
                */
               case 7 -> {
                   System.out.print("Enter the word or string: ");
                   String word = sc.next();
                   List<String> result = dm.dictionarySearch(word, d);
                   for (String ans: result) {
                       System.out.println(ans);
                   }
               }

               /**
                * export the dictionary into a new file
                */
               case 8 -> dm.exportDictionaryToFile(d);
           }
       }
   }
}
