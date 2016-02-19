package Commands;

public class Command_Wait implements Command {

	@Override
	public String getName() {
		return "wait";
	}

	@Override
	public boolean execute(String s) {
		if(s.startsWith("wait")) {
			String split[] = s.split(" ", 2);
			if(Bot.Bot.getBot().getNumM().isNum(split[1])) {
				try {
					System.out.print("Wait...");
					Thread.sleep(100 * (Bot.Bot.getBot().getNumM().getNum(split[1])));
					System.out.println("Done");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return true;
			} else {
				System.err.println("Command wird ignoriert, da Fehlerhaft: " + s);
			}
		}
		return false;
	}

}