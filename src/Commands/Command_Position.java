package Commands;

import java.awt.MouseInfo;

import Bot.Bot;

public class Command_Position implements Command{
	
	private int offsetx, offsety;
	
	public Command_Position() {
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
	public boolean execute(String s) {
		if(s.startsWith("pos")) {
			System.out.println("Current Mouse Position: (" + mouseX() + "|" + mouseY() + ")");
		}
		return false;
	}
	
	private int mouseX() {
		return (MouseInfo.getPointerInfo().getLocation().x - offsetx);
	}
	private int mouseY() {
		return (MouseInfo.getPointerInfo().getLocation().y - offsety);
	}
}