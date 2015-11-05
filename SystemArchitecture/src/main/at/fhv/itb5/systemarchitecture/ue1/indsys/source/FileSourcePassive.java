package main.at.fhv.itb5.systemarchitecture.ue1.indsys.source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamCorruptedException;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.source.SourcePassive;

public class FileSourcePassive implements SourcePassive<String>{

	private BufferedReader _bufferedReader;
	
	public FileSourcePassive() {
		_bufferedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("aliceInWonderland.txt")));
	}

	@Override
	public String read() throws StreamCorruptedException, EndOfStreamException {
		
		String line;
		try {
			if((line = _bufferedReader.readLine()) != null) {
				return line;
			} else {
				throw new EndOfStreamException();
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new StreamCorruptedException();
		}
	}
	


}
