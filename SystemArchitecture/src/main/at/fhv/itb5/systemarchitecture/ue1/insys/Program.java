package main.at.fhv.itb5.systemarchitecture.ue1.insys;

import main.at.fhv.itb5.systemarchitecture.ue1.insys.sink.FileSourceActive;
import main.at.fhv.itb5.systemarchitecture.ue1.insys.source.ConsoleSinkPassive;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.source.SourceActive;

public class Program {

	public static void main(String[] args) {
		SourceActive<String> source = new FileSourceActive(new ConsoleSinkPassive());
		source.run();
	}

}
