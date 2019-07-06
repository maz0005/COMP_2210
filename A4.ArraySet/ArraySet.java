import java.util.Iterator; import java.util.NoSuchElementException;
import java.util.Arrays;
/**  
* ArraySet.java.  
* 
 * Provides an implementation of the Set interface using an 
  * array as the underlying data structure. Values in the array  
  * are kept in ascending natural order and, where possible, 
   * methods take advantage of this. Many of the methods with parameters  
   * of type ArraySet are specifically designed to take advantage 
    * of the ordered array implementation.  
    *  * @author Marco Zuniga (maz0005@auburn.edu) 
     * @author Dean Hendrix (dh@auburn.edu)  
     * @version 2016-09-29 
      *  
      */ 
public class ArraySet<T extends Comparable<? super T>> implements Set<T> {
   ////////////////////////////////////////////    
   // DO NOT CHANGE THE FOLLOWING TWO FIELDS //   
    ////////////////////////////////////////////   
   T[] elements;    
   int size;
   private T[] aux;
   ////////////////////////////////////   
    // DO NOT CHANGE THIS CONSTRUCTOR //    
    ////////////////////////////////////    
    /**  
     * Instantiates an empty set.     
     */   
   @SuppressWarnings("unchecked")   
    public ArraySet() {       
      elements = (T[]) new Comparable[1];       
      size = 0;    
   }
   /** 
   * Instantiates a set with a specicified size.
   *
   */
   @SuppressWarnings("unchecked")
   private ArraySet(int size) {
   
      elements = (T[]) new Comparable[size];
      this.size = size;
   
   }
   
   /**
   * Instantiates a set with a specified array
   * and its size.
   *
   */
   
 
   @SuppressWarnings("unchecked")
   private ArraySet(T[] array, int size) {
   
      elements = (T[]) new Comparable[size];
      System.arraycopy(array, 0, elements, 0, size);
      this.size = size;
   }
   
      
 
   ///////////////////////////////////    
   // DO NOT CHANGE THE SIZE METHOD //    
   ///////////////////////////////////    
   /**     
   * Returns the current size of this collection.    
    *     
    * @return  the number of elements in this collection.   
      */  

   
    
   public int size() {       
      return size;    
   }
   //////////////////////////////////////    
   // DO NOT CHANGE THE ISEMPTY METHOD //    
   //////////////////////////////////////   
    /**     
    * Tests to see if this collection is empty.     
    *     
    * @return  true if this collection contains no elements,     
    *  false otherwise.     
    */    
   public boolean isEmpty() {      
      return size == 0;   
   }
   ///////////////////////////////////////

   // DO NOT CHANGE THE TOSTRING METHOD //    
   ///////////////////////////////////////    
   /**     
   * Return a string representation of this ArraySet.     
   *     
   * @return a string representation of this ArraySet    
    */   
   @Override   
    public String toString() {      
      if (isEmpty()) {          
         return "[]";       }      
      StringBuilder result = new StringBuilder();       
      result.append("[");       
      for (T element : this) {         
         
         
         result.append(element + ", ");       
      }      
      result.delete(result.length() - 2, result.length());       
      result.append("]");      
      return result.toString();    
   }
    /**     
     * Ensures the collection contains the specified element. Elements are     
      * maintained in ascending natural order at all times. Neither duplicate nor     
       * null values are allowed.      
       *   
       * floor method is used to find where the new element needs to go.
          
       * @param  element  The element whose presence is to be ensured.      
       * @return true if collection is changed, false otherwise.     
        */    
        
   public boolean add(T element) { 
      if (element == null) {
         return false;
      }
      
      if (contains(element)) {
         return false;
      }
    
      if (size == elements.length) {
         resize(elements.length * 2);
      }
      
      int finder = 0;
      while (finder < size && element.compareTo(elements[finder]) > 0) {
         finder++;
      }
      
      for (int j = size; j > finder; j--) {
         elements[j] = elements[j - 1];
      }
     
      
      elements[finder] = element;
      size++;
      return true;
   
   }
 
  
  
    
   
