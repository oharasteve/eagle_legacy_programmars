// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Functions;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.AWK.AWK_ArgumentList;
import com.eagle.programmar.AWK.AWK_ArgumentList.AWK_MoreArguments;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.Expressions.AWK_String;
import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.terminals.TerminalLiteralExpression;
import com.eagle.tokens.terminals.TerminalLiteralExpression.LiteralPiece;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class AWK_SprintfFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) AWK_Keyword SPRINTF = new AWK_Keyword("sprintf");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) AWK_ArgumentList argList;
	public @S(40) PunctuationRightParen rightParen;

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, SPRINTF.getValue(), SPRINTF);
		}
		
		String fmt = interpreter.getStrValue(argList.expr);
		ArrayList<PrecedenceChooser> args = new ArrayList<PrecedenceChooser>();
		for (AWK_MoreArguments more : argList.more._elements)
		{
			args.add(more.expr);
		}
		ArrayList<LiteralPiece> pieces = TerminalLiteralExpression.parsePercent(fmt, '%', "ds", args);
		String result = TerminalLiteralExpression.evaluateLiteral(interpreter, _metrics, AWK_Expression.class, pieces);
		interpreter.pushStr(result);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<TypeEnum> metrics = transformer.findArgumentsMetric(SPRINTF);
		
		AbstractToken which = argList.expr.getWhich(); 
		if (!(which instanceof AWK_String))
		{
			throw new RuntimeException("Format must be a literal, not " + which);
		}
		AWK_String str = (AWK_String) which;
		String fmt = str.literal.removeQuotes();

		ArrayList<PrecedenceChooser> args = new ArrayList<PrecedenceChooser>();
		for (AWK_MoreArguments more : argList.more._elements)
		{
			args.add(more.expr);
		}
		ArrayList<LiteralPiece> pieces = TerminalLiteralExpression.parsePercent(fmt, '%', "ds", args);
		AbstractExpression result = TerminalLiteralExpression.compileLiteral(transformer, generator, metrics,
				AWK_Expression.class, pieces, this);
		return result;
	}
}
