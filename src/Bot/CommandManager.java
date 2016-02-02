package Bot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Commands.Command;
import Commands.Command_Keyboard;
import Commands.Command_Loop;
import Commands.Command_Mouse;
import Commands.Command_Move;
import Commands.Command_Position;
import Commands.Command_Wait;

public class CommandManager {

	List<Command> cmds = new ArrayList<Command>();

	public boolean run = true;
	public int step = 0;
	
	public CommandManager() {
		cmds.add(new Command_Move());
		cmds.add(new Command_Wait());
		cmds.add(new Command_Keyboard());
		cmds.add(new Command_Mouse());
		cmds.add(new Command_Position());
		cmds.add(new Command_Loop());
	}


	public CommandManager interpret(String[] s) {
		
		(new Thread(new Runnable() {
			public void run() {
				Scanner s = new Scanner(System.in);
				while(true) {
					s.nextLine();
					run = false;
					System.out.println("Paused");
					s.nextLine();
					run = true;
					System.out.println("Resumed");
				}
			}
		})).start();
		
		
		
		
		
		
		
		
		while(run) {
			
			if(step == s.length-1) {
				run = false;
				System.exit(0);
				break;
			}
			
			String commandl = s[step].trim();
			
			for(Command cmd : cmds) {
				if(cmd.execute(commandl)) {//sucess
					try {
						Thread.sleep(5);
					} catch(Exception e) {}
					break;
				}
			}
			step ++;
		}
		return this;
	}
}