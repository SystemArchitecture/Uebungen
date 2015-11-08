package main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.sink.file.active;

import java.io.File;
import java.io.StreamCorruptedException;
import java.util.LinkedList;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dto.WordLine;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.util.FileWriterUtil;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.sink.SinkActive;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;

public class FileSinkWordLindesActive extends SinkActive<LinkedList<WordLine>> {
	
	private FileWriterUtil _fileWriterUtil;
	public FileSinkWordLindesActive(File sinkFile, Readable<LinkedList<WordLine>> predeseccor) {
		super(predeseccor);
		_fileWriterUtil = new FileWriterUtil(sinkFile);
	}

	@Override
	public void process() {
		try {
			LinkedList<WordLine> lines = read();
			if(lines != null) {
				for (WordLine wordLine : lines) {
					_fileWriterUtil.write(wordLine.toString());
				}
			}
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
			stop();
		} catch (EndOfStreamException e) {
			stop();
		}
	}

}