/** */
package com.zeroonejourney.textgen;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for MyLinkedList.
 *
 * @author UC San Diego MOOC team
 */
public class MyLinkedListTest {

  private static final int LONG_LIST_LENGTH = 10;

  MyLinkedList<String> shortList;
  MyLinkedList<Integer> emptyList;
  MyLinkedList<Integer> longerList;
  MyLinkedList<Integer> list1;

  @Before
  public void setUp() {
    // Feel free to use these lists, or add your own
    shortList = new MyLinkedList<>();
    shortList.add("A");
    shortList.add("B");
    emptyList = new MyLinkedList<>();
    longerList = new MyLinkedList<>();
    for (int i = 0; i < LONG_LIST_LENGTH; i++) {
      longerList.add(i);
    }
    list1 = new MyLinkedList<>();
    list1.add(65);
    list1.add(21);
    list1.add(42);
  }

  /**
   * Test if the get method is working correctly. You should not need to add much to this method. We
   * provide it as an example of a thorough test.
   */
  @Test
  public void testGet() {
    // test empty list, get should throw an exception
    try {
      emptyList.get(0);
      fail("Check out of bounds");
    } catch (IndexOutOfBoundsException ignored) {

    }

    // test short list, first contents, then out of bounds
    assertEquals("Check first", "A", shortList.get(0));
    assertEquals("Check second", "B", shortList.get(1));

    try {
      shortList.get(-1);
      fail("Check out of bounds");
    } catch (IndexOutOfBoundsException ignored) {

    }
    try {
      shortList.get(2);
      fail("Check out of bounds");
    } catch (IndexOutOfBoundsException ignored) {

    }
    // test longer list contents
    for (int i = 0; i < LONG_LIST_LENGTH; i++) {
      assertEquals("Check " + i + " element", (Integer) i, longerList.get(i));
    }

    // test off the end of the longer array
    try {
      longerList.get(-1);
      fail("Check out of bounds");
    } catch (IndexOutOfBoundsException ignored) {

    }
    try {
      longerList.get(LONG_LIST_LENGTH);
      fail("Check out of bounds");
    } catch (IndexOutOfBoundsException ignored) {

    }
  }

  /**
   * Test removing an element from the list. We've included the example from the concept challenge.
   * You will want to add more tests.
   */
  @Test
  public void testRemove() {
    int a = list1.remove(0);
    assertEquals("Remove: check a is correct ", 65, a);
    assertEquals("Remove: check element 0 is correct ", (Integer) 21, list1.get(0));
    assertEquals("Remove: check size is correct ", 2, list1.size());

    int number4 = longerList.remove(4);
    assertEquals("Remove: check removed return is correct", 4, number4);
    assertEquals("Remove: check element 3 is correct", (Integer) 3, longerList.get(3));
    assertEquals("Remove: check element 4 is correct", (Integer) 5, longerList.get(4));
    assertEquals("Remove: check element 5 is correct", (Integer) 6, longerList.get(5));

    try {
      longerList.remove(-1);
      fail("Check index out of bound");
    } catch (IndexOutOfBoundsException ignored) {

    }
  }

  /** Test adding an element into the end of the list, specifically public boolean add(E element) */
  @Test
  public void testAddEnd() {
    // Check adding a null element
    try {
      emptyList.add(null);
      fail("Check null element");
    } catch (NullPointerException ignored) {

    }
    // Check empty list head and tail node
    assertNull("Check empty list head data", emptyList.head.data);
    assertNull("Check empty list tail data", emptyList.tail.data);
    assertEquals("Check empty list head next node", emptyList.head.next, emptyList.tail);
    assertEquals("Check empty list tail prev node", emptyList.tail.prev, emptyList.head);

    // check short list elements
    assertEquals("Check first element data", "A", shortList.head.next.data);
    assertEquals("Check second element data", "B", shortList.head.next.next.data);
    assertEquals("Check element prev node", shortList.head.next.prev, shortList.head);
    assertEquals("Check element next node", shortList.head.next.next.prev, shortList.head.next);
    assertEquals("Check tail prev node", shortList.head.next.next, shortList.tail.prev);
  }

  /** Test the size of the list */
  @Test
  public void testSize() {
    assertEquals("Check size", 0, emptyList.size());
    assertEquals("Check size", 2, shortList.size());
    assertEquals("Check size", LONG_LIST_LENGTH, longerList.size());
  }

  /**
   * Test adding an element into the list at a specified index, specifically: public void add(int
   * index, E element)
   */
  @Test
  public void testAddAtIndex() {
    // Test adding null element
    try {
      shortList.add(1, null);
      fail("Check null element");
    } catch (NullPointerException ignored) {

    }

    // Test adding an element with invalid index
    try {
      shortList.add(-1, "C");
      fail("Check index out of bounds");
    } catch (IndexOutOfBoundsException ignored) {

    }
    try {
      shortList.add(shortList.size(), "C");
    } catch (IndexOutOfBoundsException ignored) {

    }

    // Test adding en element at a specific index
    emptyList.add(0, 1);
    assertEquals("Check adding element at index for empty list", (Integer) 1, emptyList.get(0));
    shortList.add(0, "C");
    assertEquals("Check adding element at index", "C", shortList.get(0));
    assertEquals("Check adding element at index", "A", shortList.get(1));
    assertEquals("Check adding element at index", "B", shortList.get(2));
    shortList.add(2, "D");
    assertEquals("Check adding element at index", "D", shortList.get(2));
    assertEquals("Check adding element at index", "B", shortList.get(3));
  }

  /** Test setting an element in the list */
  @Test
  public void testSet() {
    shortList.set(0, "C");
    assertEquals("Checking setting element at index", "C", shortList.get(0));
    assertEquals("Checking size after setting an element", 2, shortList.size());
  }
}