   private void resize(int newSize) {
      assert newSize > 0;
      @SuppressWarnings("unchecked")
         T[] newArray = (T[]) new Comparable[newSize];
      System.arraycopy(elements, 0, newArray, 0, size);
      elements = newArray;
   }
   
    /**    
    * Searches for specified element in this collection.     
    *     
    * @param   element  The element whose presence in this collection    
    * is to be tested.     
    * @return  true if this collection contains the specified element,     
    *  false otherwise.    
    */   
       
   public boolean contains(T element) {   
   
      if (this.isEmpty() || element == null) {
         return false;
      }
      
      if (this.elements[0] == element) {
         return true;
      }
   
      int left = 0;
      int right = size - 1;
      while (left <= right) {
      
        
      
         int middle = (left + right) / 2;
         int comparedValue = element.compareTo(elements[middle]);
       
         if (comparedValue < 0) {
            right = middle - 1;
         }
         if (comparedValue > 0) {
            left = middle + 1;
         }
         //This is to make sure the last value is indeed equal.
         if (comparedValue == 0) {
            return true;
         }
      }
      return false;
      
       
   }

   /**     
   * Tests for equality between this set and the parameter set.     
   * Returns true if this set contains exactly the same elements     
   * as the parameter set, regardless of order.     
   *

    * @return  true if this set contains exactly the same elements    
     *               as the parameter set, false otherwise     
     */    
   public boolean equals(Set<T> s) { 
   
      if (elements == null || s == null || s.size() != size())  {   
         return false;  
      }
      
      Iterator<T> itrS = s.iterator();
   
      while (itrS.hasNext()) {
         if (!contains(itrS.next())) {
            return false;
         }
      
      }
      return true;  
      
   }

   
    /**     
   * Tests for equality between this set and the parameter set.     
   * Returns true if this set contains exactly the same elements     
   * as the parameter set, regardless of order.     
   *    
    * @return  true if this set contains exactly the same elements     
    * as the parameter set, false otherwise     
    */    
   public boolean equals(ArraySet<T> s) { 
   
      if (elements == null || s == null || size != s.size())  {   
         return false;  
      }
      
      for (int i = 0; i < size; i++) {
         if(elements[i].compareTo(s.elements[i]) != 0) {
            return false; 
         
         }
         
      }
      return true;
     
   }
   
   /**
     * Ensures the collection does not contain the specified element.      
     * If the specified element is present, this method removes it     
      * from the collection. Elements are maintained in ascending natural     
       * order at all times.     
        *      
        * @param   element  The element to be removed.      
        * @return  true if collection is changed, false otherwise.     
         */ 
           
   public boolean remove(T element) {  
      if (this.size() == 0) {
         return false;
      }
   
       
   
      int loc = locate(element);
      if (loc == -1 || loc >= size) { 
         return false;
      }
      
      if (this.size == 1 && elements[0] != element) {
         return false;
      } 
      
      if (this.size() == 1 && elements[0] == element) {
         elements[0] = null;
         size--;
         return true;
      }
      
      for (int i = loc; i < size - 1; i++) {
         elements[i] = elements[i + 1];
      }
         
      elements[size - 1] = null;
      
      
      size--;
      
      if(size > 0 && size < elements.length / 4) {
         resize(elements.length / 2) ;
      }
      return true;
      
   }
   
   public Iterator<T> iterator() {
   
      // ALMOST ALL THE TESTS DEPEND ON THIS METHOD WORKING CORRECTLY.       
      // MAKE SURE YOU GET THIS ONE WORKING FIRST.       
      // HINT: JUST USE THE SAME CODE/STRATEGY AS THE ARRAYBAG CLASS       
      // FROM LECTURE. THE ONLY DIFFERENCE IS THAT YOU'LL NEED THE       
      // ARRAYITERATOR CLASS TO BE NESTED, NOT TOP-LEVEL.
     
      return new ArrayIterator<T>(elements, size);  
        
      
   }
   
   private class ArrayIterator<T> implements Iterator<T> {
   
   // the array of elements to iterate over
      private T[] elements;
   // the number of elements in the array, beginning at index zero
      private int size;
   // the index of the next element in the iteration sequence
      private int current;
   
