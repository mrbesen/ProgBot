package Bot;

import java.util.HashMap;

import Commands.Command;
import Utils.Y_NumberTool;

public class NummberManager implements Command{

	HashMap<String, Integer> vars = new HashMap<String, Integer>();

	//=========================================================get
	public int getNum(String num) {//num -> potenzielle nummer
		num = num.trim().replace(" ", "");//remove useles space

		if(isCalc(num)) {
			return getCalc(num);
		}

		if(isVar(num)) {//is var?
			return (getVar(num.substring(1)));
		}

		if(Y_NumberTool.is_nummber(num)) {//is num?
			return (Integer.parseInt(num));
		}

		return 0;//error
	}

	public int getVar(String name) {
		if(name.startsWith("$"))//remove init part
			name = name.substring(1);
		if(name.equals("TIME")) {
			return (int) (System.currentTimeMillis()/1000);
		}
		if(vars.containsKey(name)) {//return registered var
			return vars.get(name);
		}
		return 0;//error
	}

	public int getCalc(String str) {
		if(str.contains("**")) {//pot
			String[] split = str.split("\\*\\*",2);
			return (int) (Math.pow(getNum(split[0]), getNum(split[1])));
		}
		if(str.contains("%")) {//mod
			String[] split = str.split("%",2);
			return getNum(split[0]) % getNum(split[1]);
		}
		if(str.contains("*")) {
			String[] split = str.split("\\*",2);
			return getNum(split[0]) * getNum(split[1]);
		}
		if(str.contains("/")) {
			String[] split = str.split("/",2);
			return getNum(split[0]) / getNum(split[1]);
		}
		if(str.contains("+")) {//simple math
			String[] split = str.split("\\+",2);
			return getNum(split[0]) + getNum(split[1]);
		} 
		if(str.contains("-")) {
			String[] split = str.split("-",2);
			return getNum(split[0]) - getNum(split[1]);
		}
		return 0;//error
	}

	//================================================format

	public String format(String num) {
		if(isCalc(num)) {
			return ("" + getCalc(num));
		}		

		if(isVar(num)) {//var
			return (num + " = " + getVar(num.substring(1)));
		}
		return num;//normal nummber
	}
	//========================================test
	public boolean isNum(String num) {
		num = num.trim();//remove useles space

		if(Y_NumberTool.is_nummber(num) | isVar(num) | isCalc(num)) {
			return true;
		}
		return false;
	}

	public boolean isVar(String var) {//is variable
		return var.startsWith("$");
	}

	public boolean isCalc(String var) {//is calc
		return (var.contains("+") | var.contains("-") | var.contains("*") | var.contains("/") | var.contains("%") );
	}
	//=================================command stuff
	@Override
	public String getName() {
		return "var";
	}

	@Override
	public boolean execute(String s) {
		if(s.startsWith("var") | s.startsWith("$")) {//var $v = d

			s = s.substring(3).replace(" ", "");//remove var & spaces
			String[] split = s.split("=",2);

			//declare
			//left side
			String name = split[0];
			if(name.startsWith("$")) {
				name = name.substring(1);
			}
			if(isNum(split[1])) {//right side / content
				vars.put(name, getNum(split[1]));
			}

			return true;
		}
		return false;
	}
}