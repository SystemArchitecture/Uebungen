package main.at.fhv.itb5.systemarchitecture.ue1.indsys.sink.file;

import java.io.File;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.util.FileWriterUtil;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.sink.SinkPassive;

public abstract class FileSink<T> implements SinkPassive<T> {
private FileWriterUtil _fileWriterUtil;

	public FileSink(File file) {
		_fileWriterUtil = new FileWriterUtil(file);
	}
	
	protected void saveLine(String line) {
		_fileWriterUtil.write(line);
	}
}
