package main.at.fhv.itb5.systemarchitecture.ue2.sink;

import java.io.File;
import java.io.StreamCorruptedException;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.util.FileWriterUtil;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;

public class FileSinkActive extends AbstractFilter<String, String> implements Runnable{

	protected FileWriterUtil _fileWriterUtil;
	
	public FileSinkActive(File file, Readable<String> predeseccor) {
		super(predeseccor);
		_fileWriterUtil = new FileWriterUtil(file);
	}

	@Override
	public String read() throws StreamCorruptedException {
		return readInput();
	}

	@Override
	public void write(String value) throws StreamCorruptedException {
		throw new StreamCorruptedException();
	}

	@Override
	public void run() {
		String value;
		do{
			try {
				value = read();
				if(value != null) {
					_fileWriterUtil.write(value);
				}
			} catch (StreamCorruptedException e) {
				value = null;
			}
		}while(value != null);
	}

}
