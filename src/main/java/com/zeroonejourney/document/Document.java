package com.zeroonejourney.document;

/** 
 * A class that represents a text org.evanxu.document
 * @author UC San Diego Intermediate Programming MOOC team
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document {

	private final String text;

	/**
	 * Create a new org.evanxu.document from the given text. Because this class is abstract,
	 * this is used only from subclasses.
	 * 
	 * @param text The text of the org.evanxu.document.
	 */
	protected Document(String text) {
		this.text = text;
	}

	/**
	 * Returns the tokens that match the regex pattern from the org.evanxu.document text
	 * string.
	 * 
	 * @param pattern A regular expression string specifying the token pattern
	 *                desired
	 * @return A List of tokens from the org.evanxu.document text that match the regex pattern
	 */
	protected List<String> getTokens(String pattern) {
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);

		while (m.find()) {
			tokens.add(m.group());
		}

		return tokens;
	}

	/**
	 * This is a helper function that returns the number of syllables in a word. You
	 * should write this and use it in your BasicDocument class.
	 * 
	 * You will probably NOT need to add a countWords or a countSentences method
	 * here. The reason we put countSyllables here because we'll use it again next
	 * week when we implement the EfficientDocument class.
	 * 
	 * For reasons of efficiency you should not create Matcher or Pattern objects
	 * inside this method. Just use a loop to loop through the characters in the
	 * string and write your own logic for counting syllables.
	 * 
	 * @param word The word to count the syllables in
	 * @return The number of syllables in the given word, according to this rule:
	 *         Each contiguous sequence of one or more vowels is a syllable, with
	 *         the following exception: a lone "e" at the end of a word is not
	 *         considered a syllable unless the word has no other syllables. You
	 *         should consider y a vowel.
	 */
	protected int countSyllables(String word) {
		Set<Character> vowels = new HashSet<Character>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'y'));
		boolean isPreviousLetterVowel = false;
		int numVowels = 0;
		word = word.toLowerCase();
		for (int i = 0; i < word.length(); i++) {
			if (i == word.length() - 1 && word.charAt(word.length() - 1) == 'e') {
				if (numVowels == 0) {
					numVowels++;
				}
				if (!isPreviousLetterVowel) {
					numVowels--;
				}
			}
			if (!isPreviousLetterVowel && vowels.contains(word.charAt(i))) {
				numVowels++;
				isPreviousLetterVowel = true;
			} else {
				isPreviousLetterVowel = vowels.contains(word.charAt(i));
			}
		}
		return numVowels;
	}

	/**
	 * A method for testing
	 * 
	 * @param doc       The Document object to test
	 * @param syllables The expected number of syllables
	 * @param words     The expected number of words
	 * @param sentences The expected number of sentences
	 * @return true if the test case passed. False otherwise.
	 */
	public static boolean testCase(Document doc, int syllables, int words, int sentences) {
		System.out.println("Testing text: ");
		System.out.print(doc.getText() + "\n....");
		boolean passed = true;
		int syllFound = doc.getNumSyllables();
		int wordsFound = doc.getNumWords();
		int sentFound = doc.getNumSentences();
		if (syllFound != syllables) {
			System.out.println("\nIncorrect number of syllables.  Found " + syllFound + ", expected " + syllables);
			passed = false;
		}
		if (wordsFound != words) {
			System.out.println("\nIncorrect number of words.  Found " + wordsFound + ", expected " + words);
			passed = false;
		}
		if (sentFound != sentences) {
			System.out.println("\nIncorrect number of sentences.  Found " + sentFound + ", expected " + sentences);
			passed = false;
		}

		if (passed) {
			System.out.println("passed.\n");
		} else {
			System.out.println("FAILED.\n");
		}
		return passed;
	}

	/** Return the number of words in this org.evanxu.document */
	public abstract int getNumWords();

	/** Return the number of sentences in this org.evanxu.document */
	public abstract int getNumSentences();

	/** Return the number of syllables in this org.evanxu.document */
	public abstract int getNumSyllables();

	/** Return the entire text of this org.evanxu.document */
	public String getText() {
		return this.text;
	}

	/** return the Flesch readability score of this org.evanxu.document */
	public double getFleschScore() {
		double wordsSentencesRatio = (double) getNumWords() / getNumSentences();
		double syllablesWordsRatio = (double) getNumSyllables() / getNumWords();
		return 206.835 - 1.015 * wordsSentencesRatio - 84.6 * syllablesWordsRatio;
	}
}
