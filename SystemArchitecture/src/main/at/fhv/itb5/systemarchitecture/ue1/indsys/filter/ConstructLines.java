package main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dto.Alignement;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class ConstructLines extends AbstractFilter<String, String> {

	private int _lineSize;
	private Alignement _alignement;
	public ConstructLines(int lineSize, Alignement alignement, Writeable<String> output) throws InvalidParameterException {
		super(output);
		_lineSize = lineSize;
		_alignement = alignement;
	}

	@Override
	public String read() throws StreamCorruptedException, EndOfStreamException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private StringBuilder _lineBuilder;
	@Override
	public void write(String value) throws StreamCorruptedException {
		if(value != ENDING_SIGNAL) {
			// TODO alignement
			if(_lineBuilder == null) {
				_lineBuilder = new StringBuilder();
			}
			
			if((_lineBuilder.length() + value.length()) > _lineSize) {
				String line = _lineBuilder.toString();
				
				_lineBuilder = new StringBuilder();
				_lineBuilder.append(value + " ");
				
				writeOutput(line);
			} else {
				_lineBuilder.append(value + " ");
			}
		} else{
			sendEndSignal();
		}
	}
}
