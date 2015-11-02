package main.at.fhv.itb5.systemarchitecture.ue1.insys;

import main.at.fhv.itb5.systemarchitecture.ue1.insys.dao.SimpleLine;
import main.at.fhv.itb5.systemarchitecture.ue1.insys.filter.CharacterFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.insys.filter.PerlmutateFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.insys.filter.SortFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.insys.filter.WordNoiseFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.insys.filter.WordSeperatorFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.insys.sink.ConsoleSinkPassive;
import main.at.fhv.itb5.systemarchitecture.ue1.insys.source.FileSourceActive;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.source.SourceActive;

public class Program {

	public static void main(String[] args) {
		SourceActive<SimpleLine> source = new FileSourceActive(
										new CharacterFilter(
										new WordSeperatorFilter(
										new WordNoiseFilter(
										new PerlmutateFilter(
										new SortFilter(
										new CommonWordFilter(
										new ConsoleSinkPassive())))))));
		source.run();
	}

}
