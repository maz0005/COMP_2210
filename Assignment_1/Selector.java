import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Marco Zuniga (maz0005@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  2016-08-18
*
*/

public final class Selector {

   private Selector() { }
   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    
    * Pick the first value in the array a  and initialize it. Then compare
    * it to the next one. If the next one is smaller, set that value as the 
    * smallest.
    * Made num and num2 refernce types to see if they're null.
    
    * @return min will return the smallest value found in the
    * array.
    * @param a is the array you're wanting to find the smallest 
    * value of.
    * @throws IllegalArgumentException throws if the method 
    * doesn't meet the requirements.
    */
   public static int min(int[] a) throws IllegalArgumentException {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int min = a[0];
      for (int i = 1; i < a.length; i++) {
         if (a[i] < min) {
            min = a[i];
         }
      }
      return min;
   }

    /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    
    *Set the value at index 0 and scan the array to 
    *find the largest value.
    
    * @return max Returns the biggest value in the array.
    * @param a is the array you're wanting to find the biggest 
    * value of.
    * @throws IllegalArgumentException throws if method
    * doesn't satisfy any of the conditions.
    */
   public static int max(int[] a) throws IllegalArgumentException {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int max = a[0];
      for (int i = 1; i < a.length; i++) {
         if (a[i] > max) {
            max = a[i];
         }
      }
      return max;
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
   
    *First create a copy array of the array and sort it.
    *Then make another copy of the same length which will
    *only hold unique value. Then you will see that to get 
    *the kth min of of array C you need to return c[k - 1];
    
    *@return c[k -1] Returns the value that has k - 1 values less
    *than it.
    *@param a is the array you're wanting to find k min of.
    *@param k is the value that helps you find the k min value.
    *@throws IllegalArgumentException throws if the method doens't 
    *satisfy any of the conditions.
    */
   public static int kmin(int[] a, int k) throws IllegalArgumentException {
      
      if (a == null || a.length == 0 || k < 1 || k > a.length) {
         throw new IllegalArgumentException();
      }
      int[] b = Arrays.copyOf(a, a.length);
      Arrays.sort(b);
      int[] c = new int[b.length];
      //b[0] is already a unique character.
      int count = 1;
      c[0] = b[0];
      
      for (int i = 1; i < b.length; i++) {
         if (b[i] != b[i - 1]) {
            c[count] = b[i];
            count++;
         }
      }
      
      if (k > count) {
         throw new IllegalArgumentException();
      }
      
      return c[k - 1];
   }
   
   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    
    *First create a copy array of the array and sort it.
    *Then make another copy of the same length which will
    *only hold unique value. Then you will see that to get 
    *the kth value of array C you need to return c[count - k];
    
    *@return c[count - k] Returns the value that has k - 1 values more
    *than it.
    *@param a is the array you're wanting to find k max of.
    *@param k is the value that helps you find the k min value.
    *@throws IllegalArgumentException throws if the method doens't 
    *satisfy any of the conditions.
    */
   public static int kmax(int[] a, int k) {
     
      if (a == null || a.length == 0 || k < 1 || k > a.length) {
         throw new IllegalArgumentException();
      }
      int[] b = Arrays.copyOf(a, a.length);
      Arrays.sort(b);
      int[] c = new int[b.length];
      //b[0] is already a unique character.
      int count = 1;
      c[0] = b[0];
      
      for (int i = 1; i < b.length; i++) {
         if (b[i] != b[i - 1]) {
            c[count] = b[i];
            count++;
         }
      }
      
      if (k > count) {
         throw new IllegalArgumentException();
      }
      
      return c[count - k];
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    
    *Seach the array to find the number that satisfy the 
    *conditions to make a precise array.
    *then add the ones that do to a new array by using the
    *same for loop.
    
    *@return b returns a new array that consists of values
    *equal and within to low and high.
    *@param a is the array you're using.
    *@param low is the lowest value one can be.
    *@param high is the highest value one can be.
    *@throws IllegalArgumentException throws if the method doens't 
    *satisfy any of the conditions.
    */
   public static int[] range(int[] a, int low, int high) 
   throws IllegalArgumentException {
   /**
   -First see how many values satisfy the condition then make
   a new array based off that value.
   */
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int count = 0;
      for (int i = 0; i < a.length; i++) {
         if (low <= a[i] && a[i] <= high) {
            count++;
         }
      }
   /**
   -Go through a similar for-loop to add the values 
   that satisfy the conditions to the new array.
   */
      int[] b = new int[count]; 
      int arrayCount = 0;
      for (int j = 0; j < a.length; j++) {
         if (low <= a[j] && a[j] <= high) {
            b[arrayCount] = a[j];
            arrayCount++;
         }
      }
      
      return b;
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    
    *First Check if the array hold the key value
    *if not run create a new array(b) consisting of array(a)
    *ran through the arrayWBV method.
    *Then use the min method to find the smallest value
    *in the array, which comes right after the key.
    
    *@return value Returns the closest value that is >= key.
    *@param a is the array you're comparing.
    *@param key is the value you're comparing to.
    *@throws IllegalArgumentException throws if the method doens't 
    *satisfy any of the conditions.
    */
    
   public static int ceiling(int[] a, int key) throws IllegalArgumentException {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      for (int i = 0; i < a.length; i++) {
         if (a[i] == key) {
            return key;
         }
      }
      int[] b = Selector.arrayWBV(a, key);
      if (b == null || b.length == 0) {
         throw new IllegalArgumentException();
      }
      int value = Selector.min(b);
      
      return value;
   }
   
   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    
    *First Check if the array hold the key value
    *if not run create a new array(b) consisting of array(a)
    *ran through the arrayWSV method.
    *Then use the min method to find the smallest value
    *in the array, which comes right before the key.
    
    *@return value Returns the closest value that is >= key.
    *@param a is the array you're comparing.
    *@param key is the value you're comparing to.
    *@throws IllegalArgumentException throws if the method doens't 
    *satisfy any of the conditions.
    */
    
   public static int floor(int[] a, int key) throws IllegalArgumentException {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      for (int i = 0; i < a.length; i++) {
         if (a[i] == key) {
            return key;
         }
      }
      int[] b = Selector.arrayWSV(a, key);
      if (b == null || b.length == 0) {
         throw new IllegalArgumentException();
      }
      int value = Selector.max(b);
      
      return value;
   }
   
  /**
  *This method allows the user the see what values 
  *in a given a array are bigger than the key entered.
  
  *@return b returns an array bigger than key.
  *@param a is the array you're comparing.
  *@param key The number that you want to compare to.
  
  */
 
   private static int[] arrayWBV(int[] a, int key) {
      int count = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] > key) {
            count++;
         }
      }
      int[] b = new int[count];
      int selectedNum = 0;
      for (int j = 0; j < a.length; j++) {
         if (a[j] > key) {
            b[selectedNum] = a[j];
            selectedNum++;
         }
      }
      return b;
   
   }
   
   /**
   *This method allows the user to return a new array
   *with only values smaller than the key entered.
   
   *@return b returns an array less than key.
   *@param a is the array you're comparing.
   *@num The number that you want to compare to.
   */
   
   private static int[] arrayWSV(int[] a, int key) {
      int count = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] < key) {
            count++;
         }
      }
      int[] b = new int[count];
      int selectedNum = 0;
      for (int j = 0; j < a.length; j++) {
         if (a[j] < key) {
            b[selectedNum] = a[j];
            selectedNum++;
         }
      }
      return b;
   
   }
 

}
