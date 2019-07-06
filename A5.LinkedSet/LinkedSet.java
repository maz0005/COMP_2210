import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Marco Zuniga (maz0005@auburn.edu)
 * @version 2016-10-19
 *
 */
public class LinkedSet<T extends Comparable<? super T>> implements Set<T> {

   //////////////////////////////////////////////////////////
   // Do not change the following three fields in any way. //
   //////////////////////////////////////////////////////////

   /** References to the first and last node of the list. */
   Node front;
   Node rear;

   /** The number of nodes in the list. */
   int size;

   /////////////////////////////////////////////////////////
   // Do not change the following constructor in any way. //
   /////////////////////////////////////////////////////////

   /**
    * Instantiates an empty LinkedSet.
    */
   public LinkedSet() {
      front = null;
      rear = null;
      size = 0;
   }


   //////////////////////////////////////////////////
   // Public interface and class-specific methods. //
   //////////////////////////////////////////////////

   ///////////////////////////////////////
   // DO NOT CHANGE THE TOSTRING METHOD //
   ///////////////////////////////////////
   /**
    * Return a string representation of this LinkedSet.
    *
    * @return a string representation of this LinkedSet
    */
   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (T element : this) {
         result.append(element + ", ");
      }
      result.delete(result.length() - 2, result.length());
      result.append("]");
      return result.toString();
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
    * @return  true if this collection contains no elements, false otherwise.
    */
   public boolean isEmpty() {
      return (size == 0);
   }


   /**
    * Ensures the collection contains the specified element. Neither duplicate
    * nor null values are allowed. This method ensures that the elements in the
    * linked list are maintained in ascending natural order.
    *
    * @param  element  The element whose presence is to be ensured.
    * @return true if collection is changed, false otherwise.
    */
   public boolean add(T element) {
      if (contains(element) || element == null) {
         return false;
      }
      
      Node x = new Node(element); 
      //If it's empty 
      if (isEmpty()) {
         front = x;
         front.prev = null;
         rear = x;
         rear.next = null;
         size++;
         return true;
      }
      
      
      
    //If you need to put it in the end
      int count = 0;
      int index = floor(this, element);
      int num = 1;
      if (index == size) {
         Node t = rear;
         rear.next = x;
         x.prev = t;
         rear = x;
         rear.next = null;
         size++;
         return true;
                 
      }
      
      else {
         Node p = front;
         while (count < index) {
            p = p.next;
            count++;
         }
      
         Node n = new Node(element);
      //If it needs to be put in front
         if (p.prev == null) {
            n.next = p;
            front = n;
            p.prev = n;
            size++;
            return true;
         }
      
         if (p.prev != null) {
            Node prev = p.prev;
            prev.next = n;
            n.next = p;
            p.prev = n;
            n.prev = prev;
            size++;
         
            return true;
         }
      }
     
      return false;
   }
   
   private boolean addTo(T element) {
      Node n = new Node(element);
      if (this.isEmpty()) {
      
         this.front = n;
         this.rear = n;
         this.size++;
         front.prev = null;
         rear.next = null;
         return true;
      }
      
      Node t = rear;
      rear.next = n;
      n.prev = t;
      rear = n;
      rear.next = null;
      size++;
      return true;
    
   }

   private int floor(LinkedSet<T> set, T obj) {
   
      Iterator<T> itr = set.iterator();
      int count = 0;
      while (itr.hasNext() && set.front != null) {
         if (itr.next().compareTo(obj) > 0) {
            return count;
         }
         count++;
      }
      return count;
   
   }
   /**
    * Ensures the collection does not contain the specified element.
    * If the specified element is present, this method removes it
    * from the collection. This method, consistent with add, ensures
    * that the elements in the linked lists are maintained in ascending
    * natural order.
    *
    * @param   element  The element to be removed.
    * @return  true if collection is changed, false otherwise.
    */
   public boolean remove(T element) {
   
      if (!contains(element)) {
         return false;
      }
      if (isEmpty()) {
         return false;
      }
      
      if (element.compareTo(front.element) == 0 && size != 1) {
         front = front.next;
         front.prev = null;
         size--;
         return true;
      }
      if (element.compareTo(front.element) == 0 && size == 1) {
         front = null;
         rear = null;
         size--;
         return true;
      }
      
      if (element.compareTo(rear.element) == 0) {
         Node x = rear.prev;
         rear.prev = x;
         x.next = null;
         rear = x;
         size--;
         return true;
      }
      
      int index = locate(element);
      if (index == -1) {
         return false;
      }
      int count = 0;
      Node p = front;
      rear.next = null;
      while (count < index - 1) {
         p = p.next;
         count++;
      }
      
      if (p.next.next == null) {
         p.next = null;
         return true;
      }
      Node n = p.next.next;
      p.next = p.next.next;
      
      n.prev = p;
      size--;
      
      return true;
      
      
   }
   
