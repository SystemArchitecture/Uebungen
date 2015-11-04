package main.at.fhv.itb5.systemarchitecture.ue1.indsys.dao;

public class SimpleLine {
	private String _value;
	private int _lineNumber;
	
	public SimpleLine(String value, int lineNumber) {
		_value = value;
		_lineNumber = lineNumber;
	}
	
	public String getValue() {
		return _value;
	}
	
	public int getLinenumber() {
		return _lineNumber;
	}
	
	@Override
	public String toString() {
		return _lineNumber + " -> " + _value;
	}

	public boolean isEmpty() {
		return _value.isEmpty();
	}
}
