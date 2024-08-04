// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleString;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.CMD.CMD_Expression;
import com.eagle.programmar.CMD.CMD_Statement;
import com.eagle.programmar.CMD.Statements.CMD_For_Statement.CMD_For_Type.CMD_For_L;
import com.eagle.programmar.CMD.Statements.CMD_For_Statement.CMD_For_Type.CMD_Simple_For;
import com.eagle.programmar.CMD.Terminals.CMD_Filename;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_Literal;
import com.eagle.programmar.CMD.Terminals.CMD_PctPctVariable;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.programmar.CMD.Terminals.CMD_RawArgument;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CMD_For_Statement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("for.mspx") CMD_Keyword FOR = new CMD_Keyword("for");
	public @S(20) CMD_For_Type whichFor;
	public @S(30) CMD_Keyword DO = new CMD_Keyword("do");
	public @S(40) @OPT CMD_Punctuation at = new CMD_Punctuation('@');
	public @S(50) CMD_Statement stmt;

	public static class CMD_For_More_Args extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) CMD_RawArgument arg;
	}

	public static class CMD_For_Type extends TokenChooser
	{
		public @CHOICE static class CMD_Simple_For extends TokenSequence
		{
			public @S(10) @OPT CMD_Simple_For_Option option;
			public @S(20) CMD_PctPctVariable var;
			public @S(30) CMD_Keyword IN = new CMD_Keyword("in");
			public @S(40) PunctuationLeftParen leftParen;
			public @S(50) CMD_RawArgument arg;
			public @S(60) @OPT TokenList<CMD_For_More_Args> moreArgs;
			public @S(70) PunctuationRightParen rightParen;
			
			public static class CMD_Simple_For_Option extends TokenChooser
			{
				public @CHOICE static class CMD_For_D extends TokenSequence
				{
					public @S(10) PunctuationSlash slash;
					public @S(20) CMD_Keyword D = new CMD_Keyword("d");
				}

				public @CHOICE static class CMD_For_F extends TokenSequence
				{
					public @S(10) PunctuationSlash slash;
					public @S(20) CMD_Keyword F = new CMD_Keyword("f");
					public @S(30) @OPT CMD_Literal options;
				}
	
				public @CHOICE static class CMD_For_R extends TokenSequence
				{
					public @S(10) PunctuationSlash slash;
					public @S(20) CMD_Keyword R = new CMD_Keyword("r");
					public @S(30) @OPT CMD_Filename fileName;
				}
			}
		}

		public @CHOICE static class CMD_For_L extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) CMD_Keyword L = new CMD_Keyword("l");
			public @S(30) CMD_PctPctVariable var;
			public @S(40) CMD_Keyword IN = new CMD_Keyword("in");
			public @S(50) PunctuationLeftParen leftParen;
			public @S(60) CMD_Expression start;
			public @S(70) PunctuationComma comma1;
			public @S(80) CMD_Expression incr;
			public @S(90) PunctuationComma comma2;
			public @S(100) CMD_Expression stop;
			public @S(110) PunctuationRightParen rightParen;
		}
	}

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, getFileName(), getStartLine(), getStartChar());
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

		if (whichFor.getWhich() instanceof CMD_Simple_For)
		{
			CMD_Simple_For simpleFor = (CMD_Simple_For) whichFor.getWhich();
			if (simpleFor.option != null && simpleFor.option.isPresent())
			{
				throw new RuntimeException("FOR statement cannot have options");
			}
	
			int numArgs = 1;
			if (simpleFor.moreArgs != null && simpleFor.moreArgs.isPresent())
			{
				numArgs += simpleFor.moreArgs.size();
			}
			
			for (int i = 0; i < numArgs; i++)
			{
				CMD_RawArgument nextArg;
				if (i == 0) 
				{
					nextArg = simpleFor.arg;
				}
				else
				{
					nextArg = simpleFor.moreArgs._elements.get(i-1).arg;
				}
	
				metric.iterate();
				String val = interpreter.getStrValue(nextArg);
				interpreter._symbolTable.setSymbol(simpleFor.var.getFileName(), simpleFor.var.getStartLine(),
						simpleFor.var.getStartChar(), simpleFor.var.getValue(), new EagleString(val));
	
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break; 
			}
		}
		else if (whichFor.getWhich() instanceof CMD_For_L)
		{
			CMD_For_L forL = (CMD_For_L) whichFor.getWhich();
			int i = interpreter.getIntValue(forL.start);
			int incr = interpreter.getIntValue(forL.incr);
			int stop = interpreter.getIntValue(forL.stop);
			while (true)
			{
				if (incr < 0 && i <= stop) break;
				if (incr > 0 && i >= stop) break;
				
				metric.iterate();
				interpreter._symbolTable.setSymbol(forL.var.getFileName(), forL.var.getStartLine(),
						forL.var.getStartChar(), forL.var.getValue(), new EagleInteger(i));
	
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;				

				i += incr;
			}
		}
		else throw new RuntimeException("Unable to handle " + whichFor);
		
		_metrics.competedLoop(metric);
		return result;
	}
}

