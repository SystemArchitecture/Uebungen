package main.at.fhv.itb5.systemarchitecture.ue2.source;

import java.io.StreamCorruptedException;

import javax.media.jai.PlanarImage;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;
import main.at.fhv.itb5.systemarchitecture.ue2.benchmark.BenchmarkCallback;

public class BenchmarkImageSourceActive extends ImageSourceActive {

	private BenchmarkCallback _benchmarkCallback;
	private int _benchmarkIterations;
	
	public BenchmarkImageSourceActive(BenchmarkCallback benchmarkCallback, int benchmarkIterations, PlanarImage sourceImage, Writeable<PlanarImage> successor) {
		super(sourceImage, successor);
		_benchmarkCallback = benchmarkCallback;
		_benchmarkIterations = benchmarkIterations;
	}
	
	private int currentIteration;
	
	@Override
	public void process() {
		try {
			if(_benchmarkIterations > currentIteration) {
				_benchmarkCallback.invoke();
				++currentIteration;
				write(_sourceImage);
			} else {
				stop();
			}

		} catch (StreamCorruptedException e) {
			System.out.println(e.getMessage());
			stop();
		}
	}

}
