package Commands;

import java.util.Random;

import Bot.Bot;

public class Command_Random implements Command {

	Random rand;
	
	public Random getrand() {
		if(rand == null) 
			rand = new Random();
		return rand;
	}
	
	@Override
	public String getName() {
		return "random";
	}

	@Override
	public int execute(String s) {
		if(s.startsWith("random")) {
			int max = Integer.MAX_VALUE -1;
			String[] args = s.split(" ",2);
			if(args.length == 2) {
				if(Bot.getBot().getNumM().isNum(args[1])) {
					max = Bot.getBot().getNumM().getNum(args[1]);
				}
			}
			return getrand().nextInt(max);
		}
		return Integer.MIN_VALUE;
	}

	@Override
	public boolean canExecute(String cmd) {
		return (cmd.startsWith("random"));
	}

}
