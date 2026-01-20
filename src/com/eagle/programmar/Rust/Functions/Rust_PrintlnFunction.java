// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

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
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_PrintlnFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_Keyword PRINTLN = new Rust_Keyword("println");
	public @S(20) @NOSPACE Rust_Punctuation bang = new Rust_Punctuation("!");
	public @S(30) @NOSPACE PunctuationLeftParen leftParen;
	public @S(40) @NOSPACE SeparatedList<Rust_Expression, PunctuationComma> argList;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;
	public @S(60) @OPT @NOSPACE PunctuationSemicolon semicolon;

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, PRINTLN.getValue(), PRINTLN);
		}
		String result = Rust_Format.format(interpreter, argList, _metrics);
		System.out.println(result);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		ArrayList<String> metrics = transformer.findArgumentsMetric(PRINTLN);
		AbstractExpression value = Rust_Format.transform(transformer, generator, argList, metrics);
		return generator.newPrintFunction(value, true, false, this);
	}

	public Rust_Expression generatePrintFunc(Rust_Expression line, boolean newLine,
			boolean toErr, AbstractToken source)
	{
		leftParen = new PunctuationLeftParen();
		rightParen = new PunctuationRightParen();

		argList = new SeparatedList<Rust_Expression, PunctuationComma>();
		argList.addPrimaryElement(line);

		setTransformationSource(source);
		return Rust_Generator.wrapExpression(this);
	}
}
