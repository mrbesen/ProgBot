package Bot;

import java.util.HashMap;

import Commands.Command;
import Utils.Y_NumberTool;

public class NummberManager implements Command{

	HashMap<String, Integer> vars = new HashMap<String, Integer>();

	//=========================================================get
	public int getNum(String num) {//num -> potenzielle nummer
		num = num.trim().replace(" ", "");//remove useles space

		if(Y_NumberTool.is_nummber(num)) {//is num?
			return (Integer.parseInt(num));
		}

		if(isVar(num)) {//is var?
			return (getVar(num.substring(1)));
		}
		return Integer.MAX_VALUE;
	}

	public int getVar(String name) {
		if(name.startsWith("$")) 
			name = name.substring(1);
		if(name.equals("TIME")) {
			return (int) (System.currentTimeMillis()/1000);
		}
		if(vars.containsKey(name)) {//return registered var
			return vars.get(name);
		}
		System.err.println("Variable (" + name +") undefined!");
		return Integer.MAX_VALUE;
	}

	//================================================format

	public String format(String num) {
		if(isVar(num)) {
			return (num + " = " + getVar(num.substring(1)));
		}

		return num;
	}
	//========================================test
	public boolean isNum(String num) {
		num = num.trim();//remove useles space

		if(Y_NumberTool.is_nummber(num) | isVar(num) ) {
			return true;
		}
		return false;
	}

	public boolean isVar(String var) {
		return var.startsWith("$");
	}
	//=================================command stuff
	@Override
	public String getName() {
		return "var";
	}

	@Override
	public boolean execute(String s) {
		if(s.startsWith("var")) {//var $v = d
			s = s.substring(3).replace(" ", "");//remove var & spaces


			String[] split = s.split("=",2);

			//left side
			if(split[0].startsWith("$")) {
				//declare
				if(isNum(split[1])) {//2. teil ist nummer / variable
					vars.put(split[0].substring(1), getNum(split[1]));
				}
			} else {
				System.err.println("Variable invalid!");
				return false;
			}

			return true;
		}
		return false;
	}

}
