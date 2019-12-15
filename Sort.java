import java.util.Comparator;

/**
 * Class for sorting lists that implement the IndexedUnsortedList interface,
 * using ordering defined by class of objects in list or a Comparator.
 * As written uses Mergesort algorithm.
 *
 * @author CS221
 */
public class Sort
{	
	/**
	 * Returns a new list that implements the IndexedUnsortedList interface. 
	 * As configured, uses WrappedDLL. Must be changed if using 
	 * your own IUDoubleLinkedList class. 
	 * 
	 * @return a new list that implements the IndexedUnsortedList interface
	 */
	private static <T> IndexedUnsortedList<T> newList() 
	{
		return new WrappedDLL<T>(); //TODO: replace with your IUDoubleLinkedList for extra-credit
	}
	
	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @see IndexedUnsortedList 
	 */
	public static <T extends Comparable<T>> void sort(IndexedUnsortedList<T> list) 
	{
		mergesort(list);
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using given Comparator.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 * @see IndexedUnsortedList 
	 */
	public static <T> void sort(IndexedUnsortedList <T> list, Comparator<T> c) 
	{
		mergesort(list, c);
	}
	
	/**
	 * Mergesort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface, 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 */
	private static <T extends Comparable<T>> void mergesort(IndexedUnsortedList<T> list)
	{
		// TODO: Implement recursive mergesort algorithm 
		if(list.size() <=1) // already sorted if only one element
		{
			return;
		}
		int mid = (list.size())/2; // midpoint to divide list in two
		IndexedUnsortedList<T> leftList = newList(); // container for left side of list
		IndexedUnsortedList<T> rightList = newList(); // container for right side of list
		
		for(int i = 0; i < mid; i++)
		{
			leftList.addToFront(list.removeFirst()); // add to left list until midpoint
		}
		while(!list.isEmpty())
		{
			rightList.addToFront(list.removeFirst()); // add rest of elements in list to right list
		}
		if(leftList.size() != 0 && leftList.size() != 1) // if leftlist has more than one element
		{
			mergesort(leftList); // divide until one element 
			if(rightList.size() !=0 && rightList.size() != 1)
			{
				mergesort(rightList); // divide until one element
			}
			list = merge(list, leftList, rightList); // merge list back
		}
		if(rightList.size() !=0 && rightList.size() != 1) // if rightList has more than one lement
		{
			mergesort(rightList); // divide until one element
			if(leftList.size() != 0 && leftList.size() != 1)
			{
				mergesort(leftList); // divide until one element
			}
			list = merge(list, leftList, rightList); // merge list back
		}
		if(leftList.size() == 1 && rightList.size() == 1) // if both contain one element
		{
			list =merge(list, leftList, rightList); // merge into one list
		}
		
		
	}
		
	/**
	 * Merge algorithm to merge two lists  
	 * that implements the IndexedUnsortedList interface,
	 * using the given Comparator.
	 * 
	 * @param list0
	 *            The list to be merged into, implements IndexedUnsortedList interface 
     * @param list1
	 *            leftList to be merged into list0, implements IndexedUnsortedList interface 
	 * @param list1
	 *            rightList to be merged into list0, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 */
	private static <T> IndexedUnsortedList<T> mergeComp(IndexedUnsortedList<T>list0, IndexedUnsortedList<T>list1,IndexedUnsortedList<T> list2, Comparator<T> c)
	{
		while (!list1.isEmpty() && !list2.isEmpty()) //if both left and right list have elements
		{
			
			if(c.compare(list1.first(), list2.first())<= 0) // if list1 first element is smaller
			{
				list0.addToRear(list1.removeFirst()); // add to rear of list 1
			}
			else
			{
				list0.addToRear(list2.removeFirst()); // otherwise add list 2 first element to rear
			}
		}
		while ( !list1.isEmpty()) // if list 1 is not empty but list 2 is
		{
			list0.addToRear(list1.removeFirst()); // add rest of list 1 elements to end
		}
		while (!list2.isEmpty()) // if list 2 is not empty and list 1 is
		{
			list0.addToRear(list2.removeFirst()); // add rest of list 2 to rear
		}
		return list0; // return merged list
	}
	
	/**
	 * Merge algorithm to merge two lists  
	 * that implements the IndexedUnsortedList interface,
	 * using the given Comparator.
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 */
	private static <T> void mergesort(IndexedUnsortedList<T> list, Comparator<T> c)
	{
		// TODO: Implement recursive mergesort algorithm using Comparator
		if(list.size() <=1) // list with only one element (or none) is sorted
		{
			return;
		}
		int mid = (list.size())/2; // establish midpoint to divide list in two
		IndexedUnsortedList<T> leftList = newList(); // list to hold left half
		IndexedUnsortedList<T> rightList = newList(); // list to hold right half
		
		for(int i = 0; i < mid; i++)
		{
			leftList.addToFront(list.removeFirst()); // populate left half
		}
		while(!list.isEmpty())
		{
			rightList.addToFront(list.removeFirst()); // populate right half
		}
		if(leftList.size() != 0 && leftList.size() != 1)
		{
			mergesort(leftList, c); // divide list until one element
			if(rightList.size() !=0 && rightList.size() != 1)
			{
				mergesort(rightList, c); // divide list until one element
			}
			list = mergeComp(list, leftList, rightList, c); // merge lists
		}
		if(rightList.size() !=0 && rightList.size() != 1)
		{
			mergesort(rightList, c); // divide list until one element
			if(leftList.size() != 0 && leftList.size() != 1)
			{
				mergesort(leftList, c); // divide list until one element
			}
			list =mergeComp(list, leftList, rightList, c); // merge lists
		}
		if(leftList.size() == 1 && rightList.size() == 1)
		{
			list =mergeComp(list, leftList, rightList, c); // merge lists
		}

	}
	/**
	 * Merge algorithm to merge two lists  
	 * that implements the IndexedUnsortedList interface,
	 * using Comparable.
	 * 
	 * @param list0
	 *            The list to be merged into, implements IndexedUnsortedList interface 
     * @param list1
	 *            leftList to be merged into list0, implements IndexedUnsortedList interface 
	 * @param list1
	 *            rightList to be merged into list0, implements IndexedUnsortedList interface 
	 */
	private static <T extends Comparable<T>> IndexedUnsortedList<T> merge(IndexedUnsortedList<T>list0, IndexedUnsortedList<T>list1,IndexedUnsortedList<T> list2)
	{
		while (!list1.isEmpty() && !list2.isEmpty())
		{
			if(list1.first().compareTo(list2.first())<= 0) 
			{
				list0.addToRear(list1.removeFirst()); // add first element of leftList to rear of merged list if smaller than first element of rightList 
			}
			else
			{
				list0.addToRear(list2.removeFirst()); // add first element of rightList to rear of merged list if smaller than first element of leftList
			}
		}
		while ( !list1.isEmpty()) // if rightList is empty and leftList isn't add all elements of leftList to rear of merged list
		{
			list0.addToRear(list1.removeFirst());
		}
		while (!list2.isEmpty()) // if list 1 is empty and list2 isn't add all of rightList elements to merged list
		{
			list0.addToRear(list2.removeFirst());
		}
		return list0;
	}



}