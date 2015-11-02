package main.at.fhv.itb5.systemarchitecture.ue1.insys.sink;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamCorruptedException;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.source.SourceActive;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class FileSourceActive extends SourceActive<String>{
	private BufferedReader _bufferedReader;
	
	public FileSourceActive(Writeable<String> successor) {
		super(successor);
		_bufferedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("aliceInWonderland.txt")));
	}

	@Override
	public void process() {
		String line;
		try {
			if((line = _bufferedReader.readLine()) != null) {
				System.out.println(line);
				//write(line);
			} else {
				//TODO(san7985) eos
				//write(null);
			}
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
