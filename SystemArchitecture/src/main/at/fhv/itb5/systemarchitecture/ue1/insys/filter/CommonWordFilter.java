package main.at.fhv.itb5.systemarchitecture.ue1.insys.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.LinkedList;

import main.at.fhv.itb5.systemarchitecture.ue1.insys.dao.WordLine;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class CommonWordFilter extends AbstractFilter<WordLine, WordLine> {

	public CommonWordFilter(Writeable<WordLine> output) throws InvalidParameterException {
		super(output);
	}

	@Override
	public WordLine read() throws StreamCorruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(WordLine value) throws StreamCorruptedException {
		if (value != ENDING_SIGNAL) {
			clearUpList(value);
			if (value.getWords().size() > 0) {
				writeOutput(value);
			}

		} else {
			sendEndSignal();
		}
	}

	private void clearUpList(WordLine input) {
		// clone words list
		@SuppressWarnings("unchecked")
		LinkedList<String> newWords = (LinkedList<String>) input.getWords().clone();

		// filter frequent english words
		HashMap<Integer, String> wordMap = loadWordList();
		for (String word : input.getWords()) {
			if (wordMap.containsKey(word.toLowerCase().hashCode())) {
				newWords.remove(word);
			}
		}

		// set new words list
		input.setWords(newWords);
	}

	private HashMap<Integer, String> loadWordList() {
		// create new hashmap and fill it with the most frequent words of the
		// list
		HashMap<Integer, String> wordMap = new HashMap<Integer, String>();

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(getClass().getClassLoader().getResourceAsStream("frequentEnglishWords.txt")));
		String line;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				wordMap.put(line.hashCode(), line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return wordMap;
	}
}
