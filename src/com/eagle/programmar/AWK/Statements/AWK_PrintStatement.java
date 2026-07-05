// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.AWK.AWK_ArgumentList;
import com.eagle.programmar.AWK.AWK_ArgumentList.AWK_MoreArguments;
import com.eagle.programmar.AWK.Terminals.AWK_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class AWK_PrintStatement extends TokenSequence
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) @DOC("#print") AWK_KeywordChoice PRINT = new AWK_KeywordChoice("print", "printf");
	public @S(20) AWK_PrintParameters params;

	public static class AWK_PrintParameters extends TokenChooser implements AbstractStatement
	{
		public @FIRST AWK_Print_WithParens XXwithParens;
		public @CHOICE AWK_Print_NoParens XXnoParens;
	}

	public static class AWK_Print_WithParens extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT AWK_ArgumentList argList;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static class AWK_Print_NoParens extends TokenSequence
	{
		public @S(10) @OPT AWK_ArgumentList argList;
	}

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, PRINT.getValue(), PRINT);
		}
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();

		AWK_ArgumentList args;
		if (params.getWhich() instanceof AWK_Print_WithParens)
		{
			args = ((AWK_Print_WithParens) params.getWhich()).argList;
		}
		else if (params.getWhich() instanceof AWK_Print_NoParens)
		{
			args = ((AWK_Print_NoParens) params.getWhich()).argList;
		}
		else
		{
			throw new RuntimeException("Unexpected print argument: " + params.toString());
		}

		EagleValue val = interpreter.getEagleValue(args.expr);
		String result = val.forceStringValue();
		argTypes.add(val.getType());
		System.out.print(result);

		if (args.more != null)
		{
			for (AWK_MoreArguments nxt : args.more._elements)
			{
				val = interpreter.getEagleValue(nxt.expr);
				result = val.forceStringValue();
				argTypes.add(val.getType());
				System.out.print(result);
			}
		}

		_metrics.calledWith(argTypes);
		System.out.println();
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// Pick up metrics, if known
		ArrayList<TypeEnum> metrics = transformer.findArgumentsMetric(PRINT);

		AWK_ArgumentList argList;
		AbstractToken which1 = params.getWhich();
		if (which1 instanceof AWK_Print_WithParens)
		{
			AWK_Print_WithParens with = (AWK_Print_WithParens) which1;
			argList = with.argList;
		}
		else if (which1 instanceof AWK_Print_NoParens)
		{
			AWK_Print_NoParens without = (AWK_Print_NoParens) which1;
			argList = without.argList;
		}
		else
		{
			throw new RuntimeException("Unable to handle " + which1);
		}

		ArrayList<AbstractExpression> pieces = new ArrayList<AbstractExpression>();
		ArrayList<TypeEnum> types = new ArrayList<TypeEnum>();
		if (metrics == null)
		{
			types.add(TypeEnum.STRING);
		}
		else
		{
			types.add(metrics.get(0));
		}
		pieces.add(transformer.transformExpression(generator, argList.expr));
		
		int i = 0;
		for (AWK_MoreArguments more : argList.more._elements)
		{
			pieces.add(transformer.transformExpression(generator, more.expr));
			if (metrics == null)
			{
				types.add(TypeEnum.STRING);
			}
			else
			{
				i++;
				types.add(metrics.get(i));
			}
		}
		
		return generator.newPrintStatement(pieces, types, true, false, this);
	}
}
