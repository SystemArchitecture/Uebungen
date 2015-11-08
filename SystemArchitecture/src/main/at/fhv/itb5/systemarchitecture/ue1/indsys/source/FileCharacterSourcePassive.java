package main.at.fhv.itb5.systemarchitecture.ue1.indsys.source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamCorruptedException;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.source.SourcePassive;

public class FileCharacterSourcePassive  implements SourcePassive<Character>{
	private BufferedReader _bufferedReader;

	public FileCharacterSourcePassive() {
		super();
		_bufferedReader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("aliceInWonderland.txt")));
	}

	@Override
	public Character read() throws StreamCorruptedException, EndOfStreamException {
		int charValue;
		try {
			if((charValue = _bufferedReader.read()) != -1) {
				return (new Character((char)charValue));
			} else {
				throw new EndOfStreamException();
			}
		} catch (IOException e) {
			throw new StreamCorruptedException();
		}
	}

}
