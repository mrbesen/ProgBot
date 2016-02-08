package Commands;

import java.util.HashMap;
import java.util.Map;

import Bot.CommandManager;
import Utils.Y_NumberTool;

public class Command_Loop implements Command {

	Map<Integer, Integer> loops = new HashMap<Integer, Integer>();
	CommandManager cmdmanager = Bot.Bot.getBot().commandmanager;
	
	
	@Override
	public String getName() {
		return "loop";
	}

	@Override
	public boolean execute(String s) {
		if(s.startsWith("loop")) {
			String[] split = s.split(" ", 2);
			
			if(Y_NumberTool.is_nummber(split[1])) {//add / replace
				loops.put(cmdmanager.step, Integer.parseInt(split[1]));
				
			} else if(split[1].equalsIgnoreCase("end")) {//end of a loop -> remove / jump back
				for(int i = cmdmanager.step; i >= 0; i--) {
					if(loops.containsKey(i)) {
						if(loops.get(i) > 1) {
							loops.put(i, loops.get(i)-1);//subtract to stop after final count
							cmdmanager.step = i;//wrid von command manager noch erhöt
							return true;
						} else if(loops.get(i) == Integer.MIN_VALUE) {//dont count down to make infinite
							cmdmanager.step = i;//wrid von command manager noch erhöt
							return true;
						}
					}
				}
			} else if(split[1].equalsIgnoreCase("infinite")) {
				loops.put(cmdmanager.step, Integer.MIN_VALUE);
			}
			return true;
		}
		return false;
	}
}