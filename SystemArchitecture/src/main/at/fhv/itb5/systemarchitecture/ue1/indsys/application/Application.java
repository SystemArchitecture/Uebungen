package main.at.fhv.itb5.systemarchitecture.ue1.indsys.application;

import java.util.LinkedList;
import java.util.List;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dto.WordLine;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.CharacterFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.CommonWordFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.ConstructLines;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.LineDemulitplexer;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.PermutateFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.SortFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.StringToFileFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.WordConstructorFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.WordNoiseFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.WordSeperatorFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.converter.StringToSimpleLineFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.sink.file.FileSinkWordLinesActive;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.sink.file.passive.FileSinkWordLinesPassive;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.sink.file.passive.StringFileSinkPassive;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.source.FileCharacterSourceActive;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.source.FileCharacterSourcePassive;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.source.FileSourceActive;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.source.FileSourcePassive;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class Application implements Runnable{

	private Runnable _runnable;
	
	public Application(String[] args) {
		RunDescriptor runDescriptor = ProgramParamParser.parse(args);
		System.out.println("INDSYS started with arguments: " + runDescriptor);
		switch(runDescriptor.getExercise()) {
		case A: {
			runExerciseA(runDescriptor);
			break;
		}
		case B: {
			runExerciseB(runDescriptor);
			break;
		}
		}
	}
	
	public void runExerciseB(RunDescriptor runDescriptor) {
		switch (runDescriptor.getPipelineType()) {
		case Pull: {
			_runnable = new FileSinkWordLinesActive(runDescriptor.getSinkIndexFile(),
					(Readable<LinkedList<WordLine>>)new SortFilter(
					(Readable<LinkedList<WordLine>>)new CommonWordFilter(
					new PermutateFilter(
					new WordNoiseFilter(
					new WordSeperatorFilter(
					new CharacterFilter(
					new StringToSimpleLineFilter(
					new StringToFileFilter(runDescriptor.getSinkFormatedFile(),
					new ConstructLines(runDescriptor.getLineSize(), runDescriptor.getAlignment(), 
					new WordConstructorFilter(
					new FileCharacterSourcePassive())))))))))));
			break;
		}
		case Push: {
			
			List<Writeable<String>> demultiplexerSuccessors = new LinkedList<>();
			
			//part from exercise a
			demultiplexerSuccessors.add(new StringToSimpleLineFilter(
					new CharacterFilter(
					new WordSeperatorFilter(
					new WordNoiseFilter(
					new PermutateFilter(
					(Writeable<LinkedList<WordLine>>)new CommonWordFilter(
					(Writeable<LinkedList<WordLine>>)new SortFilter(
					new FileSinkWordLinesPassive(runDescriptor.getSinkIndexFile())))))))));
			
			//write to file
			demultiplexerSuccessors.add(new StringFileSinkPassive(runDescriptor.getSinkFormatedFile()));
			
			_runnable = new FileCharacterSourceActive(
					new WordConstructorFilter(
					new ConstructLines(runDescriptor.getLineSize(), runDescriptor.getAlignment(), new LineDemulitplexer(demultiplexerSuccessors))));
			break;
		}
		}	
	}
	
	public void runExerciseA(RunDescriptor runDescriptor) {
		switch (runDescriptor.getPipelineType()) {
		case Pull: {
			_runnable = new FileSinkWordLinesActive(runDescriptor.getSinkIndexFile(),
					(Readable<LinkedList<WordLine>>)new SortFilter(
					(Readable<LinkedList<WordLine>>)new CommonWordFilter(
					new PermutateFilter(
					new WordNoiseFilter(
					new WordSeperatorFilter(
					new CharacterFilter(
					new FileSourcePassive())))))));                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
			break;
		}
		case Push: {
			_runnable = new FileSourceActive(
					new CharacterFilter(
					new WordSeperatorFilter(
					new WordNoiseFilter(
					new PermutateFilter(
					(Writeable<LinkedList<WordLine>>)new CommonWordFilter(
					(Writeable<LinkedList<WordLine>>)new SortFilter(
					new FileSinkWordLinesPassive(runDescriptor.getSinkIndexFile()))))))));
			break;
		}
		}	
	}
	
	@Override
	public void run() {
		_runnable.run();
	}
}
