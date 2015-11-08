package main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.converter;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dto.SimpleLine;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class StringToSimpleLineFilter extends AbstractFilter<String, SimpleLine> {

	private int _lineCounter;
	
	public StringToSimpleLineFilter(Writeable<SimpleLine> output) throws InvalidParameterException {
		super(output);
	}

	public StringToSimpleLineFilter(Readable<String> input) {
		super(input);
	}

	@Override
	public SimpleLine read() throws StreamCorruptedException, EndOfStreamException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(String value) throws StreamCorruptedException {
		if(value != ENDING_SIGNAL) {
			writeOutput(new SimpleLine(value, _lineCounter++));
		} else{
			sendEndSignal();
		}
	}

}
