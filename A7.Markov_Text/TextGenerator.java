import java.io.File;
import java.io.IOException;
import java.util.HashMap;
/**
 * TextGenerator.java. Creates an order K Markov model of the supplied source
 * text, and then outputs M characters generated according to the model.
 *
 * @author     Marco Zuniga (maz0005@auburn.edu)
 * @author     Dean Hendrix (dh@auburn.edu)
 * @version    2016-11-17
 *
 */
public class TextGenerator {

   /** Drives execution. */
   public static void main(String[] args) {
    
         
      if (args.length < 3) {
         System.out.println("Usage: java TextGenerator k length input");
         return;
      }
   
      // No error checking! You may want some, but it's not necessary.
      int K = Integer.parseInt(args[0]);
      int M = Integer.parseInt(args[1]);
      if ((K < 0) || (M < 0)) {
         System.out.println("Error: Both K and M must be non-negative.");
         return;
      }
   
      File text;
      try {
         text = new File(args[2]);
         if (!text.canRead()) {
            throw new Exception();
         }
      }
      catch (Exception e) {
         System.out.println("Error: Could not open " + args[2] + ".");
         return;
      }
   
      MarkovModel model = new MarkovModel(K, text);
      // instantiate a MarkovModel with the supplied parameters and
      // generate sample output text ...
      int count = 0;
      String output = "";
      String kGram = model.getFirstKgram();
      
      output = output + kGram;
      count += kGram.length();
      while (count < M) {
         char something = model.getNextChar(kGram);
         if (something == '\u0000') {
            kGram = model.getRandomKgram();
         }
         something = model.getNextChar(kGram);
          
          
        
         output = output + something;
         kGram = kGram + something;
         count++;
         kGram = kGram.substring(1);
      }
      
      System.out.print(output);
   
   
   }
}
