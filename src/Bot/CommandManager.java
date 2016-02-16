package Bot;

import java.util.ArrayList;
import java.util.List;

import Commands.Command;
import Commands.Command_Keyboard;
import Commands.Command_Loop;
import Commands.Command_Mouse;
import Commands.Command_Move;
import Commands.Command_Position;
import Commands.Command_Print;
import Commands.Command_Wait;

public class CommandManager {

	List<Command> cmds = new ArrayList<Command>();

	public boolean run = true;
	public int step = 0;
	
	public CommandManager init() {
		cmds.add(new Command_Move());
		cmds.add(new Command_Print());
		cmds.add(new Command_Wait());
		cmds.add(new Command_Keyboard());
		cmds.add(new Command_Mouse());
		cmds.add(new Command_Position());
		cmds.add(new Command_Loop());
		cmds.add(Bot.getBot().getNumM());
		
		return this;
	}


	public CommandManager interpret(String[] s) {
		while(run) {
			if(step == s.length) {
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