   private int locate(T obj) {
      if (this == null || obj == null) {
         return -1;
      }
      
      
      Iterator<T> itr = this.iterator();
      int count = 0;
      T current = itr.next();
      while (itr.hasNext() && count <= size()) {
         
         if (current.compareTo(obj) == 0) {
            return count;
         }
         count++;
         current = itr.next();
      }
      
      if (count >= size() || current.compareTo(obj) != 0) {
         return -1;
      }
   
   
      return count;
   }


   /**
    * Searches for specified element in this collection.
    *
    * @param   element  The element whose presence in this collection is to be tested.
    * @return  true if this collection contains the specified element, false otherwise.
    */
   public boolean contains(T element) {
   
      if (this == null || element == null) {
         return false;
      }
      
      Node p = this.front;
      Iterator<T> itr = this.iterator();
      while (itr.hasNext() && p != null) {
         if (itr.next() == element) {
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
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(Set<T> s) {
      if (s.size() != this.size()) {
         return false;
      }
      Iterator<T> itr = this.iterator();
      
      int count = 0;
      while (itr.hasNext()) {
      
         T thisSet = itr.next();
         Iterator<T> itr2 = s.iterator();
         while  (itr2.hasNext()) {
         
            if (thisSet.compareTo(itr2.next()) == 0) {
               count++;
            }
         }
      }
      return count == size() ? true : false;
   }


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   public boolean equals(LinkedSet<T> s) {
      if (s.size() != this.size()) {
         return false;
      }
      Iterator<T> itr = this.iterator();
      Iterator<T> itr2 = s.iterator();
      while (itr.hasNext()) {
         T thisSet = itr.next();
         T sSet = itr2.next();
         if (thisSet.compareTo(sSet) != 0) {
            return false;
         }
      }
      return true;
   }


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(Set<T> s){
   
      if (this == null) {
         return s;
      }
      if (s == null) {
         return this;
      }
      
      LinkedSet<T> unionSet = new LinkedSet<T>();
     
      Iterator<T> itr = this.iterator();
      
      while (itr.hasNext()) {
         unionSet.add(itr.next());
      }
      
      
      Iterator<T> itr2 = s.iterator();
      
      while (itr2.hasNext()){
         unionSet.add(itr2.next());
      }
        
      return unionSet;
   }



   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   public Set<T> union(LinkedSet<T> s){
      if (this == null) {
         return s;
      }
      if (s == null) {
         return this;
      }
      LinkedSet<T> unionSet = new LinkedSet<T>();
      if (s == null || s.size() == 0) {
         return this;
      }
      if (this == null || size() == 0) {
         return s;
      }
      int leftOver = 0;
      int count = 0;
      int max = s.size() + s.size();
      int linkedCount = 0;
      int sCount = 0;
      Iterator<T> itr = this.iterator();
      Iterator<T> itr2 = s.iterator();
      T current = itr.next();
      T current2 = itr2.next();
      while (count < max) {
         
         if (current.compareTo(current2) > 0) {
            unionSet.addTo(current2);
            sCount++;
            if (itr2.hasNext()) {
               current2 = itr2.next();
            }
            count++;
         }
         if (current.compareTo(current2) < 0) {
            unionSet.addTo(current);
            linkedCount++;
            if (itr.hasNext()) {
               current = itr.next();
            }
            count++;
         }
         
         if (current.compareTo(current2) == 0) {
            unionSet.addTo(current);
            linkedCount++;
            sCount++;
            if (itr.hasNext()) {
               current = itr.next();
               
            }
            if (itr2.hasNext()) {
               current2 = itr2.next();
            }
            count+= 2;
         }
         
         
         
         if (linkedCount == size && s.size == sCount) {
            break;
         }
         if (linkedCount == size()) {
            leftOver = 1;
            break;
         }
         if (sCount == s.size()) {
            leftOver = 2;
            break;
         }
      }
      
       
      if (leftOver == 1) {
         //unionSet.addTo(current2);
         if (current2 != null) {
            unionSet.addTo(current2);
         }
         while (itr2.hasNext()) {
            unionSet.addTo(itr2.next());
            
         }
      }
      
      if (leftOver == 2) {
         if (current != null) {
            unionSet.addTo(current);
         }
         //unionSet.addTo(current);
         while (itr.hasNext()) {
            unionSet.addTo(itr.next());
         }
      }
      
      return unionSet;
      
   }


   /**
    * Returns a set that is the intersection of this set and the parameter set.
    *
    * @return  a set that contains elements that are in both this set and the parameter set
    */
   public Set<T> intersection(Set<T> s) {
      if (s == null || s.size() == 0) {
         return s;
      
      }
      if (front == null || this.size() == 0) {
         return this;
      }
      
     
      LinkedSet<T> intSet = new LinkedSet<T>();
      /*
       var will determine if an element will be
      added after each scan.
     */
      
      int var = -1;
      Iterator<T> itr = this.iterator();
      
      while (itr.hasNext()) {
         T current = itr.next();
       
         Iterator<T> itr2 = s.iterator();
         while (itr2.hasNext()) {
            
            T current2 = itr2.next();
            if (current.compareTo(current2) == 0) {
               var = 1;
               break;
            }
         }
         if (var == 1) {
            intSet.add(current);
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
   public Set<T> intersection(LinkedSet<T> s) {
      LinkedSet<T> intSet = new LinkedSet<T>();
      if (s == null || s.size() == 0) {
         return s;
      }
      if (this == null || size() == 0) {
         return this;
      }
      
     
      int leftOver = 0;
      int count = 0;
      int max = s.size() + s.size();
      int linkedCount = 0;
      int sCount = 0;
      Iterator<T> itr = this.iterator();
      Iterator<T> itr2 = s.iterator();
      T current = itr.next();
      T current2 = itr2.next();
      while (count < max) {
         
         if (current.compareTo(current2) > 0) {
            sCount++;
            if (itr2.hasNext()) {
               current2 = itr2.next();
            }
            count++;
         }
         if (current.compareTo(current2) < 0) {
            linkedCount++;
            if (itr.hasNext()) {
               current = itr.next();
            }
            count++;
         }
         
         if (current.compareTo(current2) == 0) {
            intSet.addTo(current);
            linkedCount++;
            sCount++;
            if (itr.hasNext() && itr2.hasNext()) {
               current = itr.next();
               current2 = itr2.next();
            }
            count+= 2;
         }
         
         if (linkedCount == size() || sCount == s.size()) {
            break;
         }
      }
      return intSet;
   
   }


   /**
    * Returns a set that is the complement of this set and the parameter set.
    *
    * @return  a set that contains elements that are in this set but not the parameter set
    */
   public Set<T> complement(Set<T> s) {
      LinkedSet<T> compSet = new LinkedSet<T>();
      if (this.isEmpty() || s == null || s.isEmpty()) {
         return this;
      }
      
     /*
      'value' will indicate whether the set 's'
      contained each element through linear seach.
     */ 
      int value = 1;
      Iterator<T> itr = this.iterator();
      
      while (itr.hasNext()) {
         T current = itr.next(); 
         Iterator<T> itr2 = s.iterator();
         
         while (itr2.hasNext()) {
            T current2 = itr2.next();
         
            if (current.compareTo(current2) == 0) {
               value = -1;
            }
            
         }
         if (value == 1) {
            compSet.add(current);
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
   public Set<T> complement(LinkedSet<T> s) {
      if (s.isEmpty()|| s == null || s.size == 0) {
         return this;
      }
      
      if (isEmpty()|| this == null) {
         return this;
      }
      
      
      LinkedSet<T> compSet = new LinkedSet<T>();
         
      int eCount = 0;
      int sCount = 0;
       
      int count = 0;
      Iterator<T> itr = this.iterator();
      Iterator<T> itr2 = s.iterator();
      T current = itr.next();
      T current2= itr2.next();
      
      while (eCount < this.size && sCount < s.size()) {
         if (current.compareTo(current2) < 0) {
            compSet.addTo(current);
            if (itr.hasNext()){
               current = itr.next();
            }
            eCount++;
         }
         
         
         else if (current.compareTo(current2) > 0) {
            if (itr2.hasNext()){
               current2 = itr2.next();
            }
            sCount++;
         }
         
         else if (current.compareTo(current2) == 0) {
            if (itr.hasNext()) {
               current = itr.next();
            }
            eCount++;
         }
         
         
         
      }
      
      if (eCount < this.size) {
         compSet.addTo(current);
         while (itr.hasNext()) {
            compSet.addTo(itr.next());
         
         }
      
      }
    
      
      
      return compSet;
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in ascending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> iterator() {
      return new LinkedListIterator();
   }


   /**
    * Returns an iterator over the elements in this LinkedSet.
    * Elements are returned in descending natural order.
    *
    * @return  an iterator over the elements in this LinkedSet
    */
   public Iterator<T> descendingIterator() {
      return new LinkedDescendingIterator();
   }



   /**
    * Returns an iterator over the members of the power set
    * of this LinkedSet. No specific order can be assumed.
    *
    * @return  an iterator over members of the power set
    */
   public Iterator<Set<T>> powerSetIterator() {
      return new powSetIterator();
   }
   
   private class powSetIterator implements Iterator<Set<T>> {
      private Node p;
      private int current;
    
      public powSetIterator() {
         p = front;
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
            LinkedSet<T> powSet = new LinkedSet<T>();
         
         Node x = p;
         
         int regularPositionCount = 0;
         for (int i = binary.length() - 1; i >= 0; i--) {
            Node currentValue = x;
          
            
            if (binary.charAt(i) == '1') {
               powSet.addTo(currentValue.element);
            }
            if (currentValue != null) {
               x = x.next;
            }
         }
         
         
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



   //////////////////////////////
   // Private utility methods. //
   //////////////////////////////

   // Feel free to add as many private methods as you need.

   ////////////////////
   // Nested classes //
   ////////////////////

   //////////////////////////////////////////////
   // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
   //////////////////////////////////////////////

   /**
    * Defines a node class for a doubly-linked list.
    */
   class Node {
      /** the value stored in this node. */
      T element;
      /** a reference to the node after this node. */
      Node next;
      /** a reference to the node before this node. */
      Node prev;
   
      /**
       * Instantiate an empty node.
       */
      public Node() {
         element = null;
         next = null;
         prev = null;
      }
   
      /**
       * Instantiate a node that containts element
       * and with no node before or after it.
       */
      public Node(T e) {
         element = e;
         next = null;
         prev = null;
      }
   }

   private class LinkedListIterator implements Iterator<T> {
   
   // the array of elements to iterate over
      private Node p;
   // the number of elements in the array, beginning at index zero
     
   // the index of the next element in the iteration sequence
     
   
   /**
    * Construct a properly initialized iterator.
    *
    * @param  elem the array to be iterated over
    * @param  size the number of elements in the array
    */
      public LinkedListIterator() {
         p = front;
      }
   
   /**
    * Returns true if there is at least one more element remaining
    * in the iteration sequence.
    *
    * @return true if there is a next element to iterate over
    */
      public boolean hasNext() {
      
         return p != null;
      }
   
   /**
    * Returns the next element in the iteration sequence.
    * @return the next element in the iteration sequence
    */
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         
         
         
         T newElement = p.element;
         
         p = p.next;
         return newElement;
         
      }
      
       /**
    * Unsupported operation.
    */
      public void remove() {
         throw new UnsupportedOperationException();
      }
   } 
   private class LinkedDescendingIterator implements Iterator<T> {
   
      private Node p;
   
   /**
    * Construct a properly initialized iterator.
    *
    * @param  elem the array to be iterated over
    * @param  size the number of elements in the array
    */
    
      public LinkedDescendingIterator() {
         p = rear;
      }
   
   /**
    * Returns true if there is at least one more element remaining
    * in the iteration sequence.
    *
    * @return true if there is a next element to iterate over
    */
    
      public boolean hasNext() {
         return p != null;
      }
   
   /**
    * Returns the next element in the iteration sequence.
    * @return the next element in the iteration sequence
    */
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         
         
         
         T newElement = p.element;
         
         p = p.prev;
         return newElement;
      }
      
       /**
    * Unsupported operation.
    */
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }


}