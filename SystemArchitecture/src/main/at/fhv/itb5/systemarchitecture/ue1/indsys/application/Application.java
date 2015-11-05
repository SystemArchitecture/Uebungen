package main.at.fhv.itb5.systemarchitecture.ue1.indsys.application;

import java.util.LinkedList;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dao.WordLine;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.CharacterFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.CommonWordFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.PermutateFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.SortFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.WordNoiseFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.WordSeperatorFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.sink.ConsoleSinkPassive;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.source.FileSourceActive;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;


public class Application implements Runnable{

	private Runnable _runnable;
	
	public Application(String[] args) {

		RunDescriptor runDescriptor = ProgramParamParser.parse(args);

		switch (runDescriptor.getPipelineType()) {
		case Pull: {
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
					new ConsoleSinkPassive())))))));
			break;
		}
		}	
	}
	
	@Override
	public void run() {
		_runnable.run();
	}
}
