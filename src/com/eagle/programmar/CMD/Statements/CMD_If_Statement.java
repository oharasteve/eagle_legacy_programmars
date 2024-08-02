// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2011

package com.eagle.programmar.CMD.Statements;

import java.util.ArrayList;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.CMD.CMD_Argument;
import com.eagle.programmar.CMD.CMD_Statement;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_KeywordChoice;
import com.eagle.programmar.CMD.Terminals.CMD_Literal;
import com.eagle.programmar.CMD.Terminals.CMD_Number;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class CMD_If_Statement extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) @DOC("if.mspx") CMD_Keyword IF = new CMD_Keyword("if");
	public @S(20) @OPT CMD_Keyword NOT = new CMD_Keyword("not");
	public @S(30) CMD_IfWhat what;
	public @S(40) @OPT CMD_Punctuation at = new CMD_Punctuation('@');
	public @S(50) CMD_Statement stmt;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class CMD_IfEqual extends TokenSequence
	{
		public @S(10) @OPT PunctuationHyphen minus1;
		public @S(20) CMD_Argument expr1;
		public @S(30) CMD_IfOperator operator;
		public @S(40) @OPT PunctuationHyphen minus2;
		public @S(50) CMD_Argument expr2;

		public static class CMD_IfOperator extends TokenChooser
		{
			public @CHOICE CMD_KeywordChoice XXoperator = new CMD_KeywordChoice("equ", "geq", "gtr", "leq", "lss", "neq");
			public @CHOICE CMD_Punctuation XXequals = new CMD_Punctuation("==");
		}
	}

	public static class CMD_IfWhat extends TokenChooser
	{
		public @LAST CMD_Literal XXliteral;
		public @CHOICE CMD_IfEqual XXifEqual;

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

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, getFileName(), getStartLine(), getStartChar()));
		}
		
		CMD_IfEqual ifEqual = (CMD_IfEqual) what.getWhich();
		boolean passTest = false;	// Initial value is not used
		boolean doIntegerCompare = true;
		EagleValue leftVal = interpreter.getEagleValue(ifEqual.expr1.arg);
		EagleValue rightVal = interpreter.getEagleValue(ifEqual.expr2.arg);
		if (leftVal.isString() && rightVal.isString())
		{
			String leftStr = leftVal.forceStringValue();
			String rightStr = rightVal.forceStringValue();
			switch (ifEqual.operator.getWhich().toString())
			{
			case "equ", "==":
				passTest = leftStr.equalsIgnoreCase(rightStr);
				doIntegerCompare = false;
				break;
			case "neq":
				passTest = leftStr != rightStr;
				doIntegerCompare = false;
				break;
			}
		}

		if (doIntegerCompare)
		{
			int leftInt = leftVal.forceIntegerValue();
			if (ifEqual.minus1.isPresent()) leftInt = -leftInt;
			int rightInt = rightVal.forceIntegerValue();
			if (ifEqual.minus2.isPresent()) rightInt = -rightInt;
			switch (ifEqual.operator.getWhich().toString())
			{
			case "equ", "==":
				passTest = leftInt == rightInt;
				break;
			case "geq":
				passTest = leftInt >= rightInt;
				break;
			case "gtr":
				passTest = leftInt > rightInt;
				break;
			case "leq":
				passTest = leftInt <= rightInt;
				break;
			case "lss":
				passTest = leftInt < rightInt;
				break;
			case "neq":
				passTest = leftInt != rightInt;
				break;
			default:
				throw new RuntimeException("Cannot handle numeric relational operator: " + ifEqual.operator.getWhich());
			}
		}

		if (NOT.isPresent()) passTest = !passTest;
		_metrics.get(0).completedIf(passTest);
		if (passTest)
		{
			interpreter.tryToInterpret(stmt);
		}
	}
}
