package main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.LinkedList;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dao.WordLine;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dao.WordLineComparator;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class SortFilter extends AbstractFilter<LinkedList<WordLine>, LinkedList<WordLine>> {

	private LinkedList<WordLine> _sorted;
	private boolean _isCollectingData;
	
	private void init() {
		_sorted = new LinkedList<>();
		_isCollectingData = true;
	}
	
	public SortFilter(Writeable<LinkedList<WordLine>> output) throws InvalidParameterException {
		super(output);
		init();
	}
	
	public SortFilter(Readable<LinkedList<WordLine>> input) {
		super(input);
		init();
	}

	@Override
	public LinkedList<WordLine> read() throws EndOfStreamException, StreamCorruptedException {
		if(!_isCollectingData) {
			throw new EndOfStreamException();
		}
		
		while(_isCollectingData) {
			try {
				_sorted.addAll(readInput());
			} catch (EndOfStreamException e) {
				_isCollectingData = false;
			}
		}
		sort();
		return _sorted;
	}

	@Override
	public void write(LinkedList<WordLine> value) throws StreamCorruptedException {
		if (value != ENDING_SIGNAL) {
			_sorted.addAll(value);
		} else {
			sort();
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
