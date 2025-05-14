// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleDouble;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Basic.Basic_Expression;
import com.eagle.programmar.Basic.Basic_Statement;
import com.eagle.programmar.Basic.Basic_Statement.Basic_BaseStatement;
import com.eagle.programmar.Basic.Statements.Basic_ForStatement.Basic_For_PostFor.Basic_For_PostPair;
import com.eagle.programmar.Basic.Statements.Basic_ForStatement.Basic_For_PreNext.Basic_For_PreNextPair;
import com.eagle.programmar.Basic.Symbols.Basic_Identifier_Reference;
import com.eagle.programmar.Basic.Terminals.Basic_EndOfLine;
import com.eagle.programmar.Basic.Terminals.Basic_Keyword;
import com.eagle.programmar.Basic.Terminals.Basic_KeywordChoice;
import com.eagle.programmar.Basic.Terminals.Basic_Number;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationBackSlash;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Basic_ForStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) Basic_Keyword FOR = new Basic_Keyword("FOR");
	public @S(20) Basic_Identifier_Reference var1;
	public @S(30) PunctuationEquals equals;
	public @S(40) Basic_Expression from;
	public @S(50) Basic_Keyword TO = new Basic_Keyword("TO");
	public @S(60) Basic_Expression to;
	public @S(70) @OPT Basic_ForStep step;
	
	// What a mess! rest of this line, some lines, start of line with NEXT on it. Ooof.
	public @S(80) @OPT Basic_For_PostFor block1;
	public @S(90) @OPT Basic_EndOfLine eoln;
	public @S(100) @OPT TokenList<Basic_Statement> statements2;
	public @S(110) @OPT Basic_Number label;
	public @S(120) @OPT Basic_For_PreNext block3;
	public @S(130) @OPT PunctuationBackSlash backSlash;
	
	public @S(140) @OPT Basic_For_IfThenNext ifNext;
	public @S(150) Basic_KeywordChoice NEXT = new Basic_KeywordChoice("NEXT", "NEX");
	public @S(160) Basic_Identifier_Reference var2;

	public static class Basic_ForStep extends TokenSequence
	{
		public @S(10) Basic_Keyword STEP = new Basic_Keyword("STEP");
		public @S(20) Basic_Expression step;
	}
	
	public static class Basic_For_PostFor extends TokenSequence
	{
		public @S(10) TokenList<Basic_For_PostPair> pairs;
		
		public static class Basic_For_PostPair extends TokenSequence
		{
			public @S(10) PunctuationBackSlash backSlash;
			public @S(20) Basic_BaseStatement statement;
		}
	}
	
	public static class Basic_For_PreNext extends TokenSequence
	{
		public @S(10) TokenList<Basic_For_PreNextPair> pairs;
		
		public static class Basic_For_PreNextPair extends TokenSequence
		{
			public @S(10) Basic_BaseStatement statement;
			public @S(20) PunctuationBackSlash backSlash;
		}
	}
	
	public static class Basic_For_IfThenNext extends TokenSequence
	{
		public @S(10) Basic_Keyword IF = new Basic_Keyword("IF");
		public @S(20) Basic_Expression condition;
		public @S(30) Basic_KeywordChoice THEN = new Basic_KeywordChoice("THEN", "THE");
	}
	
	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, this);
		}
		ForLoopMetric metric = new ForLoopMetric();

		// Have to decide whether to loop over Integers or Doubles
		boolean useDoubles;
		
		int currentInt = 0;
		int stopInt = 0;
		int byInt = 1;
		double currentDbl = 0.0;
		double stopDbl = 0.0;
		double byDbl = 1.0;
		
		EagleValue current = interpreter.getEagleValue(from);
		EagleValue stop = interpreter.getEagleValue(to);
		EagleValue by = null;
		
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
			}
		}
		
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		while (true)
		{
			if (useDoubles)
			{
				if (byDbl < 0)
				{
					if (currentDbl < stopDbl) break;
				}
				else
				{
					if (currentDbl > stopDbl) break;
				}
			}
			else // use integers
			{
				if (byInt < 0)
				{
					if (currentInt < stopInt) break;
				}
				else
				{
					if (currentInt > stopInt) break;
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
				for (Basic_For_PostPair pair : block1.pairs._elements)
				{
					result = interpreter.tryToInterpret(pair.statement);
					if (result != Eagle_Statement_Result.NORMAL) break;
				}
			}

			// Complete lines between FOR and NEXT
			if (result == Eagle_Statement_Result.NORMAL)
			{
				if (statements2 != null && statements2.size() > 0)
				{
					
					for (Basic_Statement stmt : statements2._elements)
					{
						result = interpreter.tryToInterpret(stmt);
						if (result != Eagle_Statement_Result.NORMAL) break;
					}
				}
			}
			
			// Partial lines leading up to the NEXT
			if (result == Eagle_Statement_Result.NORMAL)
			{
				if (block3 != null && block3.pairs != null && block3.pairs.size() > 0)
				{
					for (Basic_For_PreNextPair pair : block3.pairs._elements)
					{
						result = interpreter.tryToInterpret(pair.statement);
						if (result != Eagle_Statement_Result.NORMAL) break;
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

		_metrics.competedLoop(metric);
		return result;
	}
}
