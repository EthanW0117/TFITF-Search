package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class FileDocSource extends DocSource{
	private List<File> _fileNames;
	
	public FileDocSource(String filePath) {
		_fileNames = FileFinder.GetAllFiles(filePath);
	}
	
	@Override
	public int getNumDocs() {
		// TODO Auto-generated method stub
		return _fileNames.size();
	}

	@Override
	public String getDoc(int id) {
		// TODO Auto-generated method stub
		BufferedReader cin = null;
		StringBuilder sb = new StringBuilder();
		try {
			cin = new BufferedReader(new FileReader(_fileNames.get(id)));
			String temp;
			do {
				temp = cin.readLine();
				if (temp != null)
				sb.append(temp + " ");
			}while(temp != null);
			cin.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		FileReader fin = new FileReader(_fileNames.get(id));
		return sb.toString();
	}

}
