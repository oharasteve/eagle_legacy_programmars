// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2022

package com.eagle.programmar.Go.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Go_Statement;
import com.eagle.programmar.Go.Go_Variable;
import com.eagle.programmar.Go.Symbols.Go_Identifier_Reference;
import com.eagle.programmar.Go.Terminals.Go_Keyword;
import com.eagle.programmar.Go.Terminals.Go_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Go_ForStatement extends TokenSequence implements EagleRunnableWithResult, AbstractStatement
{
	public @S(10) @DOC("#For_statements") Go_Keyword FOR = new Go_Keyword("for");
	public @S(20) Go_Identifier_Reference var;
	public @S(30) Go_Punctuation colonEquals = new Go_Punctuation(":=");
	public @S(40) Go_ForWhat forWhat;

	private @SKIP ForLoopMetrics _metrics = null;

	public static class Go_ForWhat extends TokenChooser
	{
		public @CHOICE Go_ForLoop forLoop;
		public @CHOICE Go_ForRange forRange;
	}

	public static class Go_ForLoop extends TokenSequence
	{
		public @S(10) Go_Expression initValue;
		public @S(20) PunctuationSemicolon semiColon1;
		public @S(30) Go_Expression condition;
		public @S(40) PunctuationSemicolon semiColon2;
		public @S(50) Go_Expression increment;
		public @S(60) Go_Statement statement;
	}

	public static class Go_ForRange extends TokenSequence
	{
		public @S(10) Go_Keyword RANGE = new Go_Keyword("range");
		public @S(20) Go_Variable variable;
		public @S(30) Go_Statement statement;
	}
	
	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (forWhat.getWhich() instanceof Go_ForLoop)
		{
			Go_ForLoop forLoop = (Go_ForLoop) forWhat.getWhich();
			
			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, getFileName(), getStartLine(), getStartChar());
			}
			ForLoopMetric metric = new ForLoopMetric();

			int current = interpreter.getIntValue(forLoop.initValue);
			
			String loopVar = var.getValue();
			interpreter._symbolTable.setSymbol(_fileName, _currentLine, _currentChar,
					loopVar, new EagleInteger(current));

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			while (true)
			{
				boolean cond = interpreter.getBoolValue(forLoop.condition);
				if (!cond) break;

				metric.iterate();
	
				result = interpreter.tryToInterpret(forLoop.statement);
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
	
				interpreter.tryToInterpret(forLoop.increment);
			}
	
			_metrics.competedLoop(metric);
			return result;
		}
		
		throw new RuntimeException("Cannot handle this type of for loop (yet): " + forWhat);
	}
}