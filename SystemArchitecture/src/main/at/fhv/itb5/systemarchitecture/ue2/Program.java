package main.at.fhv.itb5.systemarchitecture.ue2;

import java.awt.Rectangle;
import java.awt.image.renderable.ParameterBlock;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class Program {
	public static void main(String[] args) {

		ParameterBlock parameterBlock = new ParameterBlock();
		parameterBlock.add(HelloLena.class.getClassLoader().getResource("loetstellen.jpg").getPath());
		
		Rectangle roi = new Rectangle(0, 50, 448, 150);
		
		Runnable runnable = new ImageSourceActive(JAI.create("fileload", parameterBlock), 
							(Writeable<PlanarImage>) new CutOutROIFilter(roi,
							(Writeable<PlanarImage>) new ThresholdFilter(
							(Writeable<PlanarImage>) new MedianFilter(
							(Writeable<PlanarImage>) new ErodeFilter(
							(Writeable<PlanarImage>) new DisplayImageSink())))));
		
		runnable.run();
	}
}
