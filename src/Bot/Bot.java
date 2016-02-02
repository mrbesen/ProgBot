package Bot;

import java.awt.Robot;
import java.util.Scanner;

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
		System.out.println("Enter path to *.bot script-file");
		String path = (new Scanner(System.in)).nextLine();

		//load programm
		Y_Filereader fr = new Y_Filereader(path);
		code = fr.read();

		commandmanager = new CommandManager();

		commandmanager.interpret(code);

		return this;
	}

	public Bot close() {//called by closed Display


		return this;
	}
}
