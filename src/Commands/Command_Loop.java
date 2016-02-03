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
			
			System.out.println("LOOP");
			
			String[] split = s.split(" ", 2);
			
			if(Y_NumberTool.is_nummber(split[1])) {//add / replace
				loops.put(cmdmanager.step, Integer.parseInt(split[1]));
				System.out.println("Loop registered");
				
			} else if(split[1].equalsIgnoreCase("end")) {//end of a loop -> remove / jump back
				System.out.println("loop end");
				for(int i = cmdmanager.step; i > 0; i--) {
					if(loops.containsKey(i)) {
						if(loops.get(i) > 0) {
							loops.put(i, loops.get(i)-1);
							System.out.println("Loop gefunden!");
							cmdmanager.step = i;//wrid von command manager noch erhöt
						}
					}
				}
			}
			return true;
		}
		return false;
	}
}