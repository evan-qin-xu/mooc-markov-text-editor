package com.zeroonejourney.textgen;

import java.util.AbstractList;

/**
 * A class that implements a doubly linked list
 *
 * @param <E> The type of the elements stored in the list
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MyLinkedList<E> extends AbstractList<E> {

  LLNode<E> head;
  LLNode<E> tail;
  int size;

  /** Create a new empty LinkedList */
  public MyLinkedList() {
    this.head = new LLNode<>(null);
    this.tail = new LLNode<>(null);
    head.next = tail;
    tail.prev = head;
    this.size = 0;
  }

  /**
   * Appends an element to the end of the list
   *
   * @param element The element to add
   */
  public boolean add(E element) {
    LLNode<E> data = new LLNode<>(element);
    if (element == null) {
      throw new NullPointerException();
    }
    LLNode<E> last = tail.prev;
    last.next = data;
    data.next = tail;
    data.prev = last;
    tail.prev = data;
    size++;
    return true;
  }

  /**
   * Get the element at position index
   *
   * @throws IndexOutOfBoundsException if the index is out of bounds
   */
  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    LLNode<E> current = head.next;
    int currIndex = 0;
    E data = null;
    while (current != null) {
      if (currIndex == index) {
        data = current.data;
      }
      current = current.next;
      currIndex++;
    }
    return data;
  }

  /**
   * Add an element to the list at the specified index
   *
   * @param index where the element should be added
   * @param element The element to add
   */
  public void add(int index, E element) {
    if (element == null) {
      throw new NullPointerException();
    }
    LLNode<E> data = new LLNode<>(element);
    LLNode<E> current = head.next;
    if (size == 0 && index == 0) {
      insertNode(current, data);
      return;
    }
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    int currIndex = 0;
    while (currIndex < size) {
      if (currIndex == index) {
        insertNode(current, data);
        break;
      } else {
        current = current.next;
        currIndex++;
      }
    }
  }

  private void insertNode(LLNode<E> current, LLNode<E> data) {
    LLNode<E> prev;
    prev = current.prev;
    current.prev = data;
    data.next = current;
    prev.next = data;
    data.prev = prev;
    size++;
  }

  /** Return the size of the list */
  public int size() {
    return size;
  }

  @Override
  public String toString() {
    LLNode<E> current = head.next;
    StringBuilder nodes = new StringBuilder();
    for (int i = 0; i < size; i++) {
      nodes.append(current.toString()).append(", ");
      current = current.next;
    }
    return "MyLinkedList{"
        + "head="
        + head
        + ", "
        + nodes
        + ", tail="
        + tail
        + ", size="
        + size
        + "}";
  }

  /**
   * Remove a node at the specified index and return its data element.
   *
   * @param index The index of the element to remove
   * @return The data element removed
   * @throws IndexOutOfBoundsException If index is outside the bounds of the list
   */
  public E remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    int listIndex = 0;
    LLNode<E> current = head.next;
    E removedElement = null;
    while (listIndex < size) {
      if (listIndex == index) {
        current.prev.next = current.next;
        current.next.prev = current.prev;
        removedElement = current.data;
        size--;
        break;
      } else {
        current = current.next;
        listIndex++;
      }
    }
    return removedElement;
  }

  /**
   * Set an index position in the list to a new element
   *
   * @param index The index of the element to change
   * @param element The new element
   * @return The element that was replaced
   * @throws IndexOutOfBoundsException if the index is out of bounds
   */
  public E set(int index, E element) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    if (element == null) {
      throw new NullPointerException();
    }
    int currentIndex = 0;
    LLNode<E> currentNode = head.next;
    while (index != currentIndex) {
      currentNode = currentNode.next;
      currentIndex++;
    }
    E replaced = currentNode.data;
    currentNode.data = element;
    return replaced;
  }
}

class LLNode<E> {
  LLNode<E> prev;
  LLNode<E> next;
  E data;

  public LLNode(E e) {
    this.data = e;
    this.prev = null;
    this.next = null;
  }

  public LLNode(LLNode<E> prev, LLNode<E> next, E data) {
    this.prev = prev;
    this.next = next;
    this.data = data;
  }

  @Override
  public String toString() {
    return "LLNode{" + "data=" + data + "}";
  }
}
