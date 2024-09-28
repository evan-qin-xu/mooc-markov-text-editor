package com.zeroonejourney.spelling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 *
 * @author You
 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

  private TrieNode root;

  /** Number of nodes in this trie. */
  private int size;


  public AutoCompleteDictionaryTrie() {
    root = new TrieNode();
  }


  /** Insert a word into the trie.
   * For the basic part of the assignment (part 2), you should convert the
   * string to all lower case before you insert it.
   *
   * This method adds a word by creating and linking the necessary trie nodes
   * into the trie, as described outlined in the videos for this week. It
   * should appropriately use existing nodes in the trie, only creating new
   * nodes when necessary. E.g. If the word "no" is already in the trie,
   * then adding the word "now" would add only one additional node
   * (for the 'w').
   *
   * @return true if the word was successfully added or false if it already exists
   * in the dictionary.
   */
  public boolean addWord(String word) {
    String lowerCaseWord = word.toLowerCase();
    TrieNode curr = root;
    TrieNode child = null;
    for (int i = 0; i < lowerCaseWord.length(); i++) {
      char c = lowerCaseWord.charAt(i);
      child = curr.getChild(c);
      if (child == null) {
        child = curr.insert(c);
        size++;
      }
      curr = child;
    }
    if (curr.endsWord()) {
      return false;
    } else {
      curr.setEndsWord(true);
      return true;
    }
  }

  /**
   * Return the number of words in the dictionary.  This is NOT necessarily the same
   * as the number of TrieNodes in the trie.
   */
  public int size() {
    int numWords = 0;
    printNode(root);
    return countWords(numWords, root);
  }

  private int countWords(int numWords, TrieNode curr) {
    if (curr.endsWord()) {
      numWords++;
    }
    for (char c : curr.getValidNextCharacters()) {
      numWords = countWords(numWords, curr.getChild(c));
    }
    return numWords;
  }


  /** Returns whether the string is a word in the trie, using the algorithm
   * described in the videos for this week. */
  @Override
  public boolean isWord(String s) {
    String lowerCaseStr = s.toLowerCase();
    TrieNode curr = root;
    for (int i = 0; i < lowerCaseStr.length(); i++) {
      TrieNode child = curr.getChild(lowerCaseStr.charAt(i));
      if (child == null) {
        return false;
      }
      curr = child;
    }
    return curr.endsWord();
  }

  /**
   * Return a list, in order of increasing (non-decreasing) word length,
   * containing the numCompletions shortest legal completions
   * of the prefix string. All legal completions must be valid words in the
   * dictionary. If the prefix itself is a valid word, it is included
   * in the list of returned words.
   *
   * The list of completions must contain
   * all of the shortest completions, but when there are ties, it may break
   * them in any order. For example, if there the prefix string is "ste" and
   * only the words "step", "stem", "stew", "steer" and "steep" are in the
   * dictionary, when the user asks for 4 completions, the list must include
   * "step", "stem" and "stew", but may include either the word
   * "steer" or "steep".
   *
   * If this string prefix is not in the trie, it returns an empty list.
   *
   * @param prefix The text to use at the word stem
   * @param numCompletions The maximum number of predictions desired.
   * @return A list containing the up to numCompletions best predictions
   */@Override
  public List<String> predictCompletions(String prefix, int numCompletions) {
    List<String> completions = new ArrayList<>();
    String lowerCasePrefix = prefix.toLowerCase();
    TrieNode curr = root;
    // Find the stem
    for (int i = 0; i < lowerCasePrefix.length(); i++) {
      TrieNode child = curr.getChild(lowerCasePrefix.charAt(i));
      if (child != null) {
        curr = child;
      } else {
        return completions;
      }
    }
    // Breath first search for the stem
    Queue<TrieNode> nodes = new LinkedList<>();
    nodes.add(curr);
    while(!nodes.isEmpty() && completions.size() < numCompletions) {
      TrieNode n = nodes.poll();
      if (n.endsWord()) {
        completions.add(n.getText());
      }
      for (char c : n.getValidNextCharacters()) {
        nodes.add(n.getChild(c));
      }
    }
    return completions;
  }

  // For debugging
  public void printTree() {
    printNode(root);
  }

  /** Do a pre-order traversal from this node down */
  public void printNode(TrieNode curr) {
    if (curr == null)
      return;

    System.out.println(curr.getText());

    TrieNode next = null;
    for (Character c : curr.getValidNextCharacters()) {
      next = curr.getChild(c);
      printNode(next);
    }
  }
}
