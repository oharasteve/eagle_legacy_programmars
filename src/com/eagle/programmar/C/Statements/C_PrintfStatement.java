// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2024

package com.eagle.programmar.C.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Format;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class C_PrintfStatement extends TokenSequence
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) C_Keyword PRINTF = new C_Keyword("printf");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) SeparatedList<C_Expression, PunctuationComma> args;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) PunctuationSemicolon semicolon;

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, PRINTF.getValue(), PRINTF);
		}
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();

		String formatted = C_Format.format(interpreter, args, argTypes);
		_metrics.calledWith(argTypes);
		System.out.print(formatted);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<TypeEnum> metrics = transformer.findArgumentsMetric(PRINTF);
		AbstractExpression line = C_Format.transform(transformer, generator, args, metrics);
		return generator.newPrintStatement1(line, TypeEnum.STRING, true, false, this);
	}
}
