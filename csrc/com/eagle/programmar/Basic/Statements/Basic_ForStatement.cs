// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

namespace com.eagle.programmar.Basic.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleDouble = com.eagle.math.EagleDouble;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleValue = com.eagle.math.EagleValue;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using Basic_Expression = com.eagle.programmar.Basic.Basic_Expression;
	using Basic_Statement = com.eagle.programmar.Basic.Basic_Statement;
	using Basic_BaseStatement = com.eagle.programmar.Basic.Basic_Statement.Basic_BaseStatement;
	using Basic_Identifier_Reference = com.eagle.programmar.Basic.Symbols.Basic_Identifier_Reference;
	using Basic_EndOfLine = com.eagle.programmar.Basic.Terminals.Basic_EndOfLine;
	using Basic_Keyword = com.eagle.programmar.Basic.Terminals.Basic_Keyword;
	using Basic_KeywordChoice = com.eagle.programmar.Basic.Terminals.Basic_KeywordChoice;
	using Basic_Number = com.eagle.programmar.Basic.Terminals.Basic_Number;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationBackSlash = com.eagle.tokens.punctuation.PunctuationBackSlash;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;

	public class Basic_ForStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Basic.Terminals.Basic_Keyword FOR = new com.eagle.programmar.Basic.Terminals.Basic_Keyword("FOR");
		public Basic_Keyword FOR = new Basic_Keyword("FOR");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Basic.Symbols.Basic_Identifier_Reference var1;
		public Basic_Identifier_Reference var1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Basic.Basic_Expression from;
		public Basic_Expression from;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Basic.Terminals.Basic_Keyword TO = new com.eagle.programmar.Basic.Terminals.Basic_Keyword("TO");
		public Basic_Keyword TO = new Basic_Keyword("TO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Basic.Basic_Expression to;
		public Basic_Expression to;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT Basic_ForStep step;
		public  OPT;

		// What a mess! rest of this line, some lines, start of line with NEXT on it.
		// Ooof.
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT Basic_For_PostFor block1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT Basic_EndOfLine eoln;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT TokenList<com.eagle.programmar.Basic.Basic_Statement> statements2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) @OPT Basic_Number label;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(120) @OPT Basic_For_PreNext block3;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(130) @OPT PunctuationBackSlash backSlash;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(140) @OPT Basic_For_IfThenNext ifNext;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(150) com.eagle.programmar.Basic.Terminals.Basic_KeywordChoice NEXT = new com.eagle.programmar.Basic.Terminals.Basic_KeywordChoice("NEXT", "NEX");
		public Basic_KeywordChoice NEXT = new Basic_KeywordChoice("NEXT", "NEX");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(160) com.eagle.programmar.Basic.Symbols.Basic_Identifier_Reference var2;
		public Basic_Identifier_Reference var2;

		public class Basic_ForStep : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Basic.Terminals.Basic_Keyword STEP = new com.eagle.programmar.Basic.Terminals.Basic_Keyword("STEP");
			public Basic_Keyword STEP = new Basic_Keyword("STEP");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Basic.Basic_Expression step;
			public Basic_Expression step;
		}

		public class Basic_For_PostFor : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<Basic_For_PostPair> pairs;
			public TokenList<Basic_For_PostPair> pairs;
		}

		public class Basic_For_PostPair : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationBackSlash backSlash;
			public PunctuationBackSlash backSlash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Basic.Basic_Statement.Basic_BaseStatement statement;
			public Basic_Statement.Basic_BaseStatement statement;
		}

		public class Basic_For_PreNext : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<Basic_For_PreNextPair> pairs;
			public TokenList<Basic_For_PreNextPair> pairs;
		}

		public class Basic_For_PreNextPair : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Basic.Basic_Statement.Basic_BaseStatement statement;
			public Basic_Statement.Basic_BaseStatement statement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationBackSlash backSlash;
			public PunctuationBackSlash backSlash;
		}

		public class Basic_For_IfThenNext : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Basic.Terminals.Basic_Keyword IF = new com.eagle.programmar.Basic.Terminals.Basic_Keyword("IF");
			public Basic_Keyword IF = new Basic_Keyword("IF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Basic.Basic_Expression condition;
			public Basic_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Basic.Terminals.Basic_KeywordChoice THEN = new com.eagle.programmar.Basic.Terminals.Basic_KeywordChoice("THEN", "THE");
			public Basic_KeywordChoice THEN = new Basic_KeywordChoice("THEN", "THE");
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
			}
			ForLoopMetric metric = new ForLoopMetric();

			// Have to decide whether to loop over Integers or Doubles
			bool useDoubles;

			int currentInt = 0;
			int stopInt = 0;
			int byInt = 1;
			double currentDbl = 0.0;
			double stopDbl = 0.0;
			double byDbl = 1.0;

			EagleValue current = interpreter.getEagleValue(from);
			EagleValue stop = interpreter.getEagleValue(to);
			EagleValue by = null;
			bool backwards = false;

			if (step != null && step.isPresent())
			{
				by = interpreter.getEagleValue(step.step);
			}

			if (current.isDouble() || stop.isDouble() || (by != null && by.isDouble()))
			{
				useDoubles = true;
				currentDbl = current.forceDoubleValue();
				stopDbl = stop.forceDoubleValue();
				if (by != null)
				{
					byDbl = by.forceDoubleValue();
					if (byDbl < 0)
					{
						backwards = true;
					}
				}
			}
			else
			{
				useDoubles = false;
				currentInt = current.forceIntegerValue();
				stopInt = stop.forceIntegerValue();
				if (by != null)
				{
					byInt = by.forceIntegerValue();
					if (byInt < 0)
					{
						backwards = true;
					}
				}
			}

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			while (true)
			{
				if (useDoubles)
				{
					if (byDbl < 0)
					{
						if (currentDbl < stopDbl)
						{
							break;
						}
					}
					else
					{
						if (currentDbl > stopDbl)
						{
							break;
						}
					}
				}
				else // use integers
				{
					if (byInt < 0)
					{
						if (currentInt < stopInt)
						{
							break;
						}
					}
					else
					{
						if (currentInt > stopInt)
						{
							break;
						}
					}
				}

				metric.iterate();
				if (useDoubles)
				{
					interpreter.setSymbol(this, var1.getValue(), new EagleDouble(currentDbl));
				}
				else
				{
					interpreter.setSymbol(this, var1.getValue(), new EagleInteger(currentInt));
				}

				// Rest of this line, following the FOR
				if (block1 != null && block1.pairs != null && block1.pairs.size() > 0)
				{
					foreach (Basic_For_PostPair pair in block1.pairs._elements)
					{
						result = interpreter.tryToInterpret(pair.statement);
						if (result != Eagle_Statement_Result.NORMAL)
						{
							break;
						}
					}
				}

				// Complete lines between FOR and NEXT
				if (result == Eagle_Statement_Result.NORMAL)
				{
					if (statements2 != null && statements2.size() > 0)
					{

						foreach (Basic_Statement stmt in statements2._elements)
						{
							result = interpreter.tryToInterpret(stmt);
							if (result != Eagle_Statement_Result.NORMAL)
							{
								break;
							}
						}
					}
				}

				// Partial lines leading up to the NEXT
				if (result == Eagle_Statement_Result.NORMAL)
				{
					if (block3 != null && block3.pairs != null && block3.pairs.size() > 0)
					{
						foreach (Basic_For_PreNextPair pair in block3.pairs._elements)
						{
							result = interpreter.tryToInterpret(pair.statement);
							if (result != Eagle_Statement_Result.NORMAL)
							{
								break;
							}
						}
					}
				}

				if (result == Eagle_Statement_Result.BREAK)
				{
					metric.broke();
					result = Eagle_Statement_Result.NORMAL;
					break;
				}
				else if (result == Eagle_Statement_Result.CONTINUE)
				{
					metric.continued();
					result = Eagle_Statement_Result.NORMAL;
				}
				else if (result == Eagle_Statement_Result.RETURN)
				{
					break;
				}

				if (useDoubles)
				{
					currentDbl += byDbl;
				}
				else
				{
					currentInt += byInt;
				}
			}

			_metrics.competedLoop(metric, backwards);
			return result;
		}
	}

}
