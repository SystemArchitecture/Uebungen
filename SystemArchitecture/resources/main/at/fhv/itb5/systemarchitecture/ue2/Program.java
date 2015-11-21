package main.at.fhv.itb5.systemarchitecture.ue2;

import java.awt.Rectangle;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;
import main.at.fhv.itb5.systemarchitecture.ue2.benchmark.BenchmarkCallback;
import main.at.fhv.itb5.systemarchitecture.ue2.dto.Coordinate;
import main.at.fhv.itb5.systemarchitecture.ue2.filter.CalcCentroidsFilter;
import main.at.fhv.itb5.systemarchitecture.ue2.filter.CoordinatesToStringFilter;
import main.at.fhv.itb5.systemarchitecture.ue2.filter.CutOutROIFilter;
import main.at.fhv.itb5.systemarchitecture.ue2.filter.SaveFastForwardFilter;
import main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter.DilateFilter;
import main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter.ErodeFilter;
import main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter.MedianFilter;
import main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter.ThresholdFilter;
import main.at.fhv.itb5.systemarchitecture.ue2.helper.Statistics;
import main.at.fhv.itb5.systemarchitecture.ue2.pipe.SyncQueudPipe;
import main.at.fhv.itb5.systemarchitecture.ue2.sink.BenchmarkFileSinkActive;
import main.at.fhv.itb5.systemarchitecture.ue2.sink.BenchmarkFileSinkPassive;
import main.at.fhv.itb5.systemarchitecture.ue2.sink.FileSinkActive;
import main.at.fhv.itb5.systemarchitecture.ue2.sink.FileSinkStringPassive;
import main.at.fhv.itb5.systemarchitecture.ue2.source.BenchmarkImageSourceActive;
import main.at.fhv.itb5.systemarchitecture.ue2.source.ImageSourceActive;
import main.at.fhv.itb5.systemarchitecture.ue2.source.ImageSourcePassive;

public class Program {
	
	private static boolean isDebug = true;
	private static boolean inBenchmarkMode = true;
	private static int benchmarkIterations = 50;
	
	private static long startTime;
	
	public static void main(String[] args) {

		if(isDebug) {
			args = new String[2];
			args[0] = "Push";
			args[1] = "B";
		}
		
		ParameterBlock parameterBlock = new ParameterBlock();
		parameterBlock.add(Program.class.getClassLoader().getResource("loetstellen.jpg").getPath());	
		Rectangle roi = new Rectangle(50, 50, 440, 50);
		
		Queue<Long> startTimeQueue = new LinkedList<>(); 
		LinkedList<Long> results = new LinkedList<>();	
		
		BenchmarkCallback sourceCallback = new BenchmarkCallback() {	
			@Override
			public void invoke() {
				if(results.size() == 0) {
					startTime = System.currentTimeMillis();
				}
				startTimeQueue.add(System.currentTimeMillis());
			}
		};
		
		BenchmarkCallback sinkCallback = new BenchmarkCallback() {
			@Override
			public void invoke() {
				results.add(System.currentTimeMillis() - startTimeQueue.poll());
				
				if(results.size() == benchmarkIterations) {
					System.out.println("Times Messured -> " + results.size());
					System.out.println(results.toString());
					Collections.sort(results);
					System.out.println("Median Time -> " + Statistics.median(results.toArray(new Long[results.size()])) + " milliseconds!");
					System.out.println("Mean Time -> " + Statistics.mean(results.toArray(new Long[results.size()])) + " milliseconds!");
					System.out.println("Overal runtime -> " + (System.currentTimeMillis() - startTime));
				}
			}
		};
		
		if(args[1] == "A"){
			Runnable runnable = null;

			if(args[0] == "Push") {
				
				if(inBenchmarkMode) {
					System.out.println("Push mode single thread! Benchmark Iterations " + benchmarkIterations);
					
					BenchmarkFileSinkPassive benchmarkFileSinkPassive = new BenchmarkFileSinkPassive(new File("coordinates.txt"));
					benchmarkFileSinkPassive.registerBenchmarkCallback(sinkCallback);
					
					runnable = new BenchmarkImageSourceActive(sourceCallback, benchmarkIterations, JAI.create("fileload", parameterBlock), 
							buildPushPipe(benchmarkFileSinkPassive, roi));
				}		
				else {
					runnable = new ImageSourceActive(JAI.create("fileload", parameterBlock), 
							buildPushPipe(new FileSinkStringPassive(new File("coordinates.txt")),roi));
				}
				
			}
			else{
				runnable = buildPullPipeline(roi, JAI.create("fileload", parameterBlock));
			}
			
			runnable.run();
			
		} else {
			if(inBenchmarkMode) {
				System.out.println("Multithreaded pipeline! Benchmark Iterations " + benchmarkIterations);
			}
			
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
			if(inBenchmarkMode) {			
				runnables.add(new BenchmarkImageSourceActive(sourceCallback, benchmarkIterations, JAI.create("fileload", parameterBlock), (Writeable<PlanarImage>) sourceROIPipe));
			}else {
				runnables.add(new ImageSourceActive(JAI.create("fileload", parameterBlock), (Writeable<PlanarImage>) sourceROIPipe));
			}
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
			
			if(inBenchmarkMode) {
				BenchmarkFileSinkActive benchmarkFileSinkActive = new BenchmarkFileSinkActive(new File("coordinates.txt"), coordinatesFileSinkPipe);
				benchmarkFileSinkActive.registerBenchmarkCallback(sinkCallback);
				runnables.add(benchmarkFileSinkActive);
			} else {
				runnables.add(new FileSinkActive(new File("coordinates.txt"), coordinatesFileSinkPipe));
			}
			
			for(Runnable runnable : runnables) {
				new Thread(runnable).start();
			}
		}
	}
	
	private static Writeable<PlanarImage> buildPushPipe(Writeable<String> sink, Rectangle roi) {
		return (Writeable<PlanarImage>) new CutOutROIFilter(roi, 
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
						sink)))))))))));
	}
	
	private static Runnable buildPullPipeline(Rectangle roi, PlanarImage image) {
		return new FileSinkActive(new File("coordinates.txt"),
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
				new ImageSourcePassive(image)))))))))))));
	}
}
