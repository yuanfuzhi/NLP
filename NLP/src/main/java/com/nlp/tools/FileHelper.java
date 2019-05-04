package com.nlp.tools;

import java.io.File;

public class FileHelper {
	public  static String getFileURL(String text) {
		File file = new File("F:\\BYSJ\\TestDemo\\"); 
		File[] tempFile = file.listFiles(); 
		String fileURL = null;
		for(int i = 0; i < tempFile.length; i++){ 
			if(tempFile[i].getName().startsWith(text.substring(0,10))){				
				fileURL = tempFile[i].getAbsolutePath();					
			}	
		}
		return fileURL;
	}
}
