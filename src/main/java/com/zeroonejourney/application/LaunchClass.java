package com.zeroonejourney.application;

import com.zeroonejourney.document.Document;
import com.zeroonejourney.document.EfficientDocument;
import com.zeroonejourney.spelling.*;
import com.zeroonejourney.textgen.MarkovTextGenerator;
import com.zeroonejourney.textgen.MarkovTextGeneratorLoL;

import java.util.Random;

public class LaunchClass {

  public String dictFile = "data/dict.txt";

  public LaunchClass() {
    super();
  }

  public Document getDocument(String text) {
    // Change this to BasicDocument(text) for week 1 only
    return new EfficientDocument(text);
  }

  public MarkovTextGenerator getMTG() {
    return new MarkovTextGeneratorLoL(new Random());
  }

  public WordPath getWordPath() {
    return new WPTree();
  }

  public AutoComplete getAutoComplete() {
    AutoCompleteDictionaryTrie tr = new AutoCompleteDictionaryTrie();
    DictionaryLoader.loadDictionary(tr, dictFile);
    return tr;
  }

  public Dictionary getDictionary() {
    Dictionary d = new DictionaryBST();
    DictionaryLoader.loadDictionary(d, dictFile);
    return d;
  }

  public SpellingSuggest getSpellingSuggest(Dictionary dic) {
    // return new org.evanxu.spelling.SpellingSuggestNW(new org.evanxu.spelling.NearbyWords(dic));
    return new NearbyWords(dic);
  }
}
