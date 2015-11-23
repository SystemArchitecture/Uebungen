package main.at.fhv.itb5.systemarchitecture.ue3;

public class FileLoader {
	
	private String _path;
	
	public FileLoader() {
		_path = "";
	}
	
	public void setImagePath(String value) {
		_path = value;
		System.out.println(value);
	}
	
	public String getImagePath() {
		return _path;
	}
}
