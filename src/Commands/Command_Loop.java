package Commands;

import java.util.HashMap;
import java.util.Map;

import Bot.Bot;
import Bot.CommandManager;

public class Command_Loop implements Command {

	Map<Integer, Integer> loops = new HashMap<Integer, Integer>();
	CommandManager cmdmanager = Bot.getBot().getCmdManager();
	
	@Override
	public String getName() {
		return "loop";
	}

	@Override
	public int execute(String s) {
		if(s.startsWith("loop")) {
			String[] split = s.split(" ", 2);
			
			if(Bot.getBot().getNumM().isNum(split[1]) & !split[1].equalsIgnoreCase("end")) {//add / replace
				loops.put(cmdmanager.step, Bot.getBot().getNumM().getNum(split[1]));
				return 1;
			} else if(split[1].equalsIgnoreCase("end")) {//end of a loop -> remove / jump back
				for(int i = cmdmanager.step; i >= 0; i--) {
					if(loops.containsKey(i)) {
						if(loops.get(i) > 1 & loops.get(i) != Integer.MAX_VALUE)//noch borhanden & nicht infinity 
							loops.put(i, loops.get(i)-1);//subtract to stop after final count
						cmdmanager.step = i;//wrid von command manager noch erhöt
						return 1;
					}
				}
			}
		}
		return Integer.MIN_VALUE;//error
	}

	@Override
	public boolean canExecute(String cmd) {
		return cmd.startsWith("loop");
	}
}