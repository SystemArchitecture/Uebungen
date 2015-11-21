package main.at.fhv.itb5.systemarchitecture.ue2.sink;

import java.io.File;
import java.io.StreamCorruptedException;

import main.at.fhv.itb5.systemarchitecture.ue2.benchmark.BenchmarkCallback;
import main.at.fhv.itb5.systemarchitecture.ue2.benchmark.BenchmarkElement;

public class BenchmarkFileSinkPassive extends FileSinkStringPassive implements BenchmarkElement{

	public BenchmarkFileSinkPassive(File file) {
		super(file);
	}
	
	@Override
	public void write(String value) throws StreamCorruptedException {
		super.write(value);
		_benchmarkCallback.invoke();
	}
	
	private BenchmarkCallback _benchmarkCallback;
	@Override
	public void registerBenchmarkCallback(BenchmarkCallback callback) {
		_benchmarkCallback = callback;
	}

}
