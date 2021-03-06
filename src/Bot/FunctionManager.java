package Bot;

import java.util.HashMap;

import Commands.Command;

public class FunctionManager implements Command {

	private HashMap<String, String[]> func = new HashMap<String, String[]>();
	private int stack_depth = 0; 

	@Override
	public String getName() {
		return "function";
	}

	@Override
	public int execute(String s) {//function name { cmds }
		if(func.containsKey(s.toLowerCase())) {//registered function
			String[] function = func.get(s.toLowerCase());

			if(stack_depth < 2000) {
				CommandManager interpreter = new CommandManager();
				stack_depth ++;
				interpreter.stack_depth = stack_depth;
				interpreter.interpret(function);
				stack_depth --;

				return 1;//success
			} else {
				System.err.println("To prevent a stack overflow, the function dont get executed!");
				return Integer.MIN_VALUE;//error
			}
		}

		String[] split = s.split(" ", 3);
		if(split.length == 3) {
			if(split[0].equalsIgnoreCase("function") & !split[1].equalsIgnoreCase("") & split[2].equalsIgnoreCase("{")) {
				String name = split[1].trim().toLowerCase();
				CommandManager cmdm = Bot.getBot().getCmdManager();
				//name allowed?
				for(Command cmd : cmdm) {
					if(cmd.getName().startsWith(name)) {
						System.err.println("Function name (" + name + ") forbidden!");
						return Integer.MIN_VALUE;//cant execute
					}
				}

				Bot bot = Bot.getBot();

				int function_begin = cmdm.step+1;
				int function_end = -1;

				for(int i = function_begin; i < bot.code.length ; i++) {
					if(bot.code[i].equalsIgnoreCase("}")) {
						function_end = i;
						break;
					}
				}

				if(function_end != -1) {
					String[] function = new String[function_end - function_begin];

					for(int i = function_begin, j = 0; i < bot.code.length & i < function_end ; i++, j++) {
						function[j] = bot.code[i];
					}

					func.put(name, function);
					cmdm.step = function_end;

				} else {
					System.err.println("Function found, but closing '}'");
				}
			}
		}
		return Integer.MIN_VALUE;//nope
	}

	@Override
	public boolean canExecute(String cmd) {
		//is function
		if(func.containsKey(cmd.toLowerCase()))
			return true;
		//is definition of function
		String[] split = cmd.split(" ", 3);
		if(split.length == 3) {
			return (split[0].equalsIgnoreCase("function") & !split[1].equalsIgnoreCase("") & split[2].equalsIgnoreCase("{"));
		}
		return false;
	}
}