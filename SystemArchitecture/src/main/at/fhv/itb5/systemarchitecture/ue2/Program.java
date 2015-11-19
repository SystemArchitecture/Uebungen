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
import main.at.fhv.itb5.systemarchitecture.ue2.pipe.SyncQueudPipe;
import main.at.fhv.itb5.systemarchitecture.ue2.sink.FileSinkActive;
import main.at.fhv.itb5.systemarchitecture.ue2.sink.FileSinkStringPassive;
import main.at.fhv.itb5.systemarchitecture.ue2.source.ImageSourceActive;
import main.at.fhv.itb5.systemarchitecture.ue2.source.ImageSourcePassive;

public class Program {
	
	private static boolean isDebug = true;
	
	public static void main(String[] args) {

		if(isDebug) {
			args = new String[2];
			args[0] = "Push";
			args[1] = "B";
		}
		
		ParameterBlock parameterBlock = new ParameterBlock();
		parameterBlock.add(Program.class.getClassLoader().getResource("loetstellen.jpg").getPath());
		
		Rectangle roi = new Rectangle(50, 50, 440, 50);
		
		if(args[1] == "A"){
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
		
		} else {
			// init pipes
			SyncQueudPipe<PlanarImage> sourceROIPipe = new SyncQueudPipe<>();
			SyncQueudPipe<PlanarImage> roiThresholdPipe = new SyncQueudPipe<>();
			SyncQueudPipe<PlanarImage> thresholdMedianPipe = new SyncQueudPipe<>();
			SyncQueudPipe<PlanarImage> medianErodePipe = new SyncQueudPipe<>();
			SyncQueudPipe<PlanarImage> erodeErodePipe = new SyncQueudPipe<>();
			SyncQueudPipe<PlanarImage> erodeDilatePipe = new SyncQueudPipe<>();
			SyncQueudPipe<PlanarImage> dilateDilatePipe = new SyncQueudPipe<>();
			SyncQueudPipe<PlanarImage> dilateSaveImagePipe = new SyncQueudPipe<>();
			SyncQueudPipe<PlanarImage> saveImageCentroidsPipe = new SyncQueudPipe<>();
			SyncQueudPipe<LinkedList<Coordinate>> centroidsAbsolutePositionPipe = new SyncQueudPipe<>();
			SyncQueudPipe<LinkedList<Coordinate>> absolutePositionCoordinatesPipe = new SyncQueudPipe<>();
			SyncQueudPipe<String> coordinatesFileSinkPipe = new SyncQueudPipe<>();
			
			LinkedList<Runnable> runnables = new LinkedList<>();
			// init filters
			runnables.add(new ImageSourceActive(JAI.create("fileload", parameterBlock), (Writeable<PlanarImage>) sourceROIPipe));
			runnables.add(new CutOutROIFilter(roi, sourceROIPipe, roiThresholdPipe));
			runnables.add(new ThresholdFilter(roiThresholdPipe, thresholdMedianPipe));
			runnables.add(new MedianFilter(thresholdMedianPipe, medianErodePipe));
			runnables.add(new ErodeFilter(medianErodePipe, erodeErodePipe));
			runnables.add(new ErodeFilter(erodeErodePipe, erodeDilatePipe));
			runnables.add(new DilateFilter(erodeDilatePipe, dilateDilatePipe));
			runnables.add(new DilateFilter(dilateDilatePipe, dilateSaveImagePipe));
			runnables.add(new SaveFastForwardFilter("output.jpg", dilateSaveImagePipe, saveImageCentroidsPipe));
			runnables.add(new CalcCentroidsFilter(saveImageCentroidsPipe, centroidsAbsolutePositionPipe));
			runnables.add(new CalcAbsolutPositionFilter(roi, centroidsAbsolutePositionPipe, absolutePositionCoordinatesPipe));
			runnables.add(new CoordinatesToStringFilter(absolutePositionCoordinatesPipe, coordinatesFileSinkPipe));
			runnables.add(new FileSinkActive(new File("coordinates.txt"), coordinatesFileSinkPipe));
			
			for(Runnable runnable : runnables) {
				new Thread(runnable).start();
			}
		}
	}
}
