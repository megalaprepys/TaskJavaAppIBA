package com.myapp.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommandExecution {
	
	private String command;
	private List<String> list_out;
	private List<String> list_err;
	private final String lineSeparator = System.getProperty("line.separator");
	
	/**
	 * @param command input command
	 */
	public CommandExecution(String command) {
		this.command = command;
		list_out = new ArrayList<String>();
		list_err = new ArrayList<String>();
	}
	
	/**
	 * @param list_out command result
	 * @param list_err command error
	 * @param lineSeparator paragraph
	 */
	public void commandExecution() {
		try (BufferedWriter wrOut = new BufferedWriter(new FileWriter("cmd_out.txt"));
				BufferedWriter wrErr = new BufferedWriter(new FileWriter("cmd_err.txt"))){
			
			Process process = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", command});
					
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader readerErr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			
			String line;
			while((line = reader.readLine()) != null) {
				list_out.add(line + lineSeparator);
			}
			for(String list : list_out) {
				wrOut.write(list);
			}
			wrOut.flush();
			
			String lineErr;
			while((lineErr = readerErr.readLine()) != null) {
				list_err.add(lineErr + lineSeparator);
			}			
			for(String list : list_err) {
				wrErr.write(list);
			}
			wrErr.flush();
			
			process.destroy();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
