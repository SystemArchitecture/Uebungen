package main.at.fhv.itb5.systemarchitecture.ue1.indsys.application;

import java.io.File;

public class ProgramParamParser {
	public static RunDescriptor parse(String[] args) {
		Exercise exercise = parseExerciseString(args[0]);
		PipelineType pipelineType = parsePipelineType(args[1]);
		String sinkFileIndexPath = args[2];
		if(args.length == 4) {
			return new RunDescriptor(exercise, pipelineType, new File(sinkFileIndexPath), new File(args[3]));
		}
		
		return new RunDescriptor(exercise, pipelineType, new File(sinkFileIndexPath));
	}
	
	private static Exercise parseExerciseString(String arg) {
		switch(arg) {
		case("A"): {
			return Exercise.A;
		}
		case("B"): {
			return Exercise.B;
		}
		default: {
			System.out.println("Invalide exercise!");
			return null;
		}
		}
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
