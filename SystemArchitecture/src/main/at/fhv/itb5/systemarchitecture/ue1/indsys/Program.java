package main.at.fhv.itb5.systemarchitecture.ue1.indsys;

import java.util.ArrayList;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.application.Application;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.application.Exercise;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.application.PipelineType;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dto.Alignment;

public class Program {

	// debug flags
	public static boolean isInDebugMode = true;
	public static PipelineType debugPipelineType = PipelineType.Pull;
	public static Exercise exercise = Exercise.B;
	public static Alignment alignment = Alignment.Right;
	public static int lineSize = 100;

	// debug arguments
	public static String pullArg = "Pull";
	public static String pushArg = "Push";

	public static String sinkFileIndexPath = "index.txt";
	public static String sinkFilePath = "formatedFile.txt";

	public static String exerciseA = "A";
	public static String exerciseB = "B";

	public static String Left = "Left";
	public static String Right = "Right";
	public static String Center = "Center";

	private static Application _application;

	/*
	 * -> A Pull/Push sinkFileIndexPath -> B Pull/Push sinkFileIndexPath
	 * sinkFilePath alignment lineSize
	 */
	public static void main(String[] args) {
		if (!isInDebugMode) {
			_application = new Application(args);
		} else {
			ArrayList<String> debugArgs = new ArrayList<>();

			switch (exercise) {
			case A:
				debugArgs.add(exerciseA);
				break;
			case B:
				debugArgs.add(exerciseB);
				break;
			}

			switch (debugPipelineType) {
			case Pull: {
				debugArgs.add(pullArg);
				break;
			}
			case Push: {
				debugArgs.add(pushArg);
				break;
			}
			}

			debugArgs.add(sinkFileIndexPath);
			if (exercise == Exercise.B) {
				debugArgs.add(sinkFilePath);

				switch (alignment) {
				case Center:
					debugArgs.add(Center);
					break;
				case Left:
					debugArgs.add(Left);
					break;
				case Right:
					debugArgs.add(Right);
				}

				debugArgs.add(Integer.toString(lineSize));
			}

			System.out.println(debugArgs);

			_application = new Application(debugArgs.toArray(new String[debugArgs.size()]));

		}
		_application.run();
	}

}