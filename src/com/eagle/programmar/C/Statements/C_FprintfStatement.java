// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2024

package com.eagle.programmar.C.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Format;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class C_FprintfStatement extends PrimaryOperator
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) C_Keyword FPRINTF = new C_Keyword("fprintf");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) C_KeywordChoice STDOUT = new C_KeywordChoice("stdout", "stderr");
	public @S(40) PunctuationComma comma;
	public @S(50) SeparatedList<C_Expression, PunctuationComma> args;
	public @S(60) PunctuationRightParen rightParen;
	public @S(70) PunctuationSemicolon semicolon;

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, FPRINTF.getValue(), FPRINTF);
		}
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();

		String formatted = C_Format.format(interpreter, args, argTypes);
		_metrics.calledWith(argTypes);
		switch (STDOUT.toString())
		{
		case "stdout":
			System.out.println(formatted);
			return;
		case "stderr":
			System.err.println(formatted);
			return;
		}
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		boolean toErr;
		switch (STDOUT.getValue())
		{
		case "stdout":
			toErr = false;
			break;
		case "stderr":
			toErr = true;
			break;
		default:
			throw new RuntimeException("Unexpected value: " + STDOUT.getValue());
		}

		ArrayList<TypeEnum> metrics = transformer.findArgumentsMetric(FPRINTF);
		AbstractExpression line = C_Format.transform(transformer, generator, args, metrics);
		return generator.newPrintStatement(line, true, toErr, this);
	}
}
