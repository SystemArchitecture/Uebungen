package main.at.fhv.itb5.systemarchitecture.ue1.indsys.application;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dao.SimpleLine;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.CharacterFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.CommonWordFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.PermutateFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.SortFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.WordNoiseFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.WordSeperatorFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.sink.ConsoleSinkPassive;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.source.FileSourceActive;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.source.SourceActive;

public class Application implements Runnable{

	private Runnable _runnable;
	
	public Application(String[] args) {

		RunDescriptor runDescriptor = ProgramParamParser.parse(args);

		switch (runDescriptor.getPipelineType()) {
		case Pull: {
			break;
		}
		case Push: {
			SourceActive<SimpleLine> source = new FileSourceActive(
					new CharacterFilter(
					new WordSeperatorFilter(
					new WordNoiseFilter(
					new PermutateFilter(
					new CommonWordFilter(
					new SortFilter(
					new ConsoleSinkPassive())))))));
					
			_runnable = source;
			break;
		}
		}	
	}
	
	@Override
	public void run() {
		_runnable.run();
	}
}
