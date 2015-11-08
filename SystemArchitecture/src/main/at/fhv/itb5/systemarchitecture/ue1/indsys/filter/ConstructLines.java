package main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dto.Alignment;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class ConstructLines extends AbstractFilter<String, String> {

	private int _lineSize;
	private Alignment _alignement;
	public ConstructLines(int lineSize, Alignment alignement, Writeable<String> output) throws InvalidParameterException {
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
			if(_lineBuilder == null) {
				_lineBuilder = new StringBuilder();
			}
			
			if((_lineBuilder.length() + value.length()) > _lineSize) {
				applyAlignment(_lineBuilder, _alignement);
				String line = _lineBuilder.toString();
				
				_lineBuilder = new StringBuilder();
				_lineBuilder.append(value);
				
				writeOutput(line);
			} else {
				_lineBuilder.append(" " + value);
			}
		} else{
			sendEndSignal();
		}
	}

	private void applyAlignment(StringBuilder lineBuilder, Alignment alignment) {
		switch(alignment) {
		case Center:
			while(lineBuilder.length() <= _lineSize) {
				//insert spacing in front
				lineBuilder.insert(0, ' ');
				
				if(lineBuilder.length() <= _lineSize) {
					//insert spacing in back
					lineBuilder.append(' ');
				}
			}
			break;
		case Left:
			//Do nothing already left aligned
			break;
		case Right:
			while(lineBuilder.length() <= _lineSize) {
				//insert spacing at the beginning
				lineBuilder.insert(0, ' ');
			}
			break;
		default:
			break;
			
		}
	}
}
