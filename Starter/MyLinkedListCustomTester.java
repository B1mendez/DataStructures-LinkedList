/**
 * Name: Brian Mendez
 * Email: b1mendez
 * Sources used: Zybook, LLIterator.java file in lecture, 
 * Public tester file.
 * 
 * 2-4 sentence file description here
 */

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.*;

/**
 * TODO: Add your class header
 * 
 * IMPORTANT: Do not change the method headers and points are awarded 
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class MyLinkedListCustomTester {
	private MyLinkedList<String> emptyStringList;
	private MyLinkedList<Integer> fiveIntegerList;
	private Integer[] intData = {1, 2, 3, 4, 5};

	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
	@Before
	public void setUp() throws Exception {
		emptyStringList = new MyLinkedList<String>(); 
		fiveIntegerList = new MyLinkedList<>(); 

	}

	private void populatedLinkList() {
		MyLinkedList<Integer>.Node node0 =  
			this.fiveIntegerList.new Node(this.intData[0]);
		MyLinkedList<Integer>.Node node1 =  
			this.fiveIntegerList.new Node(this.intData[1]);
		MyLinkedList<Integer>.Node node2 =  
			this.fiveIntegerList.new Node(this.intData[2]);
		MyLinkedList<Integer>.Node node3 =  
			this.fiveIntegerList.new Node(this.intData[3]);
		MyLinkedList<Integer>.Node node4 =  
			this.fiveIntegerList.new Node(this.intData[4]);

			this.fiveIntegerList.head.next = node0;
			node0.prev = this.fiveIntegerList.head;
			node0.next = node1;
			node1.prev = node0;
			node1.next = node2;
			node2.prev = node1;
			node2.next = node3;
			node3.prev = node2; 
			node3.next = node4;
			node4.next = this.fiveIntegerList.tail;
			this.fiveIntegerList.tail.prev = node3;
			this.fiveIntegerList.size = 5;
	}
	/**
	 * TODO: test the add method when data is null
	 */
	@Test
	public void testAdd() {
		try {
			emptyStringList.add(0,null);
			fail();
		} catch (NullPointerException e) {
			// pass
		}
	}

	/**
	 * TODO: test the add with index method when data is null
	 */
	@Test
	public void testAddWithIndexTestOne() {
		try {
			emptyStringList.add(0,null);
			fail();
		} catch (NullPointerException e) {
			// pass
		}
	}

	/**
	 * TODO: test the add with index method when list is full. 
	 */	
	@Test
	public void testAddWithIndexTestTwo() {
		this.fiveIntegerList.add(0, 12);
		assertEquals("Check head reference is updated",
			Integer.valueOf(12), this.fiveIntegerList.head.next.data);
		assertEquals("Check tail reference is updated", 
			Integer.valueOf(12), this.fiveIntegerList.tail.prev.data);
		assertEquals("Check size is updated", 6, this.fiveIntegerList.size);
	}

	/**
	 * TODO: test the get method when [...]
	 */
	@Test
	public void testGet() {
		try {
			emptyStringList.getNth(3);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// pass
		}
	}

	/**
	 * TODO: test the getNth method when index is out 
	 * of bounds
	 */
	@Test
	public void testGetNth() {
		try {
			emptyStringList.getNth(3);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// pass
		}
	}

	/**
	 * Test the set method when index out of bounds exception
	 */
	@Test
	public void testSet() {
		try {
			emptyStringList.set(0, "Brian");
			fail();
		} catch (IndexOutOfBoundsException e) {
			// pass
		}

	}

	/**
	 * Test the remove method when index out of bounds
	 */
	@Test
	public void testRemoveTestOne() {
		try {
			emptyStringList.remove(4);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// pass
		}

	}
	
	/**
	 * Test the remove method when index is not first 
	 * or last.
	 */
	@Test
	public void testRemoveTestTwo() {
		this.populatedLinkList();
		int removedData = this.fiveIntegerList.remove(3); 
		assertSame("Check the third element is returned correctly", 
			this.fiveIntegerList.head.next.next.next.next, 
			this.fiveIntegerList.getNth(3));
		assertSame("Check the third element is returned correctly", 
			this.fiveIntegerList.head.next.next.next, 
			this.fiveIntegerList.getNth(2));
		assertEquals("Removed node should be returned Correctly",
			removedData, 3);
		assertEquals("The size of the list should decrease to 4", 
			4, fiveIntegerList.size());
	}

	/**
	 * Test the clear method when list is empty.
	 * Keeping the sentinal node head and tail. 
	 */
	@Test
	public void testClear() {
		emptyStringList.clear(); 
		assertEquals("Size should be updated", 0, 
		emptyStringList.size());
		assertTrue("LinkedList should be empty", 
		emptyStringList.isEmpty());
		assertSame("Data stored at dummy head should be null", 
			null, this.emptyStringList.head.data);
		assertSame("Data stored at dummy tail should be null", 
			null, this.emptyStringList.tail.data);
		assertSame("Head should points to next", 
			this.emptyStringList.tail, this.emptyStringList.head.next);
		assertSame("Next should points to head", 
			this.emptyStringList.head, this.emptyStringList.tail.prev);
	}

	/**
	 * Test the size method when the list is full
	 * and not empty. 
	 */
	@Test
	public void testSize() {
		this.populatedLinkList();
		assertEquals("A full list should have a size of 5", 
			5, fiveIntegerList.size());
	}
}