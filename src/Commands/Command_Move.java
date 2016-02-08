package Commands;

import Bot.Bot;
import Utils.Y_NumberTool;

public class Command_Move implements Command {

	int cooldown = 0;
	long lastremoval = System.currentTimeMillis();
	
	@Override
	public String getName() {
		return "move";
	}

	@Override
	public boolean execute(String s) {
		if(s.startsWith("move")) {
			//cool down stuff:
			cooldown -= (int) ((System.currentTimeMillis() - lastremoval) / 100);//pro 1/10 second remove one cooldown point.
			lastremoval = System.currentTimeMillis();
			if(cooldown < 0) {
				cooldown = 0;
			}			
			if(cooldown > 400) {//didn't cool down for a long time
				System.err.println("The Mouse is moved to quickly! The Boot is hibernating for 10 seconds!");
				try {
					Thread.sleep(10000);
				} catch(Exception e) {}
			}
			
			
			//real command:
			String split[] = s.split(" ", 3);
			if(Y_NumberTool.is_nummber(split[1]) & Y_NumberTool.is_nummber(split[2])) {
				Bot.getBot().getRobot().mouseMove(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
				System.out.println("Mouse Moved to: (" + split[1] + "|" + split[2] + ") cooldown: " + cooldown);
				cooldown = cooldown + 35;//needs to cooldown 2/10 seconds
				return true;
			} else {
				System.err.println("Command wird ignoriert, da Fehlerhaft: " + s);
			}
		}
		return false;
	}

}
