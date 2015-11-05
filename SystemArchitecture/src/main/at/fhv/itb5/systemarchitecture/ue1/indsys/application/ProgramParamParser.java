package main.at.fhv.itb5.systemarchitecture.ue1.indsys.application;

public class ProgramParamParser {
	public static RunDescriptor parse(String[] args) {
		PipelineType pipelineType = parsePipelineType(args[0]);
		String sourceFilePath = args[1];
		String sinkFilePath = args[2];
		
		return new RunDescriptor(pipelineType, sourceFilePath, sinkFilePath);
	}
	
	private static PipelineType parsePipelineType(String arg) {
		switch(arg) {
		case("Pull"): {
			return PipelineType.Pull;
		}
		case("Push"): {
			return PipelineType.Push;
		}
		default: {
			System.out.println("Invalide pipline type!");
			return null;
		}
		}
	}
}
