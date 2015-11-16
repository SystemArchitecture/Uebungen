package main.at.fhv.itb5.systemarchitecture.ue1.indsys.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterUtil {
	private FileWriter _fileWriter;
	
	public FileWriterUtil(File file) {
		
		if(file.exists()) {
			file.delete();
		}
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Could not create new file!");
		}
		
		try {
			_fileWriter = new FileWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write(String line) {
		try {
			_fileWriter.write(line + "\n");
			_fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
