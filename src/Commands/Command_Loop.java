package Commands;

import java.util.HashMap;
import java.util.Map;

import Bot.CommandManager;

public class Command_Loop implements Command {

	Map<Integer, Integer> loops = new HashMap<Integer, Integer>();
	CommandManager cmdmanager = Bot.Bot.getBot().getCmdManager();
	
	
	@Override
	public String getName() {
		return "loop";
	}

	@Override
	public boolean execute(String s) {
		if(s.startsWith("loop")) {
			String[] split = s.split(" ", 2);
			
			if(Bot.Bot.getBot().getNumM().isNum(split[1])) {//add / replace
				loops.put(cmdmanager.step, Bot.Bot.getBot().getNumM().getNum(split[1]));
				
			} else if(split[1].equalsIgnoreCase("end")) {//end of a loop -> remove / jump back
				for(int i = cmdmanager.step; i >= 0; i--) {
					if(loops.containsKey(i)) {
						if(loops.get(i) > 1) {
							loops.put(i, loops.get(i)-1);//subtract to stop after final count
							cmdmanager.step = i;//wrid von command manager noch erhöt
							return true;
						} else if(loops.get(i) == Integer.MAX_VALUE) {//dont count down to make infinite
							cmdmanager.step = i;//wrid von command manager noch erhöt
							return true;
						}
					}
				}
			}
			return true;
		}
		return false;
	}
}