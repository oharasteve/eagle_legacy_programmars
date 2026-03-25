// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Functions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.AWK.AWK_ArgumentList;
import com.eagle.programmar.AWK.AWK_ArgumentList.AWK_MoreArguments;
import com.eagle.programmar.AWK.AWK_Format;
import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
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
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();

		StringBuffer sb = new StringBuffer();
		// sb.append(interpreter.getStrValue(argList.expr)); // Skip the format for now
		for (AWK_MoreArguments more : argList.more._elements)
		{
			EagleValue val = interpreter.getEagleValue(more.expr);
			String result = val.forceStringValue();
			argTypes.add(val.getType());
			sb.append(result);
		}
		interpreter.pushStr(sb.toString());

		_metrics.calledWith(argTypes);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<TypeEnum> metrics = transformer.findArgumentsMetric(SPRINTF);
		return AWK_Format.transform(transformer, generator, argList.expr, argList.more, metrics);
	}
}
