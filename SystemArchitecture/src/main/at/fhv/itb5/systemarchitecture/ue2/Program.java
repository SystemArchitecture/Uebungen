package main.at.fhv.itb5.systemarchitecture.ue2;

import java.awt.Rectangle;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.util.LinkedList;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;
import main.at.fhv.itb5.systemarchitecture.ue2.dto.Coordinate;
import main.at.fhv.itb5.systemarchitecture.ue2.filter.CalcCentroidsFilter;
import main.at.fhv.itb5.systemarchitecture.ue2.filter.CoordinatesToStringFilter;
import main.at.fhv.itb5.systemarchitecture.ue2.filter.CutOutROIFilter;
import main.at.fhv.itb5.systemarchitecture.ue2.filter.SaveFastForwardFilter;
import main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter.DilateFilter;
import main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter.ErodeFilter;
import main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter.MedianFilter;
import main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter.ThresholdFilter;
import main.at.fhv.itb5.systemarchitecture.ue2.sink.FileSinkActive;
import main.at.fhv.itb5.systemarchitecture.ue2.sink.FileSinkStringPassive;
import main.at.fhv.itb5.systemarchitecture.ue2.source.ImageSourceActive;
import main.at.fhv.itb5.systemarchitecture.ue2.source.ImageSourcePassive;

public class Program {
	
	private static boolean isDebug = true;
	
	public static void main(String[] args) {

		if(isDebug) {
			args = new String[1];
			args[0] = "Push";
		}
		
		ParameterBlock parameterBlock = new ParameterBlock();
		parameterBlock.add(Program.class.getClassLoader().getResource("loetstellen.jpg").getPath());
		
		Rectangle roi = new Rectangle(50, 50, 440, 50);
		
		Runnable runnable = null;
		
		if(args[0] == "Push") {
			runnable = new ImageSourceActive(JAI.create("fileload", parameterBlock), 
					(Writeable<PlanarImage>) new CutOutROIFilter(roi, 
					(Writeable<PlanarImage>) new ThresholdFilter(
					(Writeable<PlanarImage>) new MedianFilter(
					(Writeable<PlanarImage>) new ErodeFilter(
					(Writeable<PlanarImage>) new ErodeFilter(
					(Writeable<PlanarImage>) new DilateFilter(
					(Writeable<PlanarImage>) new DilateFilter(
					(Writeable<PlanarImage>) new SaveFastForwardFilter("output.jpg",
					(Writeable<PlanarImage>) new CalcCentroidsFilter(
					(Writeable<LinkedList<Coordinate>>) new CalcAbsolutPositionFilter(roi,
					(Writeable<LinkedList<Coordinate>>) new CoordinatesToStringFilter( 
					new FileSinkStringPassive(new File("coordinates.txt"))))))))))))));	
		}
		else{
			runnable = new FileSinkActive(new File("coordinates.txt"),
					new CoordinatesToStringFilter(
					(Readable<LinkedList<Coordinate>>) new CalcAbsolutPositionFilter(roi, 
					(Readable<LinkedList<Coordinate>>) new CalcCentroidsFilter(
					(Readable<PlanarImage>) new SaveFastForwardFilter("output.jpg",
					(Readable<PlanarImage>) new DilateFilter(
					(Readable<PlanarImage>) new DilateFilter(
					(Readable<PlanarImage>) new ErodeFilter(
					(Readable<PlanarImage>) new ErodeFilter(
					(Readable<PlanarImage>) new MedianFilter(
					(Readable<PlanarImage>) new ThresholdFilter(
					(Readable<PlanarImage>) new CutOutROIFilter(roi, 
					new ImageSourcePassive(JAI.create("fileload", parameterBlock))))))))))))));
		}
		
		runnable.run();
	}
}
