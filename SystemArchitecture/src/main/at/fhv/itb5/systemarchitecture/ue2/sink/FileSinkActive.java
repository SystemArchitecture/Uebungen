package main.at.fhv.itb5.systemarchitecture.ue2.sink;

import java.io.File;
import java.io.StreamCorruptedException;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.util.FileWriterUtil;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.sink.SinkActive;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;

public class FileSinkActive extends SinkActive<String> implements Runnable {

	private FileWriterUtil _fileWriterUtil;
	
	public FileSinkActive(File file, Readable<String> predeseccor) {
		super(predeseccor);
		_fileWriterUtil = new FileWriterUtil(file);
	}

	@Override
	public void process() {
		try {
			String value = read();
			System.out.println(value);
			_fileWriterUtil.write(value);
			stop();
		} catch (StreamCorruptedException | EndOfStreamException e) {
			System.out.println(e.getMessage());
			stop();
		}
	}

}
