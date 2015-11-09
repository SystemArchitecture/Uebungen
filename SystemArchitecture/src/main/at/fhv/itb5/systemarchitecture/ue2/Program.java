package main.at.fhv.itb5.systemarchitecture.ue2;

import java.awt.image.renderable.ParameterBlock;
import javax.media.jai.JAI;

public class Program {
	public static void main(String[] args) {

		ParameterBlock parameterBlock = new ParameterBlock();
		parameterBlock.add(HelloLena.class.getClassLoader().getResource("loetstellen.jpg").getPath());
		
		Runnable runnable = new ImageSourceActive(JAI.create("fileload", parameterBlock), 
							new DisplayImageSink());
		
		runnable.run();

	}
}
