package main.at.fhv.itb5.systemarchitecture.ue1.indsys.sink.file;

import java.io.File;
import java.io.StreamCorruptedException;
import java.util.LinkedList;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dao.WordLine;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.util.FileWriterUtil;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.sink.SinkPassive;

public class FileSinkPassive implements SinkPassive<LinkedList<WordLine>>{
	
	private FileWriterUtil _fileWriterUtil;
	public FileSinkPassive(File sinkFile) {
		_fileWriterUtil = new FileWriterUtil(sinkFile);
	}
	
	@Override
	public void write(LinkedList<WordLine> value) throws StreamCorruptedException {
		if(value != null) {
			for(WordLine wordline : value) {
				_fileWriterUtil.write(wordline.toString());
			}
		}
	}

}
