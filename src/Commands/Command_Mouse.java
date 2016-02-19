package Commands;

import java.awt.event.KeyEvent;

import Bot.Bot;

public class Command_Mouse implements Command {

	@Override
	public String getName() {
		return "click";
	}

	@Override
	public boolean execute(String s) {
		if(s.startsWith("click")) {
			int mask = 0;
			char c = s.charAt(s.length()-1);


			switch (c) {
			case 'l': mask = KeyEvent.BUTTON1_MASK;
			break;
			case 'm': mask = KeyEvent.BUTTON2_MASK;
			break;
			case 'r': mask = KeyEvent.BUTTON3_MASK;
			break;
			default: return false;
			}

			Bot.getBot().getRobot().mousePress(mask);
			try {
				Thread.sleep(5);
			} catch(Exception e) {}
			Bot.getBot().getRobot().mouseRelease(mask);
			return true;
		}
		return false;
	}
}