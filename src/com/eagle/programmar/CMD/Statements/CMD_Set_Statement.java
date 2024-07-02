// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 26, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.CMD.CMD_Expression;
import com.eagle.programmar.CMD.Symbols.CMD_Variable_Definition;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
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
		public @S(10) CMD_Variable_Definition var;
		public @S(20) PunctuationEquals equals;
		public @S(30) CMD_RestOfLine value;
	}

	public static class CMD_Set_Assigment extends TokenSequence
	{
		public @S(10) PunctuationSlash slash;
		public @S(20) CMD_Keyword A = new CMD_Keyword("a");
		public @S(30) CMD_Variable_Definition var;
		public @S(40) PunctuationEquals equals;
		public @S(50) CMD_Expression expr;
	}

	public static class CMD_Set_Prompt extends TokenSequence
	{
		public @S(10) PunctuationSlash slash;
		public @S(20) CMD_Keyword P = new CMD_Keyword("p");
		public @S(30) CMD_Variable_Definition var;
		public @S(40) PunctuationEquals equals;
		public @S(50) CMD_RestOfLine value;
	}

	public static class CMD_Set_What extends TokenChooser
	{
		public @CHOICE CMD_Set_Regular setRegular;
		public @CHOICE CMD_Set_Assigment setAssignment;
		public @CHOICE CMD_Set_Prompt setPrompt;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken which = setWhat.getWhich();
		if (which instanceof CMD_Set_Regular)
		{
			CMD_Set_Regular cmd = (CMD_Set_Regular) which;
			EagleValue val = interpreter.getEagleValue(cmd.value);
			interpreter._symbolTable.setSymbol(cmd.var.getFileName(), cmd.var.getStartLine(), cmd.var.getStartChar(),
					cmd.var.getValue(), val);
		}
		else if (which instanceof CMD_Set_Assigment)
		{
			CMD_Set_Assigment cmd = (CMD_Set_Assigment) which;
			int x = interpreter.getIntValue(cmd.expr);
			EagleInteger val = new EagleInteger(x);
			interpreter._symbolTable.setSymbol(cmd.var.getFileName(), cmd.var.getStartLine(), cmd.var.getStartChar(),
					cmd.var.getValue(), val);
		}
		else
		{
			throw new RuntimeException("Unexpected assignment variable: " + which);
		}
	}
}
