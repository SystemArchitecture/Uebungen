package main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter;

import java.util.List;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.demultiplexer.Demultiplexer;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class LineDemulitplexer extends Demultiplexer<String>{
	public LineDemulitplexer(List<Writeable<String>> successors) {
		super(successors);
	}
}
