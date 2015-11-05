package main.at.fhv.itb5.systemarchitecture.ue1.indsys;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.application.Application;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.application.Exercise;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.application.PipelineType;

public class Program {
	
	//debug flags
	public static boolean isInDebugMode = true;
	public static PipelineType debugPipelineType = PipelineType.Push;
	public static Exercise exercise = Exercise.A;
	
	//debug arguments
	public static String pullArg = "Pull";
	public static String pushArg = "Push";	
	public static String sinkFilePath = "indexA.txt";
	public static String exerciseA = "A";
	public static String exerciseB = "B";
	
	private static Application _application;
	/*
	 * Exercise A 
	 * -> A Pull/Push sinkFileIndexPath
	 * -> B Pull/Push sinkFileIndexPath sindFilePath
	 */
	public static void main(String[] args) {
		if(!isInDebugMode) {
			_application = new Application(args);
		} else {
			String exercisearg;
			
			switch(exercise){
			case A:
				exercisearg = exerciseA;
				switch(debugPipelineType) {
				case Pull:
					_application = new Application(new String[]{exercisearg, pullArg, sinkFilePath});
					break;
				case Push:
					_application = new Application(new String[]{exercisearg, pushArg, sinkFilePath});
					break;
				}
				break;
			case B:
				exercisearg = exerciseB;
				System.out.println("Not implemented!");
				break;
			}
			
			
		}
		_application.run();
	}

}