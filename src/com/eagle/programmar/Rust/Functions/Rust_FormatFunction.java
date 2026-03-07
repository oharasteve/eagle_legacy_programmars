// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 22, 2024

package com.eagle.programmar.Rust.Functions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Format;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_FormatFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_Keyword FORMAT = new Rust_Keyword("format");
	public @S(20) Rust_Punctuation bang = new Rust_Punctuation("!");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) SeparatedList<Rust_Expression, PunctuationComma> argList;
	public @S(50) PunctuationRightParen rightParen;

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, FORMAT.getValue(), FORMAT);
		}

		String result = Rust_Format.format(interpreter, argList, _metrics);
		interpreter.pushStr(result);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		ArrayList<String> metrics = transformer.findArgumentsMetric(FORMAT);
		return Rust_Format.compile(transformer, generator, argList, metrics);
	}

	public static Rust_Expression generateFormat(Rust_Expression fmt,
			ArrayList<Rust_Expression> args, AbstractToken source)
	{
		Rust_FormatFunction func = new Rust_FormatFunction();
		func.leftParen = new PunctuationLeftParen();
		func.argList = new SeparatedList<Rust_Expression, PunctuationComma>();
		func.argList.addPrimaryElement(fmt);
		func.rightParen = new PunctuationRightParen();
		
		for (Rust_Expression arg : args)
		{
			func.argList.addSecondaryElement(new PunctuationComma());
			func.argList.addPrimaryElement(arg);
		}
		
		func.setTransformationSource(source);
		return Rust_Generator.wrapExpression(func);
	}
}