   /**
    * Construct a properly initialized iterator.
    *
    * @param  elem the array to be iterated over
    * @param  size the number of elements in the array
    */
      public ArrayIterator(T[] elem, int length) {
         elements = elem;
         size = length;
         current = 0;
      }
   
   /**
    * Returns true if there is at least one more element remaining
    * in the iteration sequence.
    *
    * @return true if there is a next element to iterate over
    */
      public boolean hasNext() {
         return current < size;
      }
   
   /**
    * Returns the next element in the iteration sequence.
    * @return the next element in the iteration sequence
    */
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         
         
         return elements[current++];
         
      }
      
       /**
    * Unsupported operation.
    */
      public void remove() {
         throw new UnsupportedOperationException();
      }
   } 
   
    /**     
   * Returns a set that is the union of this set and the parameter set.     
   *     
   * @return  a set that contains all the elements of this set and     
   * the parameter set     
   */ 
   
   public Set<T> union(Set<T> s) 
   {  
   
      ArraySet<T> unionSet = new ArraySet<T>();
      for (T a : elements) {
         unionSet.add(a);
      }
      
      if(s != null){
         for (T b : s) {
            unionSet.add(b);
         }
      }
        
      return unionSet;
      
   }
   
    /**     
   * Returns a set that is the union of this set and the parameter set.     
   *    
    * @return  a set that contains all the elements of this set and    
     *            the parameter set    
      */  
   
   public Set<T> union(ArraySet<T> s) { 
   
   
     // int arraySize = this.countForUnion(s);
      int max = this.size + s.size();
     
      @SuppressWarnings("unchecked")
         T[] unionArray = (T[]) new Comparable[max];
     
      
      int remainingArray = 0;
      
    /*  
      Num will describe which array ran out of elements first.
      eCount is the current index of the element being examined
      in elements
      sCount is the current index of the element being examined 
      in s.
      unionSetSize is the current index where a new element will be added.
      helpful with the addTo() method.
      */
     
      int num = 0;
      int eCount = 0;
      int sCount = 0;
      int unionSetSize = 0;
      int index = 0;
      
      
      for (int i = 0; i < max; i++) {
       //If either are null;
         if (eCount == size || sCount == s.size() || elements[eCount] == null || s.elements[sCount] == null) {
            if (eCount == size || sCount == s.size()){
               if (eCount == size) {
                  index = sCount;
                  num = 1;
                  break;
               }
               else {
                  num = 2;
                  break;
               }
            }
            else {
               if (elements[eCount] == null) {
                  eCount++;  
               }
               else {
                  sCount++;
               }
            }
         }
         //If elements is smaller than s when comparing
         else if(elements[eCount].compareTo(s.elements[sCount]) < 0) {
            unionArray[remainingArray] = elements[eCount];
            eCount++;
            unionSetSize++;
            remainingArray++;
         }
         //If s is smaller than elements when comparing
         else if(elements[eCount].compareTo(s.elements[sCount]) > 0) {
            unionArray[remainingArray] = s.elements[sCount];
            sCount++;
            unionSetSize++;
            remainingArray++;
         }
          // If they are both equal
         else {
            unionArray[remainingArray] = elements[eCount];
            eCount++;
            sCount++;
            unionSetSize++;
            remainingArray++;
         }
      }
      if (num == 1) {
         for (int i = sCount; i < s.size(); i++) {
            unionArray[remainingArray] = s.elements[i];
            unionSetSize++;
            remainingArray++;
         
         }
      }
      if (num == 2) {
         for (int i = eCount; i < this.size; i++) {
            unionArray[remainingArray] = elements[i];
            unionSetSize++;
            remainingArray++;
         }
      }
      
     
      ArraySet<T> unionSet = new ArraySet<T>(unionArray, remainingArray);
        
      
      return unionSet;
    
      
      
   }

   /**    
    * Returns a set that is the intersection of this set     
    * and the parameter set.     
    *     
    * @return  a set that contains elements that are in both     
    *   this set and the parameter set     
    */
    /** 
    
        
   */
   
   public Set<T> intersection(Set<T> s) { 
   
      if (s == null || s.size() == 0) {
         return s;
      
      }
      
      if (size == 0 || elements == null) {
         return this;
      }
      
     
      ArraySet<T> intSet = new ArraySet<T>();
      /*
       var will determine if an element will be
      added after each scan.
     
     */
      
      int var = -1;
      for (int i = 0; i < this.size; i++) {
         Iterator<T> itr = s.iterator();
         
         while (itr.hasNext()) {
            T current = itr.next();
            if(this.elements[i].compareTo(current) == 0) {
               var = 1;
            }
         }
         if (var == 1) {
            intSet.add(this.elements[i]);
         }
         var = -1;
      }
      return intSet;
     
      
   }
   
         /**     
   * Returns a set that is the intersection of this set and     
   * the parameter set.     
   *     
   * @return  a set that contains elements that are in both     
   *            this set and the parameter set     
   */   
   @SuppressWarnings("unchecked")
   public Set<T> intersection(ArraySet<T> s) {
      if (s == null || s.size() == 0) {
         return s;
      
      }
      
      if (size == 0 || elements == null) {
         return this;
      }
      if (isEmpty()) {
         return this;
      }
      T[] intArray = (T[]) new Comparable[s.size + this.size];
      
      
      int eCount = 0;
      int sCount = 0;
     /* 
       'count' will keep track as to where each new element added 
      to intArray will go
     */ 
      int count = 0;
      
      int finalSize = 0;
      while (eCount < this.size && sCount < s.size) {
         if (s.elements[sCount].compareTo(this.elements[eCount]) < 0) {
            sCount++;
         }
         else if (s.elements[sCount].compareTo(this.elements[eCount]) > 0) {
            eCount++;
         }
         else if (s.elements[sCount].compareTo(this.elements[eCount]) == 0) {
            intArray[count++] = s.elements[sCount++];
            
            eCount++;
            finalSize++;
         } 
      }
      ArraySet<T> intSet = new ArraySet<T>(intArray, finalSize);
           
      return intSet;
      
   }
  

    /**     
   * Returns a set that is the complement of this set and
* the parameter set.     
*    
 * @return  a set that contains elements that are in this     
 *   set but not the parameter set     
 */   
   public Set<T> complement(Set<T> s) {  
      
      ArraySet<T> compSet = new ArraySet<T>();
      if (this.isEmpty() || s == null || s.isEmpty()) {
         return this;
      }
      
     /*
      'value' will indicate whether the set 's'
      contained each element through linear seach.
     */ 
      int value = 1;
      for (int i = 0; i < this.size; i++) {
         Iterator<T> itr = s.iterator();
         
         while (itr.hasNext()) {
            T current = itr.next();
         
            if (elements[i].compareTo(current) == 0) {
               value = -1;
            }
            
         }
         if (value == 1) {
            compSet.add(elements[i]);
         }
         value = 1;
      }
      
      return compSet;
        
   }
   
         /**     
   * Returns a set that is the complement of this set and     
   * the parameter set.     
   *     
   * @return  a set that contains elements that are in this    
    *            set but not the parameter set    
     */   
   public Set<T> complement(ArraySet<T> s) {   
   
      if (s.isEmpty()|| s == null || s.size == 0) {
         return this;
      }
      
      
      
      @SuppressWarnings("unchecked")
         T[] compArray = (T[]) new Comparable[s.size + this.size];
         
      int eCount = 0;
      int sCount = 0;
       
      int count = 0;
      int finalSize = 0;
      
      while (eCount < this.size && sCount < s.size()) {
         if (this.elements[eCount].compareTo(s.elements[sCount]) < 0) {
            compArray[count++] = this.elements[eCount++];
            finalSize++;
         }
         
         
         else if (this.elements[eCount].compareTo(s.elements[sCount]) > 0) {
            sCount++;
         }
         
         else if (this.elements[eCount].compareTo(s.elements[sCount]) == 0) {
            eCount++;
         }
         
         
         
      }
      
      if (eCount < this.size) {
         for (int i = eCount; i < size; i++) {
            compArray[count++] = this.elements[i];
            finalSize++;
         
         }
      
      }
     
      
      ArraySet<T> compSet = new ArraySet<T>(compArray, finalSize);
      
      
      return compSet;
      
      
   }
   
    /**    
    * Returns an iterator over the elements in this ArraySet.     
    * No specific order can be assumed.    
     *    
      * @return  an iterator over the elements in this ArraySet    
       */    
       /**     
   * Returns an iterator over the elements in this ArraySet.    
    * The elements are returned in descending order.     
    *    
     * @return  an iterator over the elements in this ArraySet     
     */   
   public Iterator<T> descendingIterator() {  
      
      return new desArrayIterator<T>(elements, size);
      
   }
   
   
   
   private class desArrayIterator<T> implements Iterator<T> {
   
   // the array of elements to iterate over
      private T[] elements;
   // the number of elements in the array, beginning at index zero
      private int count;
   // the index of the next element in the iteration sequence
      private int current;
   
   /**
    * Construct a properly initialized iterator.
    *
    * @param  elem the array to be iterated over
    * @param  size the number of elements in the array
    */
    
      public desArrayIterator(T[] elem, int size) {
         elements = elem;
         count = 0;
         current = size - 1;
      }
   
   /**
    * Returns true if there is at least one more element remaining
    * in the iteration sequence.
    *
    * @return true if there is a next element to iterate over
    */
    
      public boolean hasNext() {
         return current >= count;
      }
   
   /**
    * Returns the next element in the iteration sequence.
    * @return the next element in the iteration sequence
    */
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         
         
         return elements[current--];
      }
      
       /**
    * Unsupported operation.
    */
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
   
   private boolean isFull() {
      return size() == elements.length;
   }
   
   
    /**    
    * Returns an iterator over the members of the power set     
    * of this ArraySet. No specific order can be assumed.     
    *     * @return  an iterator over members of the power set     
    */  
     
    /*
    Any set is also a subset of its self.
    Ex:
    1, 2, 3
    1, 2, 3 is also a subset of the example above 
    */
   @SuppressWarnings("unchecked")
   public Iterator<Set<T>> powerSetIterator() {    
    
      return new powSetIterator(elements, size);  
     
   }
   
   private class powSetIterator implements Iterator<Set<T>> {
      private T[] elements;
      private int size;
      private int current;
    
      public powSetIterator(T[] elem, int size) {
         elements = elem;
         this.size = size;
         current = 0;
      
      }
      
      public boolean hasNext() {
         return current < (Math.pow(2, size));
      }
   
   /**
    * Returns the next element in the iteration sequence.
    * @return the next element in the iteration sequence
    */
      public Set<T> next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         
         String binary = Integer.toBinaryString(current);
         
         @SuppressWarnings("unchecked")
            T[] powArray = (T[]) new Comparable[ (int) (Math.pow(2, size))];
         Set<T> newSet = new ArraySet<T>();
         int powInt = 0;
         
         int regularPositionCount = 0;
         for (int i = binary.length() - 1; i >= 0; i--) {
            if (binary.charAt(i) == '1') {
               powArray[powInt] = elements[regularPositionCount];
               powInt++;
            }
            regularPositionCount++;
         
         }
         ArraySet<T> powSet = new ArraySet<T>(powArray, powInt);
         
         current++;
         
         return powSet;
      }
       /**
    * Unsupported operation.
    */
      public void remove() {
         throw new UnsupportedOperationException();
      }
      
   }
   
        
 
   
   
        
  
        
        
  
   
 

 /******************************************
 ***Extract mehtods in alphabetical order***
 ******************************************/
     
    

   
     
   private int locate(T target) {
     
     
      if (size == 0) {
         return -1;
      
      }
      
      if (this.elements[0] == target) {
         return 0;
      }
      
      int left = 0;
      int right = size - 1;
      while (left <= right) {
         int middle = (left + right) / 2;
       
         if (this.elements[middle].compareTo(target) > 0) {
            right = middle - 1;
         }
         if (this.elements[middle].compareTo(target) < 0) {
            left = middle + 1;
         }
         else {
            return middle;
         }
      }
      return -1;
   } 



   
 

   
     
  
   
   
   
     
}

