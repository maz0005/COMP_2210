import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class LinkedSetTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   @Test public void addTest1() {
      LinkedSet<Integer> set = new LinkedSet<Integer>();
      set.add(3);
      set.add(2);
      set.add(4);
      set.add(23);
      set.add(45);
      set.add(1);
      set.add(1);
      set.add(3);
      set.add(7);
      Assert.assertEquals(7, set.size());
   
   
   
   
   }
   @Test public void randomTest1() {
     
      LinkedSet<Integer> set = new LinkedSet<Integer>();
      for (int i = 0; i < 10; i++) {
         set.add(i);
      
      }
      
      for(int j = 9; j >= 0; j--) {
         set.remove(j);
      
      }
      for (int i = 0; i < 10; i++) {
         set.add(i);
      
      }
      for (int i = 0; i < 10; i++) {
         set.add(i);
      
      }
      
      Assert.assertEquals(10, set.size());
      
   
   
   }
   
   @Test public void unionTest() {
      LinkedSet<Integer> set = new LinkedSet<Integer>();
      LinkedSet<Integer> set2 = new LinkedSet<Integer>();
   
      set.add(1);
      set.add(2);
      set.add(3);
   
      set2.add(6);
      set2.add(4);
      set2.add(5);
      LinkedSet<Integer> set3 = new LinkedSet<Integer>();
      set3.add(1);
      set3.add(2);
      set3.add(3);
   
      set3.add(6);
      set3.add(4);
      set3.add(5);
      
      boolean whatever = set3.equals(set.union(set2));
      
      Assert.assertEquals(true, whatever);
   
   }
   
   @Test public void unionTest2() {
      LinkedSet<Integer> set = new LinkedSet<Integer>();
      LinkedSet<Integer> set2 = new LinkedSet<Integer>();
   
      set.add(1);
      set.add(2);
      set.add(3);
   
      set2.add(3);
      set2.add(4);
      set2.add(2);
      LinkedSet<Integer> set3 = new LinkedSet<Integer>();
      set3.add(1);
      set3.add(2);
      set3.add(3);
   
      set3.add(4);
    
      
      boolean whatever = set3.equals(set.union(set2));
      
      Assert.assertEquals(true, whatever);
   
   }
   
   
   
}
