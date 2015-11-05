package main.at.fhv.itb5.systemarchitecture.ue1.indsys.source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamCorruptedException;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.source.SourceActive;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class FileCharacterSourceActive extends SourceActive<Character>{
	private BufferedReader _bufferedReader;

	public FileCharacterSourceActive(Writeable<Character> successor) {
		super(successor);
		_bufferedReader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("aliceInWonderland.txt")));
	}
	
	@Override
	public void process() {
		int charValue;
		try {
			if((charValue = _bufferedReader.read()) != -1) {
				write(new Character((char)charValue));
			} else {
				write(null);
				stop();
			}
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
