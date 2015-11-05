package main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.LinkedList;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dao.WordLine;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class CommonWordFilter extends AbstractFilter<LinkedList<WordLine>, LinkedList<WordLine>> {
	private HashMap<Integer, String> _wordMap;

	public CommonWordFilter(Writeable<LinkedList<WordLine>> output) throws InvalidParameterException {
		super(output);
	}

	@Override
	public LinkedList<WordLine> read() throws StreamCorruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(LinkedList<WordLine> value) throws StreamCorruptedException {
		if (value != ENDING_SIGNAL) {
			clearUpList(value);
		} else {
			sendEndSignal();
		}
		writeOutput(value);
	}

	private void clearUpList(LinkedList<WordLine> input) {
		// load word map
		if (_wordMap == null) {
			_wordMap = loadWordList();
		}

		for (int i = 0; i < input.size(); i++) {
			// TODO remove when empty wordlines are filtered before
			if (input.get(i).getWords().isEmpty()) {
				input.remove(i);
			} else {
				int hashCode = input.get(i).getWords().getFirst().toLowerCase().hashCode();
				// filter lines beginning with these words
				if (_wordMap.containsKey(hashCode)) {
					input.remove(i);
				}
			}
		}

	}

	private HashMap<Integer, String> loadWordList() {
		// create new hashmap and fill it with the most frequent words of the
		// list
		HashMap<Integer, String> wordMap = new HashMap<Integer, String>();

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(CommonWordFilter.class.getClassLoader().getResourceAsStream("frequentEnglishWords.txt")));
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
	
	/*public static void main(String[] args){
		LinkedList<WordLine> input = new LinkedList<WordLine>();
		LinkedList<String> words1 = new LinkedList<String>();
		words1.add("your");
		words1.add("simon");
		words1.add("goes");
		words1.add("to");
		LinkedList<String> words2 = new LinkedList<String>();
		words2.add("simon");
		words2.add("your");
		words2.add("goes");
		words2.add("to");
		WordLine line1 = new WordLine(words1, 1);
		WordLine line2 = new WordLine(words2, 2);
		input.add(line1);
		input.add(line2);
		clearUpList(input);
		System.out.println(input);
	}*/
}
