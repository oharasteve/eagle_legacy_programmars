// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 26, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.programmar.CMD.CMD_Expression;
import com.eagle.programmar.CMD.CMD_Format;
import com.eagle.programmar.CMD.CMD_Variable;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_PunctuationChoice;
import com.eagle.programmar.CMD.Terminals.CMD_RestOfLine;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CMD_Set_Statement extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) @DOC("set.mspx") CMD_Keyword SET = new CMD_Keyword("set");
	public @S(20) CMD_Set_What setWhat;

	public static class CMD_Set_Regular extends TokenSequence
	{
		public @S(10) CMD_Variable var;
		public @S(20) PunctuationEquals equals;
		public @S(30) CMD_RestOfLine value;
	}

	public static class CMD_Set_Assigment extends TokenSequence
	{
		public @S(10) PunctuationSlash slash;
		public @S(20) CMD_Keyword A = new CMD_Keyword("a");
		public @S(30) CMD_Variable var;
		public @S(40) CMD_PunctuationChoice operator = new CMD_PunctuationChoice("=", "+=");
		public @S(50) CMD_Expression expr;
	}

	public static class CMD_Set_Prompt extends TokenSequence
	{
		public @S(10) PunctuationSlash slash;
		public @S(20) CMD_Keyword P = new CMD_Keyword("p");
		public @S(30) CMD_Variable var;
		public @S(40) PunctuationEquals equals;
		public @S(50) CMD_RestOfLine value;
	}

	public static class CMD_Set_What extends TokenChooser
	{
		public @CHOICE CMD_Set_Regular XXsetRegular;
		public @CHOICE CMD_Set_Assigment XXsetAssignment;
		public @CHOICE CMD_Set_Prompt XXsetPrompt;
	}
	
	private static String getName(EagleInterpreter interpreter, CMD_Variable var)
	{
		String name = var.id.getValue();
		if (var.subscript != null && var.subscript.isPresent())
		{
			int sub = interpreter.getIntValue(var.subscript.expr);
			name += "[" + sub + "]";
		}
		return name;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken which = setWhat.getWhich();
		if (which instanceof CMD_Set_Regular)
		{
			CMD_Set_Regular cmd = (CMD_Set_Regular) which;
			String name = getName(interpreter, cmd.var);
			String formatted = CMD_Format.format(interpreter, cmd.value.getValue());
			interpreter.setSymbol(cmd.var, name, new EagleString(formatted));
		}
		else if (which instanceof CMD_Set_Assigment)
		{
			CMD_Set_Assigment setA = (CMD_Set_Assigment) which;
			String name = getName(interpreter, setA.var);
			switch (setA.operator.getValue())
			{
			case "=":
				EagleValue newVal = interpreter.getEagleValue(setA.expr);
				interpreter.setSymbol(setA.var, name, newVal);
				break;
			case "+=":
				int intVal = interpreter.getIntValue(setA.expr);
				EagleValue oldVar = interpreter.findSymbol(setA.var.id.getValue());
				EagleInteger newValue = new EagleInteger(intVal + oldVar.forceIntegerValue());
				interpreter.setSymbol(setA.var, name, newValue);
				break;
			default:
				throw new RuntimeException("Unable to handle operator " + setA.operator.getValue());
			}
		}
		else
		{
			throw new RuntimeException("Unexpected assignment variable: " + which);
		}
	}
}
