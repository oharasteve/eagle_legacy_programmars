// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.FSharp_Format;
import com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine;
import com.eagle.programmar.FSharp.Terminals.FSharp_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class FSharp_PrintfnStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) @DOC("plaintext-formatting") FSharp_Keyword PRINTFN = new FSharp_Keyword("printfn");
	public @S(20) TokenList<FSharp_Expression> arguments;
	public @S(30) FSharp_EndOfLine eoln;

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, PRINTFN.getValue(), PRINTFN);
		}
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();

		String formatted = FSharp_Format.format(interpreter, arguments, argTypes);
		_metrics.calledWith(argTypes);
		System.out.println(formatted);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<TypeEnum> metrics = transformer.findArgumentsMetric(PRINTFN);
		AbstractExpression fullExpr = FSharp_Format.transform(transformer, generator, arguments, metrics);
		return generator.newPrintStatement1(fullExpr, TypeEnum.STRING,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              true, false, this);
	}
}
