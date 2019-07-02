import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of generic selection methods on Collections.
 *
 * @author  Marco Zuniga (maz0005@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2016-08-23
 *
 */
public final class Selector {

/**
 * Can't instantiate this class.
 *
 * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
 *
 */
   private Selector() { }


   /**
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    * 
    * First check if the collection or comparator is null and if the
    * size of the collection is 0. Create an iterator and compare every
    * object in the collection to that iterator and return the smallest
    * one found.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param <T> Any kind of object the client will use the method for
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      } 
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      Iterator<T> itr = coll.iterator();
      T min = itr.next();
     
      for (T a : coll) {
         if (comp.compare(min, a) > 0) {
            min = a;
         }
      }
      
      return min;
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * First check if the collection or comparator is null and if the
    * size of the collection is 0. Create an iterator and compare every
    * object in the collection to that iterator and return the largest
    * one found.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param <T> Any kind of object the client will use the method for
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      } 
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      Iterator<T> itr = coll.iterator();
      T max = itr.next();
     
      for (T a : coll) {
         if (comp.compare(max, a) < 0) {
            max = a;
         }
      }
      
      return max;
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * First check to see if the collection or comparator is null and if 
    * k is less than 1 or bigger than the size of the collection.
    * Create a new List that holds arraylists and add every object 
    * from the collection to the list so that you can work with indexes.
    * Sort the List and add all of the unique values to a 
    * new List(uniqueValues). k - 1 from the List will work to return 
    * the kth min value. Write out array and see that it works. 
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param <T> Any kind of object the client will use the method for
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (k < 1 || k > coll.size()) {
         throw new NoSuchElementException();
      }
      List<T> a = new ArrayList<T>();
      for (T b : coll) {
         a.add(b);  
      }
      java.util.Collections.sort(a, comp);
      List<T> uniqueValues = new ArrayList<T>();
      uniqueValues.add(a.get(0));
      int count = 1;
      for (int i = 1; i < a.size(); i++) {
         if (!a.get(i).equals(a.get(i - 1))) {
            uniqueValues.add(a.get(i));
            count++;
         }
      }
      
      if (uniqueValues.isEmpty() || k > count) {
         throw new NoSuchElementException();
      }
      
      return uniqueValues.get(k - 1);
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * First check to see if the collection or comparator is null and if 
    * k is less than 1 or bigger than the size of the collection.
    * Create a new List that holds arraylists and add every object 
    * from the collection to the list so that you can work with indexes.
    * Sort the List and add all of the unique values to a 
    * new List(uniqueValues).count - k from the List will work to return 
    * the kth min value. Write out array and see that it works. 
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param <T> Any kind of object the client will use the method for
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (k < 1 || k > coll.size()) {
         throw new NoSuchElementException();
      }
      
      List<T> a = new ArrayList<T>();
      for (T b : coll) {
         a.add(b);  
      }
      java.util.Collections.sort(a, comp);
      List<T> uniqueValues = new ArrayList<T>();
      uniqueValues.add(a.get(0));
      int count = 1;
      for (int i = 1; i < a.size(); i++) {
         if (!a.get(i).equals(a.get(i - 1))) {
            uniqueValues.add(a.get(i));
            count++;
         }
      }
      
      if (uniqueValues.isEmpty() || k > count) {
         throw new NoSuchElementException();
      }
      
      return uniqueValues.get(count - k);
   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * Make a List and add all the objects from the Collection. Then search 
    * through the list to find the objects that satisfy the conditions
    * and add them to a new Collection. You will then return that Collection.
    *
    * @param coll    the Collection from which the range values are selected
    * @param <T> Any kind of object the client will use the method for
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      List<T> a = new ArrayList<T>();
      for (T b : coll) {
         a.add(b); 
      }
      Collection<T> rangeValues = new ArrayList<T>();
      for (int i = 0; i < a.size(); i++) {
         if (comp.compare(a.get(i), low) >= 0 
            && comp.compare(a.get(i), high) <= 0) {
            rangeValues.add(a.get(i));
         }
      }
      
      if (rangeValues.isEmpty()) {
         throw new NoSuchElementException();
      }
      return rangeValues;
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * Make a new collection that runs the parameters through the range method.
    * Now that you have a collection fulls of values from the key to the 
    * biggest value in Collection, run the new Collection through 
    * the min method which will return either key if it's available or 
    * the next biggest value. 
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param <T> Any kind of object the client will use the method for
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
     
      Collection<T> a = range(coll, key, max(coll, comp), comp);
      if (a.size() == 0) {
         throw new NoSuchElementException();
      }
   
      return min(a, comp);
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    *
    * Make a new collection that runs the parameters through the range method.
    * Now that you have a collection fulls of values from the smallest value 
    * in Collection to the key, run the new Collection through 
    * the max method which will return either key if it's available or 
    * the next smallest value. 
    *
    * @param coll    the Collection from which the floor value is selected
    * @param <T> Any kind of object the client will use the method for
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      Collection<T> a = range(coll, min(coll, comp), key, comp);
      if (a.size() == 0) {
         throw new NoSuchElementException();
      }
      return max(a, comp);
   }

}