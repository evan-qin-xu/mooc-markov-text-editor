package com.zeroonejourney.textgen;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;

public class MarkovTextGeneratorLoLTest {
  private final MarkovTextGeneratorLoL generator = new MarkovTextGeneratorLoL(new Random(42));

  @Before
  public void setUp() throws Exception {
    String textString = "hi there hi Leo";
    generator.train(textString);
  }

  @Test
  public void testTrain() {
    List<ListNode> wordList = generator.getWordList();
    assertEquals("hi", wordList.get(0).getWord());
    assertEquals("there", wordList.get(0).getNextWords().get(0));
    assertEquals("Leo", wordList.get(0).getNextWords().get(1));
    assertEquals("there", wordList.get(1).getWord());
    assertEquals("hi", wordList.get(1).getNextWords().get(0));
    assertEquals("Leo", wordList.get(2).getWord());
    assertEquals("hi", wordList.get(2).getNextWords().get(0));
    assertEquals(0, generator.generateText(0).length());
    String input = "I love cats. I hate dogs. I I I I I I I I I I I I I I I I love cats. I I I I I I I I I I I I I I I I hate dogs. I I I I I I I I I like books. I love love. I am a text generator. I love cats. I love cats. I love cats. I love love love socks.";
    generator.retrain(input);
    assertEquals(500, generator.generateText(500).split(" ").length);
  }
}
