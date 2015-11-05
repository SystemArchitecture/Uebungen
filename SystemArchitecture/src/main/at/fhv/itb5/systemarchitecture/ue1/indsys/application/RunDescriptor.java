package main.at.fhv.itb5.systemarchitecture.ue1.indsys.application;

import java.io.File;

public class RunDescriptor {
	private Exercise _exercise;
	private PipelineType _pipelineType;
	private File _sinkIndexFile;
	private File _sinkFormatedFile;
	
	public RunDescriptor(Exercise exercise, PipelineType pipelineType, File sinkIndexFile) {
		_exercise = exercise;
		_pipelineType = pipelineType;
		_sinkIndexFile = sinkIndexFile;
	}
	
	public RunDescriptor(Exercise exercise, PipelineType pipelineType, File sinkIndexFile, File sinkFormatedFile) {
		this(exercise, pipelineType, sinkIndexFile);
		_sinkFormatedFile = sinkFormatedFile;
	}
	
	public Exercise getExercise() {
		return _exercise;
	}
	
	public PipelineType getPipelineType() {
		return _pipelineType;
	}
	
	public File getSinkIndexFile() {
		return _sinkIndexFile;
	}
	
	public File getSinkFormatedFile() {
		return _sinkFormatedFile;
	}
}
