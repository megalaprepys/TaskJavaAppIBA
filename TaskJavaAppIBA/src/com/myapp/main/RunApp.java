package com.myapp.main;

import com.myapp.data.InputArgs;

public class RunApp {
	
	private static final String USAGE = "USAGE: java com.myapp.main.RunApp <enter two args>";

	public static void main(String[] args) {
		if(args.length != 2) {
			System.out.println(USAGE);
			System.out.println("For example:");
			System.out.println("\n -f newFile.txt");
			System.out.println("\n -cmd dir");
			System.out.println("\n -rk HKEY_USERS\\S-1-5-18\\Environment");
			
		}else {
				InputArgs inputArgs = new InputArgs(args);
				inputArgs.choseArgs();
		}
	}	

}
