// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2011

namespace com.eagle.programmar.CMD.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleString = com.eagle.math.EagleString;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using CMD_BasicExpression = com.eagle.programmar.CMD.CMD_BasicExpression;
	using CMD_Expression = com.eagle.programmar.CMD.CMD_Expression;
	using CMD_Statement = com.eagle.programmar.CMD.CMD_Statement;
	using CMD_Keyword = com.eagle.programmar.CMD.Terminals.CMD_Keyword;
	using CMD_Literal = com.eagle.programmar.CMD.Terminals.CMD_Literal;
	using CMD_PctPctVariable = com.eagle.programmar.CMD.Terminals.CMD_PctPctVariable;
	using CMD_Punctuation = com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
	using CMD_RawArgument = com.eagle.programmar.CMD.Terminals.CMD_RawArgument;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSlash = com.eagle.tokens.punctuation.PunctuationSlash;

	public class CMD_For_Statement : TokenSequence, AbstractStatement, EagleRunnableWithResult
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("for.mspx") com.eagle.programmar.CMD.Terminals.CMD_Keyword FOR = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("for");
		public @DOC("for.mspx") CMD_Keyword FOR = new CMD_Keyword("for");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) CMD_For_Type whichFor;
		public CMD_For_Type whichFor;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMD.Terminals.CMD_Keyword DO = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("do");
		public CMD_Keyword DO = new CMD_Keyword("do");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT CMD_Punctuation at = new com.eagle.programmar.CMD.Terminals.CMD_Punctuation('@');
		public @OPT CMD_Punctuation at = new CMD_Punctuation('@');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.CMD.CMD_Statement stmt;
		public CMD_Statement stmt;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public static class CMD_For_More_Args extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PunctuationComma comma;
			public @OPT PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_RawArgument arg;
			public CMD_RawArgument arg;
		}

		public static class CMD_For_Type extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Simple_For XXsimpleFor;
			public CMD_Simple_For XXsimpleFor;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_For_L XXforL;
			public CMD_For_L XXforL;
		}

		public static class CMD_Simple_For extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) CMD_Simple_For_Type type;
			public CMD_Simple_For_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMD.Terminals.CMD_RawArgument arg;
			public CMD_RawArgument arg;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<CMD_For_More_Args> moreArgs;
			public @OPT TokenList<CMD_For_More_Args> moreArgs;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public static class CMD_Simple_For_Type extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_For_No_Options XXnoOptions;
			public CMD_For_No_Options XXnoOptions;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST CMD_For_D XXforD;
			public CMD_For_D XXforD;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_For_F XXforF;
			public CMD_For_F XXforF;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_For_R_Filename XXforR_filename;
			public CMD_For_R_Filename XXforR_filename;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_For_R_no_Filename XXforR_noFilename;
			public CMD_For_R_no_Filename XXforR_noFilename;
		}

		public static class CMD_For_No_Options extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_PctPctVariable var;
			public CMD_PctPctVariable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword IN = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("in");
			public CMD_Keyword IN = new CMD_Keyword("in");
		}

		public static class CMD_For_D extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) CMD_Option_D optD;
			public CMD_Option_D optD;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_PctPctVariable var;
			public CMD_PctPctVariable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMD.Terminals.CMD_Keyword IN = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("in");
			public CMD_Keyword IN = new CMD_Keyword("in");
		}

		public static class CMD_For_F extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationSlash slash;
			public PunctuationSlash slash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword F = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("f");
			public CMD_Keyword F = new CMD_Keyword("f");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT CMD_Literal options;
			public @OPT CMD_Literal options;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.CMD.Terminals.CMD_PctPctVariable var;
			public CMD_PctPctVariable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.CMD.Terminals.CMD_Keyword IN = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("in");
			public CMD_Keyword IN = new CMD_Keyword("in");
		}

		public static class CMD_For_R_Filename extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT CMD_Option_D forD;
			public @OPT CMD_Option_D forD;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationSlash slash;
			public PunctuationSlash slash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMD.Terminals.CMD_Keyword R = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("r");
			public CMD_Keyword R = new CMD_Keyword("r");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.CMD.CMD_BasicExpression fileName;
			public CMD_BasicExpression fileName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.CMD.Terminals.CMD_PctPctVariable var;
			public CMD_PctPctVariable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.CMD.Terminals.CMD_Keyword IN = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("in");
			public CMD_Keyword IN = new CMD_Keyword("in");
		}

		public static class CMD_For_R_no_Filename extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT CMD_Option_D optD;
			public @OPT CMD_Option_D optD;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationSlash slash;
			public PunctuationSlash slash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMD.Terminals.CMD_Keyword R = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("r");
			public CMD_Keyword R = new CMD_Keyword("r");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.CMD.Terminals.CMD_PctPctVariable var;
			public CMD_PctPctVariable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.CMD.Terminals.CMD_Keyword IN = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("in");
			public CMD_Keyword IN = new CMD_Keyword("in");
		}

		public static class CMD_Option_D extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationSlash slash;
			public PunctuationSlash slash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword D = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("d");
			public CMD_Keyword D = new CMD_Keyword("d");
		}

		public static class CMD_For_L extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationSlash slash;
			public PunctuationSlash slash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword L = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("l");
			public CMD_Keyword L = new CMD_Keyword("l");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMD.Terminals.CMD_PctPctVariable var;
			public CMD_PctPctVariable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.CMD.Terminals.CMD_Keyword IN = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("in");
			public CMD_Keyword IN = new CMD_Keyword("in");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.CMD.CMD_Expression start;
			public CMD_Expression start;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationComma comma1;
			public PunctuationComma comma1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.CMD.CMD_Expression incr;
			public CMD_Expression incr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.tokens.punctuation.PunctuationComma comma2;
			public PunctuationComma comma2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.programmar.CMD.CMD_Expression stop;
			public CMD_Expression stop;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
			}
			ForLoopMetric metric = new ForLoopMetric();
			bool backwards = false;

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

			if (whichFor.getWhich() is CMD_Simple_For)
			{
				CMD_Simple_For simpleFor = (CMD_Simple_For) whichFor.getWhich();
				if (!(simpleFor.type.getWhich() is CMD_For_No_Options))
				{
					throw new Exception("FOR statement cannot have options");
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
					string val = interpreter.getStrValue(nextArg);
					interpreter.setSymbol(simple.var, simple.var.getValue(), new EagleString(val));

					result = interpreter.tryToInterpret(stmt);
					if (result != Eagle_Statement_Result.NORMAL)
					{
						break;
					}
				}
			}
			else if (whichFor.getWhich() is CMD_For_L)
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
					if (incr < 0 && i < stop)
					{
						break;
					}
					if (incr > 0 && i > stop)
					{
						break;
					}

					metric.iterate();
					interpreter.setSymbol(forL.var, forL.var.getValue(), new EagleInteger(i));

					result = interpreter.tryToInterpret(stmt);
					if (result != Eagle_Statement_Result.NORMAL)
					{
						break;
					}

					i += incr;
				}
			}
			else
			{
				throw new Exception("Unable to handle " + whichFor);
			}

			_metrics.competedLoop(metric, backwards);
			return result;
		}
	}

}
