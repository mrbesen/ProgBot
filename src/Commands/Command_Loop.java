package Commands;

import java.util.ArrayList;
import java.util.List;

import Utils.Y_NumberTool;

public class Command_Loop implements Command {

	List<Loop> loops = new ArrayList<Loop>();
	
	@Override
	public String getName() {
		return "loop";
	}

	@Override
	public boolean execute(String s) {
		if(s.startsWith("loop")) {
			String[] split = s.split(" ", 2);
			
			if(Y_NumberTool.is_nummber(split[1])) {//add
				
			}
			
			if(split[1].equalsIgnoreCase("end")) {//end of a loop -> remove / jump back
				
			}
			
			return true;
		}
		return false;
	}

	
	public class Loop {
		int step;
		int times_left;
	}
}