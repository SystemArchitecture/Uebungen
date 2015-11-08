package main.at.fhv.itb5.systemarchitecture.ue1.indsys.sink.file.passive;

import java.io.File;
import java.io.StreamCorruptedException;
import java.util.LinkedList;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dto.WordLine;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.sink.file.FileSink;

public class FileSinkWordLinesPassive extends FileSink<LinkedList<WordLine>>{
	public FileSinkWordLinesPassive(File file) {
		super(file);
	}

	@Override
	public void write(LinkedList<WordLine> value) throws StreamCorruptedException {
		if(value != null) {
			for(WordLine wordline : value) {
				saveLine(wordline.toString());
			}
		}
	}
}
