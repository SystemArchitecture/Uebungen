package main.at.fhv.itb5.systemarchitecture.ue1.insys.dao;

import java.util.LinkedList;

public class WordLine {
	private LinkedList<String> _words;
	private int _lineNumber;
	
	public WordLine(LinkedList<String> words, int lineNumber) {
		_words = words;
		_lineNumber = lineNumber;
	}
	
	public LinkedList<String> getWords() {
		return _words;
	}
	
	public int getLineNumber() {
		return _lineNumber;
	}
	
	private String wordsToString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		for(String word : _words) {
			stringBuilder.append(word + " ");
		}
		
		return stringBuilder.toString();
	}
	
	@Override
	public String toString() {
		return _lineNumber + " -> " + wordsToString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public WordLine clone() {
		return new WordLine((LinkedList<String>) _words.clone(), _lineNumber);
	}

	public void setWords(LinkedList<String> words) {
		_words = words;
	}
}
