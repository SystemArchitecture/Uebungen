package main.at.fhv.itb5.systemarchitecture.ue1.insys.application;

import main.at.fhv.itb5.systemarchitecture.ue1.insys.dao.SimpleLine;
import main.at.fhv.itb5.systemarchitecture.ue1.insys.filter.CharacterFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.insys.filter.CommonWordFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.insys.filter.PermutateFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.insys.filter.SortFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.insys.filter.WordNoiseFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.insys.filter.WordSeperatorFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.insys.sink.ConsoleSinkPassive;
import main.at.fhv.itb5.systemarchitecture.ue1.insys.source.FileSourceActive;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.source.SourceActive;

public class Application implements Runnable{

	private Runnable _runnable;
	
	public Application(String[] args) {

		RunDescriptor runDescriptor = ProgramParamParser.parse(args);

		// react accordingly
		switch (runDescriptor.getPipelineType()) {
		case Pull: {
			break;
		}
		case Push: {
			SourceActive<SimpleLine> source = new FileSourceActive(
					new CharacterFilter(
					new WordSeperatorFilter(
					new WordNoiseFilter(
					new CommonWordFilter(
					new PermutateFilter(
					new SortFilter(
					new ConsoleSinkPassive())))))));
					
			_runnable = source;
			break;
		}
		default: {
			System.out.println("Invalide pipeline type!");
			break;
		}
		}	
	}
	
	@Override
	public void run() {
		_runnable.run();
	}
}
