package Commands;

public interface Command {
	
	public String getName();
	
	public int execute(String s);//Integer.min_Value -> error / 1 -> success / or result 

	public boolean canExecute(String cmd);
}