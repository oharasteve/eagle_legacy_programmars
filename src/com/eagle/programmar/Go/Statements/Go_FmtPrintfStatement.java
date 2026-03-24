// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Go_Format;
import com.eagle.programmar.Go.Terminals.Go_EOLN;
import com.eagle.programmar.Go.Terminals.Go_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Go_FmtPrintfStatement extends TokenSequence
		implements AbstractStatement, EagleRunnable, EagleTransformableStatement
{
	public @S(10) Go_Keyword FMT = new Go_Keyword("fmt");
	public @S(20) PunctuationPeriod dot;
	public @S(30) Go_Keyword PRINTF = new Go_Keyword("Printf");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) SeparatedList<Go_Expression, PunctuationComma> arguments;
	public @S(60) PunctuationRightParen rightParen;
	public @S(70) Go_EOLN eoln;

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, PRINTF.getValue(), PRINTF);
		}

		String formatted = Go_Format.format(interpreter, arguments, _metrics);
		if (formatted.endsWith("\\n"))
		{
			formatted = formatted.substring(0, formatted.length() - 2);
		}
		System.out.println(formatted);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<String> metrics = transformer.findArgumentsMetric(PRINTF);
		AbstractExpression fullExpr = Go_Format.transform(transformer, generator, arguments, metrics);
		return generator.newPrintStatement(fullExpr, true, false, this);
	}
}
