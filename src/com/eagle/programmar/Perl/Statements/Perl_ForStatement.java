// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Statement;
import com.eagle.programmar.Perl.Perl_Variable;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Perl_ForStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("control-structures.for.php") Perl_Keyword FOR = new Perl_Keyword("for");
	public @S(20) Perl_ForWhat forWhat;
	public @S(30) Perl_Statement action;
	
	private @SKIP ForLoopMetrics _metrics = null;

	public static class Perl_ForWhat extends TokenChooser
	{
		public @CHOICE Perl_ForVarInSet XXvarInSet;
		public @CHOICE Perl_ForLikeC XXlikeC;
	}
	
	public static class Perl_ForVarInSet extends TokenSequence
	{
		public @S(10) Perl_Keyword MY = new Perl_Keyword("my");
		public @S(20) Perl_Variable var;
		public @S(30) PunctuationLeftParen leftParen;
		public @S(40) Perl_Expression initExpr;
		public @S(50) PunctuationRightParen rightParen;
	}

	public static class Perl_ForLikeC extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT Perl_Expression initExpr;
		public @S(30) @OPT PunctuationSemicolon semicolon1;
		public @S(40) @OPT Perl_Expression testExpr;
		public @S(50) @OPT PunctuationSemicolon semicolon2;
		public @S(60) @OPT Perl_Expression incrExpr;
		public @S(70) PunctuationRightParen rightParen;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (forWhat.getWhich() instanceof Perl_ForLikeC)
		{
			Perl_ForLikeC forLikeC = (Perl_ForLikeC) forWhat.getWhich();

			interpreter.tryToInterpret(forLikeC.initExpr);

			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, this);
			}
			ForLoopMetric metric = new ForLoopMetric();

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			while (true)
			{
				boolean keepGoing = interpreter.getBoolValue(forLikeC.testExpr);
				if (!keepGoing) break;

				metric.iterate();
				result = interpreter.tryToInterpret(action);
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

				interpreter.tryToInterpret(forLikeC.incrExpr);
			}

			_metrics.competedLoop(metric);
			return result;
		}

		throw new RuntimeException("Unexpected for loop construct: " + forWhat.getWhich());
	}
}
