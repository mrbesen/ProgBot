package Commands;

import Bot.Bot;
import Utils.Y_NumberTool;

public class Command_Move implements Command {

	@Override
	public String getName() {
		return "move";
	}

	@Override
	public boolean execute(String s) {
		if(s.startsWith("move")) {
			String split[] = s.split(" ", 3);
			if(Y_NumberTool.is_nummber(split[1]) & Y_NumberTool.is_nummber(split[2])) {
				Bot.getBot().getRobot().mouseMove(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
				System.out.println("Mouse Moved to: (" + split[1] + "|" + split[2] + ")");
				return true;
			} else {
				System.err.println("Command wird ignoriert, da Fehlerhaft: " + s);
			}
		}
		return false;
	}

}
