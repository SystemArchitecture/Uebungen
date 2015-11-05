package main.at.fhv.itb5.systemarchitecture.ue1.indsys.dao;

import java.util.Comparator;

public class WordLineComparator implements Comparator<WordLine> {

	@Override
	public int compare(WordLine wordOne, WordLine wordTwo) {
		if(wordOne.getWords().size() == 0 || wordTwo.getWords().size() == 0) {
			System.out.println(wordOne);
			System.out.println(wordTwo);
		}
		
		return wordOne.getWords().getFirst().compareToIgnoreCase(wordTwo.getWords().getFirst());
	}

}
