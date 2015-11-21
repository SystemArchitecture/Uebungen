package main.at.fhv.itb5.systemarchitecture.ue2.sink;

import java.io.File;
import java.io.StreamCorruptedException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue2.benchmark.BenchmarkCallback;
import main.at.fhv.itb5.systemarchitecture.ue2.benchmark.BenchmarkElement;

public class BenchmarkFileSinkActive extends FileSinkActive implements BenchmarkElement {

	public BenchmarkFileSinkActive(File file, Readable<String> predeseccor) {
		super(file, predeseccor);
	}
	
	@Override
	public void run() {
		String value;
		do{
			try {
				value = read();
				if(value != null) {
					_fileWriterUtil.write(value);
					_callback.invoke();
				}
			} catch (StreamCorruptedException e) {
				value = null;
			}
		}while(value != null);
	}
	
	private BenchmarkCallback _callback;
	@Override
	public void registerBenchmarkCallback(BenchmarkCallback callback) {
		_callback = callback;
	}

}
