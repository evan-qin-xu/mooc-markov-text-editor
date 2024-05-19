package com.zeroonejourney.textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * An implementation of the MTG interface that uses a list of lists.
 *
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

  /** Space. */
  public static final String SPACE = " ";

  /** Empty string. */
  public static final String EMPTY_STRING = "";

  /** The random number generator. */
  private final Random rnGenerator;

  /** The list of words with their next words. */
  private List<ListNode> wordList;

  /** The starting "word". */
  private String starter;

  public MarkovTextGeneratorLoL(Random generator) {
    wordList = new LinkedList<>();
    starter = EMPTY_STRING;
    rnGenerator = generator;
  }

  /**
   * This is a minimal set of tests. Note that it can be difficult to test methods/classes with
   * randomized behavior.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    // feed the generator a fixed random value for repeatable behavior
    MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
    String textString =
        "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
    System.out.println(textString);
    gen.train(textString);
    System.out.println(gen);
    System.out.println(gen.generateText(20));
    String textString2 =
        "You say yes, I say no, "
            + "You say stop, and I say go, go, go, "
            + "Oh no. You say goodbye and I say hello, hello, hello, "
            + "I don't know why you say goodbye, I say hello, hello, hello, "
            + "I don't know why you say goodbye, I say hello. "
            + "I say high, you say low, "
            + "You say why, and I say I don't know. "
            + "Oh no. "
            + "You say goodbye and I say hello, hello, hello. "
            + "I don't know why you say goodbye, I say hello, hello, hello, "
            + "I don't know why you say goodbye, I say hello. "
            + "Why, why, why, why, why, why, "
            + "Do you say goodbye. "
            + "Oh no. "
            + "You say goodbye and I say hello, hello, hello. "
            + "I don't know why you say goodbye, I say hello, hello, hello, "
            + "I don't know why you say goodbye, I say hello. "
            + "You say yes, I say no, "
            + "You say stop and I say go, go, go. "
            + "Oh, oh no. "
            + "You say goodbye and I say hello, hello, hello. "
            + "I don't know why you say goodbye, I say hello, hello, hello, "
            + "I don't know why you say goodbye, I say hello, hello, hello, "
            + "I don't know why you say goodbye, I say hello, hello, hello,";
    System.out.println(textString2);
    gen.retrain(textString2);
    System.out.println(gen);
    System.out.println(gen.generateText(20));

    String textString3 = "hi hi hi hello";
    System.out.println(textString3);
    gen.retrain(textString3);
    System.out.println(gen);
    System.out.println(gen.generateText(20));
  }

  public List<ListNode> getWordList() {
    return wordList;
  }

  /** Train the generator by adding the sourceText. */
  @Override
  public void train(String sourceText) {
    if (sourceText.trim().isEmpty()) {
      return;
    }
    String[] words = sourceText.split(MarkovTextGeneratorLoL.SPACE);
    starter = words[0];
    String prevWord = starter;
    wordList.add(new ListNode(starter));
    for (int i = 1; i <= words.length; i++) {
      String w;
      if (i == words.length) {
        w = starter;
      } else {
        w = words[i];
      }
      boolean wordFound = false;
      for (ListNode ln : wordList) {
        if (ln.getWord().equals(prevWord)) {
          ln.addNextWord(w);
          wordFound = true;
          break;
        }
      }
      if (!wordFound) {
        ListNode n = new ListNode(prevWord);
        n.addNextWord(w);
        wordList.add(n);
      }
      prevWord = w;
    }
  }

  /** Generate the number of words requested. */
  @Override
  public String generateText(int numWords) {
    String currWord = starter;
    String output = EMPTY_STRING;
    int count = 0;
    while (count < numWords) {
      count++;
      for (ListNode wl : wordList) {
        if (wl.getWord().equals(currWord)) {
          String w = wl.getRandomNextWord(rnGenerator);
          output += w + SPACE;
          currWord = w;
          break;
        }
      }
    }
    return output;
  }

  @Override
  public String toString() {
    String toReturn = EMPTY_STRING;
    for (ListNode n : wordList) {
      toReturn += n.toString();
    }
    return toReturn;
  }

  /** Retrain the generator from scratch on the source text. */
  @Override
  public void retrain(String sourceText) {
    wordList = new LinkedList<>();
    starter = EMPTY_STRING;
    train(sourceText);
  }
}
