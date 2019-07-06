import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class myTest {

   /** Drives execution. */
   public static void main(String[] args) throws FileNotFoundException {
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("myFile.txt")));
      
   
      System.out.println(doublets.isWord("MATH"));
      
   
    
   }
}