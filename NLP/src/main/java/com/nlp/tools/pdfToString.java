package com.nlp.tools;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class pdfToString {
	static String text;
	private static PDFTextStripper pdfStripper;
	
	public  static String  getPdfText(String filepath) throws IOException {
		File file = new File(filepath);
	      PDDocument document = null;
	      try {
			document = PDDocument.load(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     pdfStripper = new PDFTextStripper();
	      try {
			text = pdfStripper.getText(document);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      try {
			document.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return text;
	}
	
	public  static String  getPdfTextfromFile(File inputfile) throws IOException {
	     PDDocument document = null;
	      try {
			document = PDDocument.load(inputfile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     pdfStripper = new PDFTextStripper();
	      try {
			text = pdfStripper.getText(document);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      try {
			document.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return text;
	}
	

}
