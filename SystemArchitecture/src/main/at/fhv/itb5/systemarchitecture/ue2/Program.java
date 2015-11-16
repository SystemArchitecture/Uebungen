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
import main.at.fhv.itb5.systemarchitecture.ue2.pipe.SyncBufferPipe;
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
		LinkedList<Runnable> runnables = new LinkedList<>(); 
		
		if(args[1] == "A"){

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
		
		} else {
			// init pipes
			SyncBufferPipe<PlanarImage> sourceROIPipe = new SyncBufferPipe<>();
			SyncBufferPipe<PlanarImage> roiThresholdPipe = new SyncBufferPipe<>();
			SyncBufferPipe<PlanarImage> thresholdMedianPipe = new SyncBufferPipe<>();
			SyncBufferPipe<PlanarImage> medianErodePipe = new SyncBufferPipe<>();
			SyncBufferPipe<PlanarImage> erodeErodePipe = new SyncBufferPipe<>();
			SyncBufferPipe<PlanarImage> erodeDilatePipe = new SyncBufferPipe<>();
			SyncBufferPipe<PlanarImage> dilateDilatePipe = new SyncBufferPipe<>();
			SyncBufferPipe<PlanarImage> dilateSaveImagePipe = new SyncBufferPipe<>();
			SyncBufferPipe<PlanarImage> saveImageCentroidsPipe = new SyncBufferPipe<>();
			SyncBufferPipe<LinkedList<Coordinate>> centroidsAbsolutePositionPipe = new SyncBufferPipe<>();
			SyncBufferPipe<LinkedList<Coordinate>> absolutePositionCoordinatesPipe = new SyncBufferPipe<>();
			SyncBufferPipe<String> coordinatesFileSinkPipe = new SyncBufferPipe<>();
				
			// init filters
			ImageSourceActive source = new ImageSourceActive(JAI.create("fileload", parameterBlock), (Writeable<PlanarImage>) sourceROIPipe);
			CutOutROIFilter roiFilter = new CutOutROIFilter(roi, sourceROIPipe, roiThresholdPipe);
			ThresholdFilter thresholdFilter = new ThresholdFilter(roiThresholdPipe, thresholdMedianPipe);
			MedianFilter medianFilter = new MedianFilter(thresholdMedianPipe, medianErodePipe);
			ErodeFilter erodeFilter = new ErodeFilter(medianErodePipe, erodeErodePipe);
			ErodeFilter erodeFilter2 = new ErodeFilter(erodeErodePipe, erodeDilatePipe);
			DilateFilter dilateFilter = new DilateFilter(erodeDilatePipe, dilateDilatePipe);
			DilateFilter dilateFilter2 = new DilateFilter(erodeDilatePipe, dilateSaveImagePipe);
			SaveFastForwardFilter saveFastForwardFilter = new SaveFastForwardFilter("output.jpg", dilateSaveImagePipe, saveImageCentroidsPipe);
			CalcCentroidsFilter calcCentroidsFilter = new CalcCentroidsFilter(saveImageCentroidsPipe, centroidsAbsolutePositionPipe);
			CalcAbsolutPositionFilter calcAbsolutPositionFilter = new CalcAbsolutPositionFilter(roi, centroidsAbsolutePositionPipe, absolutePositionCoordinatesPipe);
			CoordinatesToStringFilter coordinatesToStringFilter = new CoordinatesToStringFilter(absolutePositionCoordinatesPipe, coordinatesFileSinkPipe);
			FileSinkActive fileSinkStringPassive = new FileSinkActive(new File("coordinates.txt"), coordinatesFileSinkPipe);
		}
		
		//TODO run!!!
	}
}
