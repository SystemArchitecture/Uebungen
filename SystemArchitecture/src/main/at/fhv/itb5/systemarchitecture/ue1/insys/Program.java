package main.at.fhv.itb5.systemarchitecture.ue1.insys;

import main.at.fhv.itb5.systemarchitecture.ue1.insys.application.Application;
import main.at.fhv.itb5.systemarchitecture.ue1.insys.application.PipelineType;

public class Program {
	
	//debug flags
	public static boolean isInDebugMode = true;
	public static PipelineType debugPipelineType = PipelineType.Push;
	
	//debug arguments
	public static String pullArg = "Pull";
	public static String pushArg = "Push";	
	public static String sourceFilePath = "aliceInWonderland.txt";
	public static String sinkFilePath = "index.txt";
	
	private static Application _application;

	public static void main(String[] args) {
		if(isInDebugMode) {
			_application = new Application(args);
		} else {
			switch(debugPipelineType) {
			case Pull:
				_application = new Application(new String[]{pullArg, sourceFilePath, sinkFilePath});
				break;
			case Push:
				_application = new Application(new String[]{pushArg, sourceFilePath, sinkFilePath});
				break;
			default:
				//should never be reache
				break;
			
			}
		}
		_application.run();
	}

}