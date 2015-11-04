package main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.LinkedList;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dao.WordLine;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dao.WordLineComparator;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class SortFilter extends AbstractFilter<LinkedList<WordLine>, LinkedList<WordLine>> {

	private LinkedList<WordLine> _sorted;

	public SortFilter(Writeable<LinkedList<WordLine>> output) throws InvalidParameterException {
		super(output);

		_sorted = new LinkedList<>();
	}

	@Override
	public LinkedList<WordLine> read() throws StreamCorruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(LinkedList<WordLine> value) throws StreamCorruptedException {
		if (value != ENDING_SIGNAL) {
			_sorted.addAll(value);
			sort();
		} else {
			writeOutput(_sorted);
			sendEndSignal();
		}
	}

	private void sort() {
		if (_sorted != null) {
			_sorted.sort(new WordLineComparator());
		}
	}

}
