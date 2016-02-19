package Bot;

import java.awt.Robot;
import java.util.Scanner;

import Files.Y_Filereader;

public class Bot {

	private static Bot bot;
	private CommandManager commandmanager;
	private Robot robot;
	public String[] code;
	private NummberManager numm;

	private String path = "/home/yannis/Dropbox/eclipse/workspace/Programmable Bot/prog.bot";

	public static void main(String[] args)  {
		getBot().init();
	}
	
	
	//============================gets
	
	public static Bot getBot() {
		if(bot== null) 
			bot = new Bot();
		return bot;
	}

	public Robot getRobot() {
		if(robot == null) {
			try {
				robot = new Robot();
			} catch (Exception e) {}
		}
		return robot;
	}
	
	public NummberManager getNumM() {
		if (numm == null) 
			numm = new NummberManager();
		return numm;
	}
	
	public CommandManager getCmdManager() {
		if(commandmanager == null)
			commandmanager = new CommandManager();
		return commandmanager;
	}
	
	
	

	public Bot init() {

		if(path.equalsIgnoreCase("")) {
			System.out.println("Enter path to *.bot script-file");
			Scanner sca = new Scanner(System.in);
			path = sca.nextLine();
			sca.close();
		}

		//load programm
		Y_Filereader fr = new Y_Filereader(path);
		code = fr.read();


		CommandManager.init();
		getCmdManager().interpret(code);

		return this;
	}
}