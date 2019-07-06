import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Collections;


/**
 * Doublets.java
 * Provides an implementation of the WordLadderGame interface. The lexicon
 * is stored as a TreeSet of Strings.
 *
 * @author Marco Zuniga (maz0005@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2016-11-02
 */
public class Doublets implements WordLadderGame {

   ////////////////////////////////////////////
   // DON'T CHANGE THE FOLLOWING TWO FIELDS. //
   ////////////////////////////////////////////

   // A word ladder with no words. Used as the return value for the ladder methods
   // below when no ladder exists.
   List<String> EMPTY_LADDER = new ArrayList<>();

   // The word list used to validate words.
   // Must be instantiated and populated in the constructor.
   TreeSet<String> lexicon;
  

  


   /**
    * Instantiates a new instance of Doublets with the lexicon populated with
    * the strings in the provided InputStream. The InputStream can be formatted
    * in different ways as long as the first string on each line is a word to be
    * stored in the lexicon.
    */
   public Doublets(InputStream in) {
      try {
         lexicon = new TreeSet<String>();
         Scanner s =
            new Scanner(new BufferedReader(new InputStreamReader(in)));
            
         while (s.hasNext()) {
         
            String str = s.next();
            lexicon.add(str.toLowerCase());
            ////////////////////////////////////////////////
            // Add code here to store str in the lexicon. //
            ////////////////////////////////////////////////
            s.nextLine();
         }
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }

   ///////////////////////////////////////////////////////////////////////////////
   // Fill in implementations of all the WordLadderGame interface methods here. //
   ///////////////////////////////////////////////////////////////////////////////
/**
    * Returns the Hamming distance between two strings, str1 and str2. The
    * Hamming distance between two strings of equal length is defined as the
    * number of positions at which the corresponding symbols are different. The
    * Hamming distance is undefined if the strings have different length, and
    * this method returns -1 in that case. See the following link for
    * reference: https://en.wikipedia.org/wiki/Hamming_distance
    *
    * @param  str1 the first string
    * @param  str2 the second string
    * @return      the Hamming distance between str1 and str2 if they are the
    *                  same length, -1 otherwise
    */
   public int getHammingDistance(String str1, String str2) {
      if (str1.length() != str2.length()) {
         return -1;
      }
      if (str1.length() == 0) {
         return 0;
      }
      
      int count = 0;
      for (int i = 0; i < str1.length(); i++) {
         if (str1.charAt(i) != str2.charAt(i)) {
            count++;
         }
      }
      return count;
   }


  /**
   * Returns a word ladder from start to end. If multiple word ladders exist,
   * no guarantee is made regarding which one is returned. If no word ladder exists,
   * this method returns an empty list.
   *
   * Depth-first search with backtracking must be used in all implementing classes.
   *
   * @param  start  the starting word
   * @param  end    the ending word
   * @return        a word ladder from start to end
   */
   public List<String> getLadder(String start, String end) {
      return dfsIterativeB(start, end);
   }
   
   
   private List<String> dfsIterativeB(String start, String end) {
      start = start.toLowerCase();
      end = end.toLowerCase();
      if (!isWord(start) || !isWord(end) || start.length() != end.length()) {
         return EMPTY_LADDER;
      }
      
      /* If start is equal to end*/
      if (start.equals(end)) {
         List<String> singleWord = new ArrayList<String>();
         singleWord.add(start);
         return singleWord;
      }
      
      Deque<String> stack = new ArrayDeque<>();
      
      
      TreeSet<String> visitedWords = new TreeSet<String>();
      visit(visitedWords, start);
      
      //List<String> returningList = new ArrayList<String>();
      stack.addFirst(start);
      //returningList.add(start);
      while (!stack.isEmpty()) {
         String word = stack.peekFirst();
         String neighbor = getFirstUnvisited(getNeighbors(word), visitedWords);
         if (neighbor != null) {
            stack.addFirst(neighbor);
            visit(visitedWords, neighbor);
            //returningList.add(neighbor);
            if (neighbor.equals(end)) {
               List<String> returningList = Arrays.<String>asList(stack.toArray(new String[]{}));
               Collections.reverse(returningList);
               return returningList;
            }
         }
         else {
            stack.removeFirst();
         }
      }
     
      return EMPTY_LADDER;
   }
   
    
   private String getFirstUnvisited(List<String> list, TreeSet<String> set) {
      for (String neighbor : list) {
         if (!isVisited(set, neighbor)) {
            return neighbor;
         }
      
      }
      return null;
   
   }  


  /**
   * Returns a minimum-length word ladder from start to end. If multiple
   * minimum-length word ladders exist, no guarantee is made regarding which
   * one is returned. If no word ladder exists, this method returns an empty
   * list.
   *
   * Breadth-first search must be used in all implementing classes.
   *
   * @param  start  the starting word
   * @param  end    the ending word
   * @return        a minimum length word ladder from start to end
   */
   public List<String> getMinLadder(String start, String end) {
      return bfs(start, end);
   
   }
   
   private List<String> bfs(String start, String end) {
      if (!isWord(start) || !isWord(end) || start.length() != end.length()) {
         return EMPTY_LADDER;
      }
      
      if (start.equals(end)) {
         List<String> singleWord = new ArrayList<String>();
         singleWord.add(start);
         return singleWord;
      
      }
      
      TreeSet<String> visitedWords = new TreeSet<String>();
      
      
      //!!!! retrurn single word if same
      //Purpose:Breadth-first
      Deque<Node> queue = new ArrayDeque<Node>(); 
      //Purpose: Have you found what you're looking for
      
      //Purpose: Rememeber where you've been, cause then you''l just go in circles
      visit(visitedWords, start);
      
      queue.addLast(new Node(start, null));
      
      while (!queue.isEmpty()) {
         Node n = queue.removeFirst();
         String nWord = n.word;
         for (String neighbor : getNeighbors(nWord)) {
            
            if (!isVisited(visitedWords, neighbor) && isWord(neighbor)) {
               visit(visitedWords, neighbor);
               
               queue.addLast(new Node(neighbor, n));
            }
            
            
            if (neighbor.equals(end)) {
               return aList(queue.removeLast());
            }
                          
         }
      }
      return EMPTY_LADDER; 
   }
      
      
   private List<String> aList(Node n) {
      List<String> returningList = new ArrayList<String>();
      while (n != null) {
         returningList.add(n.word);
         n = n.predecessor;
      } 
      Collections.reverse(returningList);
      return returningList;
   
   }
   
 
   
 /**  
   private breadthFirstSearch(String start, String end) {
   
   
   }
*/
   
   
   private boolean isVisited(TreeSet<String> set, String neighbor) {
      if (neighbor == null) {
         return false;
      }
      return set.contains(neighbor);
   }

   private void visit(TreeSet<String> set, String word) {
      set.add(word);
   
   }
   


   private class Node {
      /** the value stored in this node. */
      private String word;
      /** a reference to the node before this node. */
      private Node predecessor;
     
      
   
      /**
       * Instantiate a node with a given word
       */
   
      public Node(String word, Node predecessor) {
         this.word = word;
         this.predecessor = predecessor;
      }
      
    
    
   }


   /**
    * Returns all the words that have a Hamming distance of one relative to the
    * given word.
    *
    * @param  word the given word
    * @return      the neighbors of the given word
    */
    
   public List<String> getNeighbors(String word) {
   
      if (word.length() == 0) {
         return EMPTY_LADDER;
      }
      
      word = word.toLowerCase();
      
      List<String> collectedWords = new ArrayList<String>();
      String thisWord = word;
      int wordIndex = 0;
   
      while (wordIndex < word.length()) {
         char[] thisWordArray = word.toCharArray();
         for (thisWordArray[wordIndex] = 'a'; thisWordArray[wordIndex] <= 'z'; thisWordArray[wordIndex]++) {
         
            String newWord = new String(thisWordArray);
         
            if (isWord(newWord) && !newWord.equals(word)) {
               collectedWords.add(newWord);
            }
         }
         wordIndex++;
      }
      
      return collectedWords;
   }


   /**
    * Returns the total number of words in the current lexicon.
    *
    * @return number of words in the lexicon
    */
   public int getWordCount() {
      return lexicon.size();
   }


   /**
    * Checks to see if the given string is a word.
    *
    * @param  str the string to check
    * @return     true if str is a word, false otherwise
    */
   public boolean isWord(String str) {
      return lexicon.contains(str.toLowerCase());
   }


   /**
    * Checks to see if the given sequence of strings is a valid word ladder.
    *
    * @param  sequence the given sequence of strings
    * @return          true if the given sequence is a valid word ladder,
    *                       false otherwise
    */
   public boolean isWordLadder(List<String> sequence) {
   
      if (sequence == null || sequence.size() == 0) {
         return false;
      }
      
      int numOfWordsChecked = 0;
      //word being compared to first word
      int wordIndex = 0;
     //letter in word being cheked 
      
   
      while (numOfWordsChecked < sequence.size() - 1) {
      
         
         if (wordIndex == sequence.size()) {
            return true;
         }
         String firstWord = sequence.get(wordIndex);
         wordIndex++;
         String nextWord = sequence.get(wordIndex);
         
         if (getHammingDistance(firstWord, nextWord) != 1) {
            return false;
         }
         if (firstWord.length() != nextWord.length() || nextWord == null || isWord(nextWord) != true) {
            return false;
         }
         int count = getHammingDistance(firstWord, nextWord);
         if (count > 1) {
            return false;
         }
         
         numOfWordsChecked++;
      }
   
      return true;
   }
   
   
   
}

