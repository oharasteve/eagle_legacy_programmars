// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleString;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.CMD.CMD_BasicExpression;
import com.eagle.programmar.CMD.CMD_Expression;
import com.eagle.programmar.CMD.CMD_Statement;
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

	private @SKIP ForLoopMetrics _metrics = null;

	public static class CMD_For_More_Args extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) CMD_RawArgument arg;
	}

	public static class CMD_For_Type extends TokenChooser
	{
		public @CHOICE CMD_Simple_For XXsimpleFor;
		public @CHOICE CMD_For_L XXforL;
	}

	public static class CMD_Simple_For extends TokenSequence
	{
		public @S(10) CMD_Simple_For_Type type;
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) CMD_RawArgument arg;
		public @S(40) @OPT TokenList<CMD_For_More_Args> moreArgs;
		public @S(50) PunctuationRightParen rightParen;
	}

	public static class CMD_Simple_For_Type extends TokenChooser
	{
		public @CHOICE CMD_For_No_Options XXnoOptions;
		public @LAST CMD_For_D XXforD;
		public @CHOICE CMD_For_F XXforF;
		public @CHOICE CMD_For_R_Filename XXforR_filename;
		public @CHOICE CMD_For_R_no_Filename XXforR_noFilename;
	}

	public static class CMD_For_No_Options extends TokenSequence
	{
		public @S(10) CMD_PctPctVariable var;
		public @S(20) CMD_Keyword IN = new CMD_Keyword("in");
	}

	public static class CMD_For_D extends TokenSequence
	{
		public @S(10) CMD_Option_D optD;
		public @S(20) CMD_PctPctVariable var;
		public @S(30) CMD_Keyword IN = new CMD_Keyword("in");
	}

	public static class CMD_For_F extends TokenSequence
	{
		public @S(10) PunctuationSlash slash;
		public @S(20) CMD_Keyword F = new CMD_Keyword("f");
		public @S(30) @OPT CMD_Literal options;
		public @S(40) CMD_PctPctVariable var;
		public @S(50) CMD_Keyword IN = new CMD_Keyword("in");
	}

	public static class CMD_For_R_Filename extends TokenSequence
	{
		public @S(10) @OPT CMD_Option_D forD;
		public @S(20) PunctuationSlash slash;
		public @S(30) CMD_Keyword R = new CMD_Keyword("r");
		public @S(40) CMD_BasicExpression fileName;
		public @S(50) CMD_PctPctVariable var;
		public @S(60) CMD_Keyword IN = new CMD_Keyword("in");
	}

	public static class CMD_For_R_no_Filename extends TokenSequence
	{
		public @S(10) @OPT CMD_Option_D optD;
		public @S(20) PunctuationSlash slash;
		public @S(30) CMD_Keyword R = new CMD_Keyword("r");
		public @S(40) CMD_PctPctVariable var;
		public @S(50) CMD_Keyword IN = new CMD_Keyword("in");
	}

	public static class CMD_Option_D extends TokenSequence
	{
		public @S(10) PunctuationSlash slash;
		public @S(20) CMD_Keyword D = new CMD_Keyword("d");
	}

	public static class CMD_For_L extends TokenSequence
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

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
		}
		ForLoopMetric metric = new ForLoopMetric();
		boolean backwards = false;

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

		if (whichFor.getWhich() instanceof CMD_Simple_For)
		{
			CMD_Simple_For simpleFor = (CMD_Simple_For) whichFor.getWhich();
			if (!(simpleFor.type.getWhich() instanceof CMD_For_No_Options))
			{
				throw new RuntimeException("FOR statement cannot have options");
			}
			CMD_For_No_Options simple = (CMD_For_No_Options) simpleFor.type.getWhich();

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
					nextArg = simpleFor.moreArgs._elements.get(i - 1).arg;
				}

				metric.iterate();
				String val = interpreter.getStrValue(nextArg);
				interpreter.setSymbol(simple.var, simple.var.getValue(), new EagleString(val));

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
			if (incr < 0)
			{
				backwards = true;
			}

			while (true)
			{
				if (incr < 0 && i < stop) break;
				if (incr > 0 && i > stop) break;

				metric.iterate();
				interpreter.setSymbol(forL.var, forL.var.getValue(), new EagleInteger(i));

				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;

				i += incr;
			}
		}
		else
			throw new RuntimeException("Unable to handle " + whichFor);

		_metrics.completedLoop(metric, backwards);
		return result;
	}
}
