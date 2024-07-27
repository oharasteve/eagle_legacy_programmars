// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 21, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Java.Java_Annotation;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Label;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Java_Type;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_ForStatement extends TokenSequence implements EagleRunnableWithResult, AbstractStatement
{
	public @S(10) @OPT @NEWLINE Java_Label label;
	public @S(20) @DOC("statements.html#14.14") Java_Keyword FOR = new Java_Keyword("for");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) @OPT @NOSPACE Java_Annotation annotation;
	public @S(50) @OPT Java_ForInit init;
	public @S(60) @NOSPACE PunctuationSemicolon semicolon1;
	public @S(70) @OPT Java_Expression terminateCondition;
	public @S(80) @NOSPACE PunctuationSemicolon semicolon2;
	public @S(90) @OPT SeparatedList<Java_Expression, PunctuationComma> increments;
	public @S(100) @NOSPACE PunctuationRightParen rightParen;
	public @S(110) @OPT Java_Comment comment;
	public @S(120) Java_Statement action;

	private @SKIP ForLoopMetrics _metrics = null;

	public static class Java_ForInit extends TokenSequence
	{
		public @S(10) @OPT Java_Keyword FINAL = new Java_Keyword("final");
		public @S(20) SeparatedList<Java_ForWhat, PunctuationComma> what;
	}

	public static class Java_ForWhat extends TokenChooser
	{
		public @CHOICE Java_Expression XXexpr;
		public @FIRST Java_ForWithType XXforWithType;
	}

	public static class Java_ForWithType extends TokenSequence
	{
		public @S(10) Java_Type varType;
		public @S(20) Java_Expression expr;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Java_ForWhat forWhat = init.what.first();
		if (forWhat.getWhich() instanceof Java_ForWithType)
		{
			Java_ForWithType whatforWith = (Java_ForWithType) forWhat.getWhich();

			interpreter.tryToInterpret(whatforWith.expr);

			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, getFileName(), getStartLine(), getStartChar());
			}
			ForLoopMetric metric = new ForLoopMetric();

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			while (true)
			{
				boolean keepGoing = interpreter.getBoolValue(terminateCondition);
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

				interpreter.tryToInterpret(increments.first());
			}

			_metrics.competedLoop(metric);
			return result;
		}

		throw new RuntimeException("Unexpected for loop construct: " + forWhat.getWhich());
	}
	
//	private EagleScope _scope = new EagleScope(this, Java_Syntax.isCaseSensitive);
//
//	@Override
//	public EagleScope getScope()
//	{
//		return _scope;
//	}

//	@Override
//	public void setScope(EagleScope scope)
//	{
//		_scope = scope;
//	}
}
