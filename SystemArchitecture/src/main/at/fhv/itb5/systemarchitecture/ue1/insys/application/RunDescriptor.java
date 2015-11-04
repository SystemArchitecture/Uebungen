package main.at.fhv.itb5.systemarchitecture.ue1.insys.application;

public class RunDescriptor {
	private PipelineType _pipelineType;
	
	private String _sourceFilePath;
	
	private String _sinkFilePath;
	
	public RunDescriptor(PipelineType pipelineType, String sourceFilePath, String sinkFilePaht) {
		_pipelineType = pipelineType;
		_sourceFilePath = sourceFilePath;
		_sinkFilePath = sinkFilePaht;
	}
	
	public PipelineType getPipelineType() {
		return _pipelineType;
	}
	
	public String getSourceFilePath() {
		return _sourceFilePath;
	}
	
	public String getSinkFilePaht() {
		return _sinkFilePath;
	}
}
