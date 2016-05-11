package com.myapp.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RegistryExecution {
	
	private String path;
	private List<String> list_key;
	private List<String> list_value;	
	private List<String> list_err;
	private final String lineSeparator = System.getProperty("line.separator");
	
	/**
	 * @param path path in the registry
	 */
	public RegistryExecution(String path) {
		this.path = path;
		list_key = new ArrayList<String>();
		list_value = new ArrayList<String>();
		list_err = new ArrayList<String>();
	}
	
	/**
	 * @param outputBuffer content path
	 *  @param outputComponents string delimiter
	 *  @param list_key data output (key=value)
	 *  @param list_value value container
	 *  @param list_err error output 
	 */
	public void readingRegistry() {
		try {
			Process process = Runtime.getRuntime().exec("reg query " + path);
			BufferedReader brOut = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader brErr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
								
			StringBuffer outputBuffer = new StringBuffer();
			String readLine;
			int count = 0;
			while((readLine = brOut.readLine()) != null) {
				outputBuffer.append(readLine);
				// Ignore empty line and key
				if(count>1) {
					String[] outputComponents = outputBuffer.toString().split("  ");
					String key = outputComponents[outputComponents.length - 5];
					list_key.add(key);
					String value = outputComponents[outputComponents.length - 1];
					list_value.add(value);
					}
				count++;				
				}
			
			// if readLine != null
			if(count > 0) {
				for(int i=0; i<list_value.size(); i++) {
					list_key.add(2*i+1, (" = " + list_value.get(i) + lineSeparator));
				}
			}
						
			String lineErr;
			while((lineErr = brErr.readLine()) != null) {
				list_err.add(lineErr);
				}
			
			process.destroy();
			
			}catch (IOException e) { e.printStackTrace(); }
		}
	
	public void writingInFile() {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("rk_out.txt"));
				BufferedWriter writerErr = new BufferedWriter(new FileWriter("rk_err.txt"))) {
			
			for(String list : list_key) {
				writer.write(list);
			}
			writer.flush();
			
			for(String list : list_err) {
				writerErr.write(list);
			}
			writerErr.flush();
			
		} catch (IOException e) { e.printStackTrace(); }		
	}
	

}
