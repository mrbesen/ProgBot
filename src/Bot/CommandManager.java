package Bot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import Commands.Command;
import Commands.Command_Keyboard;
import Commands.Command_Loop;
import Commands.Command_Mouse;
import Commands.Command_Move;
import Commands.Command_Position;
import Commands.Command_Print;
import Commands.Command_Wait;

public class CommandManager implements Collection<Command>{

	public static List<Command> cmds = new ArrayList<Command>();

	public boolean run = true;
	public int step = 0;
	public int stack_depth = 0;

	public static void init() {
		cmds.add(new Command_Move());
		cmds.add(new Command_Print());
		cmds.add(new Command_Wait());
		cmds.add(new Command_Keyboard());
		cmds.add(new Command_Mouse());
		cmds.add(new Command_Position());
		cmds.add(new Command_Loop());


		cmds.add(Bot.getBot().getNumM());
		cmds.add(new FunctionManager());
	}

	public void interpret(String[] s) {
		while(run) {
			if(step == s.length) {
				run = false;
				break;//stop on end
			}
			interpretSingle(s[step].trim());
			step ++;
		}
	}

	public int interpretSingle(String command) {
		if(!command.startsWith("#") & !command.startsWith("//"))  {//comment
			for(Command cmd : cmds) {
				if(cmd.canExecute(command)) {
					int ret = cmd.execute(command); 
					if(ret  != Integer.MIN_VALUE) {//sucess
						return ret;
					}
				}
			}
		}
		return Integer.MIN_VALUE;
	}

	public boolean isCmd(String line) {
		line = line.trim().toLowerCase();
		for(Command cmd : cmds) {
			if(cmd.canExecute(line))
				return true;
		}
		return true;
	}

	//=================================interface

	@Override
	public boolean add(Command e) {
		return cmds.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends Command> c) {
		return cmds.addAll(c);
	}

	@Override
	public void clear() {
		cmds.clear();
	}

	@Override
	public boolean contains(Object o) {
		return cmds.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return containsAll(c);
	}

	@Override public boolean isEmpty() {
		return cmds.isEmpty();
	}

	@Override
	public Iterator<Command> iterator() {
		return cmds.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return cmds.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return cmds.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return retainAll(c);
	}

	@Override public int size() {
		return cmds.size();
	}

	@Override
	public Object[] toArray() {
		return cmds.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return cmds.toArray(a);
	}
}