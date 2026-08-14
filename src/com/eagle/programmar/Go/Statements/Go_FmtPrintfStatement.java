// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Terminals.Go_EOLN;
import com.eagle.programmar.Go.Terminals.Go_Keyword;
import com.eagle.programmar.Go.Terminals.Go_KeywordChoice;
import com.eagle.programmar.Go.Terminals.Go_LiteralExpression;
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
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Go_FmtPrintfStatement extends TokenSequence
		implements AbstractStatement, EagleRunnable, EagleTransformableStatement
{
	public @S(10) Go_Keyword FMT = new Go_Keyword("fmt");
	public @S(20) PunctuationPeriod dot;
	public @S(30) Go_KeywordChoice PRINTF = new Go_KeywordChoice("Printf", "Println");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) SeparatedList<Go_Expression, PunctuationComma> argList;
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

		switch(PRINTF.getValue())
		{
		case "Printf":
			String value = Go_LiteralExpression.interpret(interpreter, argList, _metrics);
			System.out.print(value);
			break;
		case "Println":
			String line = interpreter.getStrValue(argList.first());
			System.out.println(line);
			break;
		default:
			throw new RuntimeException("Unable to handle " + PRINTF.getValue());
		}
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<TypeEnum> metrics = transformer.findArgumentsMetric(PRINTF);
		switch(PRINTF.getValue())
		{
		case "Printf":
			AbstractExpression value = Go_LiteralExpression.transform(transformer, generator, argList, metrics, this);
			return generator.newPrintStatement1(value, TypeEnum.STRING, false, false, this);
		case "Println":
			AbstractExpression line = transformer.transformExpression(generator, argList.first());
			return generator.newPrintStatement1(line, TypeEnum.STRING, true, false, this);
		}
		return null;
	}
}
