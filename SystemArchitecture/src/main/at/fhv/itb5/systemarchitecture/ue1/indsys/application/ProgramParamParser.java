package main.at.fhv.itb5.systemarchitecture.ue1.indsys.application;

import java.io.File;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dto.Alignment;

public class ProgramParamParser {
	public static RunDescriptor parse(String[] args) {
		Exercise exercise = parseExerciseString(args[0]);
		PipelineType pipelineType = parsePipelineType(args[1]);
		
		if(args.length<3){
			throw new IllegalArgumentException("no sinkFileIndexPath provided");
		}

		String sinkFileIndexPath = args[2];
		
		if (args.length == 6) {
			return new RunDescriptor(exercise, pipelineType, new File(sinkFileIndexPath), new File(args[3]),
					parseAlignment(args[4]), Integer.parseInt(args[5]));
		}

		return new RunDescriptor(exercise, pipelineType, new File(sinkFileIndexPath));
	}

	private static Alignment parseAlignment(String arg) {
		switch (arg) {
		case ("Center"): {
			return Alignment.Center;
		}
		case ("Left"): {
			return Alignment.Left;
		}
		case ("Right"): {
			return Alignment.Right;
		}
		default: {
			System.out.println("Invalide Alignment!");
			return null;
		}
		}
	}

	private static Exercise parseExerciseString(String arg) {
		switch (arg) {
		case ("A"): {
			return Exercise.A;
		}
		case ("B"): {
			return Exercise.B;
		}
		default: {
			System.out.println("Invalide exercise!");
			return null;
		}
		}
	}

	private static PipelineType parsePipelineType(String arg) {
		switch (arg) {
		case ("Pull"): {
			return PipelineType.Pull;
		}
		case ("Push"): {
			return PipelineType.Push;
		}
		default: {
			System.out.println("Invalide pipline type!");
			return null;
		}
		}
	}
}
