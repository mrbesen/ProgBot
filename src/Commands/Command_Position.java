package Commands;

import java.awt.MouseInfo;

import Bot.Bot;

public class Command_Position implements Command{

	private int offsetx, offsety;

	public Command_Position() {
		//kalibrating...(get offset)
		int x = MouseInfo.getPointerInfo().getLocation().x;
		int y = MouseInfo.getPointerInfo().getLocation().y;
		Bot.getBot().getRobot().mouseMove(0, 0);
		offsetx = MouseInfo.getPointerInfo().getLocation().x;
		offsety = MouseInfo.getPointerInfo().getLocation().y;
		Bot.getBot().getRobot().mouseMove((x - offsetx), (y - offsety));
	}


	@Override
	public String getName() {
		return "pos";
	}

	@Override
	public int execute(String s) {
		if(s.startsWith("pos")) {
			System.out.println("Current Mouse Position: (" + mouseX() + "|" + mouseY() + ")");
			String[] split = s.split(" ",2);

			if(split.length > 1) {
				if(split[1].equalsIgnoreCase("x") | Bot.getBot().getNumM().getNum(split[1]) == 1) {
					return mouseX();
				}
				if (split[1].equalsIgnoreCase("y") | Bot.getBot().getNumM().getNum(split[1]) == 2) {
					return mouseY();
				}
			}
			return 1;//without expecting return
		}
		return Integer.MIN_VALUE;
	}

	private int mouseX() {
		return (MouseInfo.getPointerInfo().getLocation().x - offsetx);
	}
	private int mouseY() {
		return (MouseInfo.getPointerInfo().getLocation().y - offsety);
	}

	@Override
	public boolean canExecute(String cmd) {
		return (cmd.startsWith("pos"));
	}
}