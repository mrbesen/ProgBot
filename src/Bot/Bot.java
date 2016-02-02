package Bot;

import java.awt.Robot;

import Files.Y_Filereader;

public class Bot {
	
	private static Bot bot;
	public CommandManager commandmanager;
	private Robot robot;
	public String[] code;

	public static void main(String[] args)  {
		getBot().init();
	}
	
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
	
	public Bot init() {
		//load programm
		Y_Filereader fr = new Y_Filereader("/home/yannis/Dropbox/eclipse/workspace/Programmable Bot/prog.bot");
		code = fr.read();
		
		commandmanager = new CommandManager();
		
		commandmanager.interpret(code);
		
		return this;
	}
	
	public Bot close() {//called by closed Display
		
		
		return this;
	}
}
