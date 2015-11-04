package main.at.fhv.itb5.systemarchitecture.ue1.indsys.dao;

import java.util.Comparator;

public class WordLineComparator implements Comparator<WordLine> {

	@Override
	public int compare(WordLine wordOne, WordLine wordTwo) {
		return wordOne.getWords().getFirst().compareToIgnoreCase(wordTwo.getWords().getFirst());
	}

}
