package Commands;

public interface Command {
	
	public String getName();
	
	public boolean execute(String s);
}