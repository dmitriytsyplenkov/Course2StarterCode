/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		try {
			emptyList.remove(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		try {
			emptyList.remove(7);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		try {
			shortList.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		try {
			shortList.remove(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		try {
			emptyList.add(null);
			fail("Check out of null input");
		}
		catch (NullPointerException e) {
		}
		assertEquals("Check adding in empty list", true, emptyList.add(7));
		assertEquals("Check adding in non-empty list", true, shortList.add("C"));
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("Check size of empty list", 0, emptyList.size());
		assertEquals("Check size of non-empty list", 2, shortList.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		try {
			longerList.add(0,null);
			fail("Check for null input");
		}
		catch (NullPointerException e) {
		}

		try {
			longerList.add(-1,8);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		try {
			emptyList.add(1,8);
			fail("Check out of bounds for empty list");
		}
		catch (IndexOutOfBoundsException e) {
		}


		emptyList.add(0, 7);
		assertEquals("adding in empty list check 0 element is correct", (Integer)7, emptyList.get(0));

		shortList.add(1, "C");
		assertEquals("adding in the end of non-list check element 0 is correct", "A", shortList.get(0));
		assertEquals("adding in the end of non-list check element 1 is correct", "C", shortList.get(1));
		assertEquals("adding in the end of non-list check element 2 is correct", "B", shortList.get(2));
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		try {
			longerList.set(0, null);
			fail("Check for null input");
		}
		catch (NullPointerException e) {
		}

		try {
			longerList.set(-1, 8);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		try {
			emptyList.set(0, 8);
			fail("Check out of bounds for empty list");
		}
		catch (IndexOutOfBoundsException e) {
		}



		shortList.set(1, "C");
		assertEquals("setting in the end of non-list check element 1 is correct", "C", shortList.get(1));
		shortList.set(0, "D");
		assertEquals("setting in the begining of non-list check element 1 is correct", "D", shortList.get(0));
	}
	
	
	// TODO: Optionally add more test methods.
	
}
