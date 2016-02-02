package Commands;

import Utils.Y_NumberTool;

public class Command_Wait implements Command {

	@Override
	public String getName() {
		return "wait";
	}

	@Override
	public boolean execute(String s) {
		if(s.startsWith("wait")) {
			String split[] = s.split(" ", 2);
			if(Y_NumberTool.is_nummber(split[1])) {
				try {
					System.out.print("Wait...");
					Thread.sleep(100 * (Integer.parseInt(split[1])));
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
