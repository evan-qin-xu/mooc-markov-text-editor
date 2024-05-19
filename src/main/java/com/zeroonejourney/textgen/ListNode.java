package com.zeroonejourney.textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/** Links a word to the next words in the list You should use this class in your implementation. */
class ListNode {
  /** The word that is linking to the next words. */
  private final String word;

  /** The next words that could follow it. */
  private final List<String> nextWords;

  ListNode(String word) {
    this.word = word;
    nextWords = new LinkedList<String>();
  }

  public List<String> getNextWords() {
    return nextWords;
  }

  public String getWord() {
    return word;
  }

  public void addNextWord(String nextWord) {
    nextWords.add(nextWord);
  }

  public String getRandomNextWord(Random generator) {
    return nextWords.get(generator.nextInt(nextWords.size()));
  }

  public String toString() {
    String toReturn = word + ": ";
    for (String s : nextWords) {
      toReturn += s + "->";
    }
    toReturn += "\n";
    return toReturn;
  }
}
