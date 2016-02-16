package Commands;

import Bot.Bot;

public class Command_Print implements Command {

	@Override
	public String getName() {
		return "print";
	}

	@Override
	public boolean execute(String s) {
		if(s.startsWith("print")) {
			//real command:
			String split[] = s.split(" ", 2);
			if(Bot.getBot().getNumM().isNum(split[1])) {
				System.out.println("OUTPUT: " + Bot.getBot().getNumM().format(split[1]));
				return true;
			} else if(split[1].startsWith("\"") &  split[1].endsWith("\"")) {
				System.out.println("OUTPUT: " + split[1].substring(1, split[1].length()-1));
			} else {
				System.err.println("Command wird ignoriert, da Fehlerhaft: " + s);
			}
			
		}
		return false;
	}

}
