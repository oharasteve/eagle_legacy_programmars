// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2011

package com.eagle.programmar.CMD.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.CMD.CMD_Expression;
import com.eagle.programmar.CMD.CMD_Label;
import com.eagle.programmar.CMD.CMD_Statement;
import com.eagle.programmar.CMD.CMD_Variable;
import com.eagle.programmar.CMD.Terminals.CMD_EndOfLine;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_Number;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class CMD_If_Statement extends TokenSequence implements EagleRunnableWithResult, AbstractStatement
{
	public @S(10) @DOC("if.mspx") CMD_Keyword IF = new CMD_Keyword("if");
	public @S(20) @OPT CMD_Keyword NOT = new CMD_Keyword("not");
	public @S(30) CMD_IfWhat what;
	public @S(40) @OPT CMD_Punctuation at = new CMD_Punctuation('@');
	public @S(50) CMD_Statement stmt;
	public @S(60) @OPT CMD_IfElseClause elseClause;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class CMD_IfElseClause extends TokenSequence
	{
		public @S(10) CMD_Keyword ELSE = new CMD_Keyword("else");
		public @S(20) @OPT CMD_EndOfLine eoln;
		public @S(30) @OPT CMD_Punctuation at = new CMD_Punctuation('@');
		public @S(40) CMD_Statement elseStatement;
	}
	
	public static class CMD_IfWhat extends TokenChooser
	{
		public @LAST CMD_Expression XXexpr;
		public @CHOICE CMD_IfDefined XXifDefined;
		public @CHOICE CMD_IfErrorLevel XXerrorLevel;
		public @CHOICE CMD_IfExist XXifExist;
	}

	public static class CMD_IfDefined extends TokenSequence
	{
		public @S(10) CMD_Keyword DEFINED = new CMD_Keyword("defined");
		public @S(20) CMD_Variable var;
	}

	public static class CMD_IfErrorLevel extends TokenSequence
	{
		public @S(10) CMD_Keyword ERRORLEVEL = new CMD_Keyword("errorlevel");
		public @S(20) CMD_Number level;
	}

	public static class CMD_IfExist extends TokenSequence
	{
		public @S(10) CMD_Keyword EXIST = new CMD_Keyword("exist");
		public @S(20) CMD_Expression file;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, this));
			if (elseClause != null && elseClause.isPresent())
			{
				_metrics.add(new IfCondMetrics(interpreter._metrics, elseClause));
			}
		}
		
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		boolean passTest = false;
		if (what.getWhich() instanceof CMD_IfErrorLevel)
		{
			CMD_IfErrorLevel errLevel = (CMD_IfErrorLevel) what.getWhich();
			CMD_Label func = (CMD_Label) interpreter.getCurrentFunction();
			int actual = func._exitStatus;
			int goal = Integer.parseInt(errLevel.level.getValue());
			passTest = actual >= goal;
		}
		else if (what.getWhich() instanceof CMD_IfDefined)
		{
			CMD_IfDefined defined = (CMD_IfDefined) what.getWhich();
			EagleValue val = interpreter.findSymbol(defined.var.id.getValue());
			passTest = val != null;
		}
		else if (what.getWhich() instanceof CMD_Expression)
		{
			CMD_Expression expr = (CMD_Expression) what.getWhich();
			passTest = interpreter.getBoolValue(expr);
		}
		else
		{
			throw new RuntimeException("Cannot handle 'if' condition: " + what.getWhich());
		}

		if (NOT.isPresent()) passTest = !passTest;
		_metrics.get(0).completedIf(passTest);
		if (passTest)
		{
			result = interpreter.tryToInterpret(stmt);
		}
		else if (elseClause != null && elseClause.isPresent())
		{
			_metrics.get(1).completedIf(true);
			result = interpreter.tryToInterpret(elseClause.elseStatement);
		}
		return result;
	}
}
