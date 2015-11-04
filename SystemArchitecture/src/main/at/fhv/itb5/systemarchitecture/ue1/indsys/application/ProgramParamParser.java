package main.at.fhv.itb5.systemarchitecture.ue1.indsys.application;

public class ProgramParamParser {
	public static RunDescriptor parse(String[] args) {
		//TODO(san7985) parse input params
		
		return new RunDescriptor(PipelineType.Push, "aliceInWonderland.txt", null);
	}
}
