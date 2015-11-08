package main.at.fhv.itb5.systemarchitecture.ue1.indsys.source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamCorruptedException;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dto.SimpleLine;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.source.SourcePassive;

public class FileSourcePassive implements SourcePassive<SimpleLine>{

	private BufferedReader _bufferedReader;
	private int _lineCount;
	
	public FileSourcePassive() {
		_bufferedReader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("aliceInWonderland.txt")));
	}

	@Override
	public SimpleLine read() throws StreamCorruptedException, EndOfStreamException {
		
		String line;
		try {
			if((line = _bufferedReader.readLine()) != null) {
				return new SimpleLine(line, _lineCount++);
			} else {
				throw new EndOfStreamException();
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new StreamCorruptedException();
		}
	}
	


}
