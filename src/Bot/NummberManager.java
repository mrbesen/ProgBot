package Bot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Commands.Command;
import Utils.Y_NumberTool;

public class NummberManager implements Command{

	private HashMap<String, Integer> vars = new HashMap<String, Integer>();
	private List<Calculation> calcs = new ArrayList<Calculation>();

	//======================================================

	public NummberManager() {
		calcs.add(new Calculation("**", "\\*\\*") {
			@Override
			public int calc_(int a, int b) {
				return (int) (Math.pow(a, b));
			}
		});
		calcs.add(new Calculation("*", "\\*") {
			@Override
			public int calc_(int a, int b) {
				return (a * b);
			}
		});

		calcs.add(new Calculation("/","\\/") {
			@Override
			public int calc_(int a, int b) {
				return (a / b);
			}
		});

		calcs.add(new Calculation("-", "-") {
			@Override
			public int calc_(int a, int b) {
				return (a - b);
			}
		});

		calcs.add(new Calculation("+", "\\+") {
			@Override
			public int calc_(int a, int b) {
				return (a + b);
			}
		});

		calcs.add(new Calculation("%", "%") {
			@Override
			public int calc_(int a, int b) {
				return (a % b);
			}
		});
	}

	//=========================================================get
	public int getNum(String num) {//num -> potenzielle nummer
		num = num.trim();//.replace(" ", "");//remove useles space (nichtmehr, falls cmd)

		if(Y_NumberTool.is_nummber(num.replace(" ", ""))) //is num?
			return (Integer.parseInt(num));
		
		if(isCalc(num)) 
			return getCalc(num);
		
		if(isCmd(num))
			return getCmd(num);

		if(isBracket(num)) {
			return getBracket(num);
		}

		if(isVar(num)) //is var?
			return (getVar(num.substring(1)));

		if(num.equalsIgnoreCase("infinite")) 
			return Integer.MAX_VALUE;
		
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
		for(Calculation ca : calcs) {
			if(ca.canCalc(str))
				return ca.calc(str);
		}

		return 0;

		//		if(str.contains("**")) {//pot
		//			String[] split = str.split("\\*\\*",2);
		//			return (int) (Math.pow(getNum(split[0]), getNum(split[1])));
		//		}
		//		if(str.contains("%")) {//mod
		//			String[] split = str.split("%",2);
		//			return (getNum(split[0]) % getNum(split[1]));
		//		}
		//		if(str.contains("*")) {
		//			String[] split = str.split("\\*",2);
		//			return (getNum(split[0]) * getNum(split[1]));
		//		}
		//		if(str.contains("/")) {
		//			String[] split = str.split("/",2);
		//			return (getNum(split[0]) / getNum(split[1]));
		//		}
		//		if(str.contains("+")) {//simple math
		//			String[] split = str.split("\\+",2);
		//			return (getNum(split[0]) + getNum(split[1]));
		//		} 
		//		if(str.contains("-")) {
		//			String[] split = str.split("-",2);
		//			return (getNum(split[0]) - getNum(split[1]));
		//		}
		//		return 0;//error
	}

	public int getBracket(String var) {
		var = var.trim().substring(1, var.length()-1);
		return (getNum(var));
	}

	public int getCmd(String cmd) {
		int ret = Bot.getBot().getCmdManager().interpretSingle(cmd);
		if (ret != Integer.MIN_VALUE)
			return ret;
		return 0;
	}

	//================================================format
	public String format(String num) {

		if(isCalc(num))
			return ("" + getCalc(num));

		if(isVar(num)) //var
			return (num +" = " + getVar(num.substring(1)));

		if(isCmd(num)) 
			return "" + getCmd(num);

		return num;//normal nummber
	}
	//========================================test
	public boolean isNum(String num) {
		num = num.trim();//remove useles space

		return (Y_NumberTool.is_nummber(num) | num.equalsIgnoreCase("infinite") | isVar(num) | isCalc(num) | isBracket(num) | isCmd(num));
	}

	public boolean isVar(String var) {//is variable
		return var.startsWith("$");
	}

	public boolean isCalc(String var) {//is calc
		var = var.toLowerCase().trim().replace(" ", "");
		for(Calculation ca : calcs) {
			if(var.contains(ca.sign))
				return true;
		}
//		return false;
		return (var.contains("+") | var.contains("-") | var.contains("*") | var.contains("/") | var.contains("%") );
	}

	public boolean isBracket(String var) {
		//		System.out.println(var + "  " + (var.trim().startsWith("(") & var.trim().endsWith(")")));
		return (var.trim().startsWith("(") & var.trim().endsWith(")"));
	}

	public boolean isCmd(String cmd) {
		return (Bot.getBot().getCmdManager().isCmd(cmd) & !cmd.startsWith("$"));
	}

	//=================================command stuff
	@Override
	public String getName() {
		return "var";
	}

	@Override
	public int execute(String s) {
		if(s.startsWith("var")) {//TODO define var without var ($x = 1)

			s = s.substring(3);//.replace(" ", "");//remove var & spaces
			String[] split = s.split("=",2);

			//declare
			//left side
			String name = split[0].trim();
			if(name.startsWith("$")) {
				name = name.substring(1);
			}
			if(isNum(split[1])) {//right side / content
				vars.put(name, getNum(split[1]));
				return 1;
			}
		}
		return Integer.MIN_VALUE;
	}

	@Override
	public boolean canExecute(String cmd) {
		return cmd.startsWith("var");
	}

	//======================================================

	private abstract class Calculation {
		private String sign;
		private String pattern;

		public Calculation(String s, String patt) {
			sign = s;
			pattern = patt;
		}

		public boolean canCalc(String num) {
			if(num.contains(sign)) {
				String split[] = num.split(pattern,2);
				return (isNum(split[0]) & isNum(split[1]) );
			} 
			return false;
		}


		public int calc(String num) {
			String[] split = num.trim().split(pattern,2);
			return (calc_(getNum(split[0]), getNum(split[1])));
		}

		public abstract int calc_(int a, int b);
	}

}