// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.CMD.CMD_Argument;
import com.eagle.programmar.CMD.CMD_Command.CMD_Statement;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_KeywordChoice;
import com.eagle.programmar.CMD.Terminals.CMD_Literal;
import com.eagle.programmar.CMD.Terminals.CMD_Number;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class CMD_If_Statement extends TokenSequence implements EagleRunnable
{
	public @S(10) @DOC("if.mspx") CMD_Keyword IF = new CMD_Keyword("if");
	public @S(20) @OPT CMD_Keyword NOT = new CMD_Keyword("not");
	public @S(30) CMD_IfWhat what;
	public @S(40) @OPT CMD_Punctuation at = new CMD_Punctuation('@');
	public @S(50) CMD_Statement stmt;

	public static class CMD_IfEqual extends TokenSequence
	{
		public @S(10) @OPT PunctuationHyphen minus1;
		public @S(20) CMD_Argument expr1;
		public @S(30) CMD_IfOperator operator;
		public @S(40) @OPT PunctuationHyphen minus2;
		public @S(50) CMD_Argument expr2;

		public static class CMD_IfOperator extends TokenChooser
		{
			public @CHOICE CMD_KeywordChoice operator = new CMD_KeywordChoice("equ", "geq", "gtr", "leq", "lss", "neq");
			public @CHOICE CMD_Punctuation equals = new CMD_Punctuation("==");
		}
	}

	public static class CMD_IfWhat extends TokenChooser
	{
		public @LAST CMD_Literal literal;
		public @CHOICE CMD_IfEqual ifEqual;

		public @CHOICE static class CMD_IfDefined extends TokenSequence
		{
			public @S(10) CMD_Keyword DEFINED = new CMD_Keyword("defined");
			public @S(20) CMD_Argument var;
		}

		public @CHOICE static class CMD_IfErrorLevel extends TokenSequence
		{
			public @S(10) CMD_Keyword ERRORLEVEL = new CMD_Keyword("errorlevel");
			public @S(20) CMD_Number level;
		}

		public @CHOICE static class CMD_IfExist extends TokenSequence
		{
			public @S(10) CMD_Keyword EXIST = new CMD_Keyword("exist");
			public @S(20) CMD_Argument file;
		}

		public @LAST static class CMD_IfCondition extends TokenSequence
		{
			public @S(10) CMD_Argument condition;
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (!(what.getWhich() instanceof CMD_IfEqual))
		{
			throw new RuntimeException("Cannot handle 'if' condition: " + what.getWhich());
		}

		CMD_IfEqual ifEqual = (CMD_IfEqual) what.getWhich();
		int left = interpreter.getIntValue(ifEqual.expr1.arg);
		if (ifEqual.minus1.isPresent()) left = -left;
		int right = interpreter.getIntValue(ifEqual.expr2.arg);
		if (ifEqual.minus2.isPresent()) right = -right;
		boolean passTest;
		switch (ifEqual.operator.operator.getValue())
		{
		case "equ":
			passTest = left == right;
			break;
		case "geq":
			passTest = left >= right;
			break;
		case "gtr":
			passTest = left > right;
			break;
		case "leq":
			passTest = left <= right;
			break;
		case "lss":
			passTest = left < right;
			break;
		case "neq":
			passTest = left != right;
			break;
		default:
			throw new RuntimeException("Cannot handle relational operator: " + ifEqual.operator.operator);
		}

		if (NOT.isPresent()) passTest = ! passTest;
		if (passTest)
		{
			interpreter.tryToInterpret(stmt.getWhich());
		}
	}
}
