package main.at.fhv.itb5.systemarchitecture.ue1.indsys.application;

import java.util.LinkedList;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dao.WordLine;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.CharacterFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.CommonWordFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.PermutateFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.SortFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.WordNoiseFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.WordSeperatorFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.sink.file.FileSinkActive;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.sink.file.FileSinkPassive;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.source.FileSourceActive;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.source.FileSourcePassive;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class Application implements Runnable{

	private Runnable _runnable;
	
	public Application(String[] args) {
		RunDescriptor runDescriptor = ProgramParamParser.parse(args);
		switch(runDescriptor.getExercise()) {
		case A: {
			runExerciseA(runDescriptor);
			break;
		}
		case B: {
			System.out.println("Not yet implemented!");
			break;
		}
		}
		
	}
	
	public void runExerciseA(RunDescriptor runDescriptor) {
		switch (runDescriptor.getPipelineType()) {
		case Pull: {
			_runnable = new FileSinkActive(runDescriptor.getSinkFile(),
					(Readable<LinkedList<WordLine>>)new SortFilter(
					(Readable<LinkedList<WordLine>>)new CommonWordFilter(
					new PermutateFilter(
					new WordNoiseFilter(
					new WordSeperatorFilter(
					new CharacterFilter(
					new FileSourcePassive())))))));;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
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
					new FileSinkPassive(runDescriptor.getSinkFile()))))))));
			break;
		}
		}	
	}
	
	@Override
	public void run() {
		_runnable.run();
	}
}
