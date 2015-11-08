package main.at.fhv.itb5.systemarchitecture.ue1.indsys.application;

import java.io.File;

import org.omg.stub.java.rmi._Remote_Stub;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dto.Alignment;

public class RunDescriptor {
	private Exercise _exercise;
	private PipelineType _pipelineType;
	private File _sinkIndexFile;
	private File _sinkFormatedFile;
	private Alignment _alignment;
	private int _lineSize;
	
	public RunDescriptor(Exercise exercise, PipelineType pipelineType, File sinkIndexFile) {
		_exercise = exercise;
		_pipelineType = pipelineType;
		_sinkIndexFile = sinkIndexFile;
	}
	
	public RunDescriptor(Exercise exercise, PipelineType pipelineType, File sinkIndexFile, File sinkFormatedFile, Alignment alignment, int lineSize) {
		this(exercise, pipelineType, sinkIndexFile);
		_sinkFormatedFile = sinkFormatedFile;
		_alignment = alignment;
		_lineSize = lineSize;
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
	
	public Alignment getAlignment() {
		return _alignment;
	}

	public int getLineSize() {
		return _lineSize;
	}
}
