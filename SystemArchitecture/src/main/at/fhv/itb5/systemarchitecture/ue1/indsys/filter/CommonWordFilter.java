package main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.LinkedList;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dto.WordLine;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class CommonWordFilter extends AbstractFilter<LinkedList<WordLine>, LinkedList<WordLine>> {
	private HashSet<String> _wordMap;

	public CommonWordFilter(Writeable<LinkedList<WordLine>> output) throws InvalidParameterException {
		super(output);
	}
	
	public CommonWordFilter(Readable<LinkedList<WordLine>> input) {
		super(input);
	}

	@Override
	public LinkedList<WordLine> read() throws StreamCorruptedException, EndOfStreamException {
		return clearUpList(readInput());
	}

	@Override
	public void write(LinkedList<WordLine> value) throws StreamCorruptedException {
		if (value != ENDING_SIGNAL) {
			value = clearUpList(value);
		} else {
			sendEndSignal();
		}
		writeOutput(value);
	}


	private LinkedList<WordLine> clearUpList(LinkedList<WordLine> input) {
		// load word map
		if (_wordMap == null) {
			_wordMap = loadWordList();
		}

		@SuppressWarnings("unchecked")
		LinkedList<WordLine> copyInput = (LinkedList<WordLine>) input.clone();
		for(WordLine line : input )  {
			if(_wordMap.contains(line.getWords().getFirst().toLowerCase())) {
				copyInput.remove(line);
			}
		}
		return copyInput;
	}

	private HashSet<String> loadWordList() {
		// create new hashset and fill it with the most frequent words of the
		// list
		HashSet<String> wordMap = new HashSet<String>();

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
				CommonWordFilter.class.getClassLoader().getResourceAsStream("frequentEnglishWords.txt")));
		String word;
		try {
			while ((word = bufferedReader.readLine()) != null) {
				wordMap.add(word);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return wordMap;
	}

	/*
	 * public static void main(String[] args){ LinkedList<WordLine> input = new
	 * LinkedList<WordLine>(); LinkedList<String> words1 = new
	 * LinkedList<String>(); words1.add("your"); words1.add("simon");
	 * words1.add("goes"); words1.add("to"); LinkedList<String> words2 = new
	 * LinkedList<String>(); words2.add("simon"); words2.add("your");
	 * words2.add("goes"); words2.add("to"); WordLine line1 = new
	 * WordLine(words1, 1); WordLine line2 = new WordLine(words2, 2);
	 * input.add(line1); input.add(line2); clearUpList(input);
	 * System.out.println(input); }
	 */
}
