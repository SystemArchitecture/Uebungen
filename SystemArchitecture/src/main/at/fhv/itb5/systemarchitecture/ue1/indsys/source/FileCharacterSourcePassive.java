package main.at.fhv.itb5.systemarchitecture.ue1.indsys.source;

import java.io.StreamCorruptedException;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.source.SourcePassive;

public class FileCharacterSourcePassive  implements SourcePassive<Character>{

	@Override
	public Character read() throws StreamCorruptedException, EndOfStreamException {
		// TODO Auto-generated method stub
		return null;
	}

}
