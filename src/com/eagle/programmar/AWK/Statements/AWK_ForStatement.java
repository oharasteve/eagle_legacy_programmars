// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 15, 2015

package com.eagle.programmar.AWK.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.AWK.AWK_Action;
import com.eagle.programmar.AWK.AWK_Action.AWK_StatementOrComment;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.AWK_Statements.AWK_Statement;
import com.eagle.programmar.AWK.AWK_Variable;
import com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class AWK_ForStatement extends TokenSequence implements EagleRunnableWithResult
{
	public @S(10) @DOC("#For-Statement") AWK_Keyword FOR = new AWK_Keyword("for");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) AWK_ForWhat forWhat;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) @OPT AWK_EndOfLine eoln;
	public @S(60) AWK_ForBlock block;

	private @SKIP ForLoopMetrics _metrics = null;

	public static class AWK_ForIteration extends TokenSequence implements AbstractStatement
	{
		public @S(10) AWK_Expression initialize;
		public @S(20) PunctuationSemicolon semicolon1;
		public @S(30) AWK_Expression test;
		public @S(40) PunctuationSemicolon semicolon2;
		public @S(50) AWK_Expression increment;
	}

	public static class AWK_ForWhat extends TokenChooser
	{
		public @CHOICE AWK_ForIteration XXforIteration;

		public @CHOICE static class AWK_ForEach extends TokenSequence
		{
			public @S(10) AWK_Variable var;
			public @S(20) AWK_Keyword IN = new AWK_Keyword("in");
			public @S(30) AWK_Expression value;
		}
	}

	public static class AWK_ForBlock extends TokenChooser
	{
		public @CHOICE AWK_Statement XXstmt;
		public @CHOICE AWK_Action XXactions;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (forWhat.getWhich() instanceof AWK_ForIteration)
		{
			AWK_ForIteration what = (AWK_ForIteration) forWhat.getWhich();

			interpreter.tryToInterpret(what.initialize);

			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, this);
			}
			ForLoopMetric metric = new ForLoopMetric();

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			while (true)
			{
				boolean keepGoing = interpreter.getBoolValue(what.test);
				if (!keepGoing) break;

				metric.iterate();
				AWK_Action stmts = (AWK_Action) block.getWhich();

				for (AWK_StatementOrComment stmt : stmts.statements._elements)
				{
					result = interpreter.tryToInterpret(stmt);
					if (result != Eagle_Statement_Result.NORMAL) break;
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

				interpreter.tryToInterpret(what.increment);
			}

			_metrics.competedLoop(metric);
			return result;
		}

		throw new RuntimeException("Unexpected for loop construct: " + forWhat.getWhich());
	}
}
