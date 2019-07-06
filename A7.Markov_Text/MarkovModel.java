import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * MarkovModel.java Creates an order K Markov model of the supplied source
 * text. The value of K determines the size of the "kgrams" used to generate
 * the model. A kgram is a sequence of k consecutive characters in the source
 * text.
 *
 * @author     Marco Zuniga (maz0005@auburn.edu)
 * @author     Dean Hendrix (dh@auburn.edu)
 * @version    2016-11-17
 *
 */
public class MarkovModel {

   // Map of <kgram, chars following> pairs that stores the Markov model.
   private HashMap<String, String> model;

   // add other fields as you need them ...
   private String firstkGram = "";
   private String[] kgramArray;
   /**
    * Reads the contents of the file sourceText into a string, then calls
    * buildModel to construct the order K model.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, File sourceText) {
      model = new HashMap<>();
      try {
         String text = new Scanner(sourceText).useDelimiter("\\Z").next();
         buildModel(K, text);
      }
      catch (IOException e) {
         System.out.println("Error loading source text: " + e);
      }
   }


   /**
    * Calls buildModel to construct the order K model of the string sourceText.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, String sourceText) {
      model = new HashMap<>();
      buildModel(K, sourceText);
   }


   /**
    * Builds an order K Markov model of the string sourceText.
    */
   private void buildModel(int K, String sourceText) {
      model = new HashMap<String, String>();
      firstkGram = sourceText.substring(0, K); 
      int length = sourceText.length();
      for (int i = 0; i < length; i++) {
         String key = sourceText.substring(i, K);
         if (K == length && model.containsKey(key)) {
            return;
         }
         if (K == length) {
            model.put(key, "\u0000");
            return;
         }
         String character = sourceText.charAt(K) + "";
         if (model.containsKey(key) && model.get(key) == null) {
            model.put(key, character);
         }
         else if(!model.containsKey(key)) {
            model.put(key, character);
         }
         else  {
            String oldString = model.get(key);
            model.put(key, oldString + character);
         }
         K++;
      }
      
      /**
      TreeSet<String> set = new TreeSet<String>();
      int count1 = 0;
      int check = 0;
      int count2 = K;
      while (sourceText.length() > count1) {
       //Create a string grabbing the first kgram 
         String key = sourceText.substring(count1, K);
         if (set.contains(key)) {
            count1++;
            count2 = K + 1;
            continue;
         }
         set.add(key);
      //This is created to get all possible similar kgrams
         String kgram = sourceText.substring(count1, K);
      //increment count1 so that the next kgram is shifted by a character
         String characters = "";
      //count2 will grab the character after a key
         
         while (sourceText.length() > count2) {
            if (kgram.equals(key)) {
               characters = characters + sourceText.charAt(count2);
            
               kgram = sourceText.substring(count1, count2);
               count2++;
            }
            
         
         }
         model.put(key, characters);
         count2 = K + 1;
         count1++;
      }
      */
      
      
   }


   /** Returns the first kgram found in the source text. */
   public String getFirstKgram() {
      return firstkGram;
   }


   /** Returns a kgram chosen at random from the source text. */
   public String getRandomKgram() {
      Set<String> set = getAllKgrams();
      int count = model.size();
      Random rand = new Random();
      int n = rand.nextInt((set.size() - 0) + 0);
      kgramArray = set.toArray(new String[count]);
      return kgramArray[n];
   }


   /**
    * Returns the set of kgrams in the source text.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   public Set<String> getAllKgrams() {
      return model.keySet();
   }


   /**
    * Returns a single character that follows the given kgram in the source
    * text. This method selects the character according to the probability
    * distribution of all characters that follow the given kgram in the source
    * text.
    */
   public char getNextChar(String kgram) {
     
      String characters = model.get(kgram);
      
      if (characters == null) {
         return '\u0000';
      }
      
      int length = characters.length();
      Random rand = new Random();
      int n = rand.nextInt((length) + 0);
      /**
      HashMap<String, Integer> map = new HashMap<>();
      String var = kgram.charAt(0) + "";
      Integer m = map.get(var);
    
      for (int i = 0; i < length; i++) {
         String current = kgram.charAt(i) + "";
         if (map.containsKey(current)) {
            Integer q = map.get(current);
            map.put(current, q + 1);
         }
         
         if (!map.containsKey(current)){
            map.put(current, 0);
         } 
         
         Integer n = map.get(current);
         
         if (n > m) { 
            m = n;
            var = current;
         }
      }
      
      return var.charAt(0);
      */
      return characters.charAt(n);
   }


   /**
    * Returns a string representation of the model.
    * This is not part of the provided shell for the assignment.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   @Override
    public String toString() {
      return model.toString();
   }

}
