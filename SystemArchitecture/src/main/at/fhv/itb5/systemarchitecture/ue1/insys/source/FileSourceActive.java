package main.at.fhv.itb5.systemarchitecture.ue1.insys.source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamCorruptedException;

import main.at.fhv.itb5.systemarchitecture.ue1.insys.dao.SimpleLine;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.source.SourceActive;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class FileSourceActive extends SourceActive<SimpleLine>{
	private BufferedReader _bufferedReader;
	
	private int _lineCount;
	
	public FileSourceActive(Writeable<SimpleLine> successor) {
		super(successor);
		_bufferedReader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("aliceInWonderland.txt")));
	}

	@Override
	public void process() {
		String line;
		try {
			if((line = _bufferedReader.readLine()) != null) {
				write(new SimpleLine(line, _lineCount++));
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
