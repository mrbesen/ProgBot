package Bot;

import java.awt.Robot;
import java.util.Scanner;

import Files.Y_Filereader;

public class Bot {

	private static Bot bot;
	public CommandManager commandmanager;
	private Robot robot;
	public String[] code;

	private String path = "/home/yannis/Dropbox/eclipse/workspace/Programmable Bot/prog.bot";

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

		if(path.equalsIgnoreCase("")) {
			System.out.println("Enter path to *.bot script-file");
			Scanner sca = new Scanner(System.in);
			path = sca.nextLine();
			sca.close();
		}

		//load programm
		Y_Filereader fr = new Y_Filereader(path);
		code = fr.read();

		commandmanager = new CommandManager();
		
		commandmanager.init().interpret(code);

		return this;
	}
}