package main.at.fhv.itb5.systemarchitecture.ue1.indsys.application;

import java.io.File;

public class RunDescriptor {
	private Exercise _exercise;
	private PipelineType _pipelineType;
	private File _sinkFile;
	
	public RunDescriptor(Exercise exercise, PipelineType pipelineType, File sinkFile) {
		_exercise = exercise;
		_pipelineType = pipelineType;
		_sinkFile = sinkFile;
	}
	
	public Exercise getExercise() {
		return _exercise;
	}
	
	public PipelineType getPipelineType() {
		return _pipelineType;
	}
	
	public File getSinkFile() {
		return _sinkFile;
	}
}
