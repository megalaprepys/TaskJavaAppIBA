package com.myapp.data;

public class InputArgs {
	
	private String[] args;
	
	public InputArgs(String[] args) {
		this.args = args;
	}
	
	public void choseArgs() {
		if(args[0].equals("-f")) {
			ReadingAndWritingFile f = new ReadingAndWritingFile(args[1]);
			f.readingFile();
			f.writingFile();
			System.out.println("COMPLETED SUCCESSFULLY");
			
		}else if(args[0].equals("-cmd")) {
			CommandExecution e = new CommandExecution(args[1]);
			e.commandExecution();
			System.out.println("COMPLETED SUCCESSFULLY");
			
		}else if(args[0].equals("-rk")) {
			RegistryExecution re = new RegistryExecution(args[1]);
			re.readingRegistry();
			re.writingInFile();
			System.out.println("COMPLETED SUCCESSFULLY");
			
		}else {
			System.out.println("Key is wrong or not found!");
		}
	}

}
