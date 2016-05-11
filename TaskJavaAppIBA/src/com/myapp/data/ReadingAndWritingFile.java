package com.myapp.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ReadingAndWritingFile {
	
	private String path;
	private List<String> list_out, list_err;
	private final String lineSeparator = System.getProperty("line.separator");
	
	/**
	 * @param path path to the file
	 */
	public ReadingAndWritingFile(String path) {
		this.path = path;
		list_out = new ArrayList<String>();
		list_err = new ArrayList<String>();
	}
	
	/**
	 * @param lineSeparator paragraph
	 * @param st class StringTokenizer string delimiter
	 * @param list_out data output (key and value)
	 * @param list_err error output
	 */
	public void readingFile() {
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			while((line = br.readLine()) != null) {
				boolean isContain = line.contains(" = ");
				if(isContain) {
					StringTokenizer st = new StringTokenizer(line, " = ");
					list_out.add(st.nextToken() + lineSeparator);
					list_out.add(st.nextToken() + lineSeparator);
				}else {
					list_err.add(line + lineSeparator);
				}			
			}			
		} catch (IOException e) {
			System.out.println("Error! The path <" + path + "> is wrong.");
		}
	}
	
	public void writingFile() {
		
		try(BufferedWriter bwOut = new BufferedWriter(new FileWriter("file_out.txt"));
				BufferedWriter bwErr = new BufferedWriter(new FileWriter("file_err.txt"))) {
			
			for(String list : list_out) {
				bwOut.write(list);	
			}
			bwOut.flush();
			
			for(String list : list_err) {
				bwErr.write(list);
			}
			bwErr.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
