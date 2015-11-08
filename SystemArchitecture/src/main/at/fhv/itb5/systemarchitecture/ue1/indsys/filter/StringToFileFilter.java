package main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter;

import java.io.File;
import java.io.StreamCorruptedException;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.util.FileWriterUtil;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;

public class StringToFileFilter extends AbstractFilter<String, String> {
	FileWriterUtil _fileWriterUtil;

	public StringToFileFilter(File sinkFormatedFile, Readable<String> input) {
		super(input);
		_fileWriterUtil = new FileWriterUtil(sinkFormatedFile);
	}

	@Override
	public String read() throws StreamCorruptedException, EndOfStreamException {
		String line = null;

		line = readInput();
		_fileWriterUtil.write(line);
		
		return line;
	}

	@Override
	public void write(String value) throws StreamCorruptedException {
		// TODO Auto-generated method stub

	}

}
