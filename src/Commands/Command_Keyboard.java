package Commands;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import Bot.Bot;

public class Command_Keyboard implements Command{

	@Override
	public String getName() {
		return "press";
	}

	@Override
	public int execute(String s) {
		
		String split[] = s.split(" ", 2);

		if(s.startsWith("press")) {
			if(split.length >= 2) {
				char c = split[1].charAt(0);

				KeyStroke key = KeyStroke.getKeyStroke(Character.toUpperCase(c), 0);
				
				if (Character.isUpperCase(c)) {
					Bot.getBot().getRobot().keyPress(KeyEvent.VK_SHIFT);
				}

				Bot.getBot().getRobot().keyPress(key.getKeyCode());
				Bot.getBot().getRobot().keyRelease(key.getKeyCode());

				if (Character.isUpperCase(c)) {
					Bot.getBot().getRobot().keyRelease(KeyEvent.VK_SHIFT);
				}

				return 1;
			} else {
				System.err.println("Command gets ignored because it contains an failure: " + s);
			}
		}

		return Integer.MIN_VALUE;
	}

	@Override
	public boolean canExecute(String cmd) {
		return cmd.startsWith("press");
	}
}
