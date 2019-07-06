import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

public class ArraySetTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


 
   
   @Test public void addTest1() {
      ArraySet<Integer> obj = new ArraySet<Integer>();
      obj.add(4);
      Assert.assertEquals(true, obj.contains(4));
   
   }
   
   @Test public void sizeTest1() {
      ArraySet<Integer> obj = new ArraySet<Integer>();
      obj.add(0);
      obj.add(1);
      obj.add(2);
      obj.add(3);
      obj.add(4);
   
      Assert.assertEquals(5, obj.size());
   
   }
   
   @Test public void desArrayIteratorTest1() {
      ArraySet<Integer> obj = new ArraySet<Integer>();
      obj.add(0);
      obj.add(1);
      obj.add(2);
      obj.add(3);
      obj.add(4);
      
      Iterator<Integer> itr = obj.descendingIterator();
      Assert.assertEquals(4, itr.next(), .001);
   
   }
 
   @Test public void powSetIteratorTest1() {
      ArraySet<Integer> obj = new ArraySet<Integer>();
      obj.add(0);
      obj.add(1);
      obj.add(2);
      
      Iterator<Set<Integer>> itr = obj.powerSetIterator();
      ArraySet<Integer> obj2 = new ArraySet<Integer>();
     
      Assert.assertEquals(true, obj2.equals(itr.next()));
   
   }
   
   @Test public void powSetIteratorTest2() {
      ArraySet<Integer> obj = new ArraySet<Integer>();
      obj.add(2);
      obj.add(3);
      
      Iterator<Set<Integer>> itr = obj.powerSetIterator();
      ArraySet<Integer> obj2 = new ArraySet<Integer>();
      obj.add(2);
      ArraySet<Integer> obj3 = new ArraySet<Integer>();
      obj3.add(3);
      Assert.assertEquals(true, obj2.equals(itr.next()));
     
   
   }
   
   
 
   
   @Test public void unionArraySetTest1() {
      ArraySet<Integer> obj = new ArraySet<Integer>();
      obj.add(0);
      obj.add(1);
      obj.add(2);
      
      ArraySet<Integer> obj2 = new ArraySet<Integer>();
      obj2.add(3);
      obj2.add(4);
      obj2.add(5);
      
      ArraySet<Integer> obj3 = new ArraySet<Integer>();
      obj3.add(0);
      obj3.add(1);
      obj3.add(2);
      
   
      obj3.add(3);
      obj3.add(4);
      obj3.add(5);
      Assert.assertEquals(true, obj3.equals(obj.union(obj2)));
      
      
   
   }
   
   @Test public void unionArraySetTest2() {
      ArraySet<Integer> obj = new ArraySet<Integer>();
      obj.add(0);
      obj.add(1);
      obj.add(2);
      obj.add(0);
      obj.add(1);
      obj.add(2);
      
      ArraySet<Integer> obj2 = new ArraySet<Integer>();
      obj2.add(3);
      obj2.add(4);
      obj2.add(5);
      obj2.add(3);
      obj2.add(4);
      obj2.add(5);
      
      ArraySet<Integer> obj3 = new ArraySet<Integer>();
      obj3.add(0);
      obj3.add(1);
      obj3.add(2);
      obj3.add(3);
      obj3.add(4);
      obj3.add(5);
      obj3.add(0);
      obj3.add(1);
      obj3.add(2);
      obj3.add(3);
      obj3.add(4);
      obj3.add(5);
      
      Assert.assertEquals(true, obj3.equals(obj.union(obj2)));
      
      
   
   }
   
   @Test public void unionArraySetTest3() {
      ArraySet<Integer> obj = new ArraySet<Integer>();
      
      
      ArraySet<Integer> obj2 = new ArraySet<Integer>();
    
      
      ArraySet<Integer> obj3 = new ArraySet<Integer>();
    
   
     
      Assert.assertEquals(true, obj3.equals(obj.union(obj2)));
      
      
   
   }
   
   @Test  public void removeTest() {
   
      ArraySet<Integer> obj = new ArraySet<Integer>();
      obj.add(2);
      obj.add(3);
      obj.add(5);
      obj.remove(5);
      
      Assert.assertEquals(2, obj.size());
   
   
   }
   
   @Test  public void randomTest() {
   
      ArraySet<Integer> obj = new ArraySet<Integer>();
      obj.contains(4);
      obj.add(4);
      obj.contains(4);
      obj.add(1);
      obj.add(5);
      obj.add(3);
      obj.add(1);
      obj.add(2);
      obj.contains(3);
      obj.remove(4);
      obj.contains(4);
      obj.remove(1);
      obj.remove(5);
      obj.contains(5);
      
      Assert.assertEquals(false, obj.contains(5));
   
   
   }
   
   @Test  public void randomTest2() {
   
      ArraySet<Integer> s1 = new ArraySet<Integer>();
      s1.add(0);
      s1.add(1);
      s1.add(2);
      s1.add(3);
   
      s1.remove(1);
      s1.remove(0);
      
      s1.add(0);
      s1.add(1);
      s1.add(2);
      s1.add(3);
   
      s1.remove(1);
      s1.remove(0);
      
      s1.remove(3);
      s1.remove(2);
      s1.add(0);
      s1.add(1);
      s1.add(2);
      s1.add(3);
   
      s1.remove(1);
      s1.remove(0);
      
      s1.add(0);
      s1.add(1);
      s1.add(2);
      s1.add(3);
   
      s1.remove(1);
      s1.remove(0);
      
      s1.remove(3);
      s1.remove(2);
      s1.add(3);
      s1.add(2);
   
      
      Iterator<Integer> itr = s1.iterator();
      Integer dnd = itr.next();
      
      
      Assert.assertEquals(3, itr.next(), .001);
   
   
   }
   
  
   
   
   
   
   
}
