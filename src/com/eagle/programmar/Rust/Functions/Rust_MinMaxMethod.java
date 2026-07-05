// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 22, 2026

package com.eagle.programmar.Rust.Functions;

import com.eagle.generate.MinMaxEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Rust_MinMaxMethod extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Rust_Expression expr1 = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Rust_KeywordChoice MINMAX = new Rust_KeywordChoice("min", "max");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE Rust_Expression expr2;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int num1 = interpreter.getIntValue(expr1);
		int num2 = interpreter.getIntValue(expr2);
		if (MINMAX.getValue().equalsIgnoreCase("min"))
		{
			interpreter.pushInt(Math.min(num1, num2));
		}
		else
		{
			interpreter.pushInt(Math.max(num1, num2));
		}
	}

	public static Rust_Expression generateMinMax2(MinMaxEnum minmax, Rust_Expression x1, Rust_Expression x2, AbstractToken source)
	{
		Rust_MinMaxMethod mm = new Rust_MinMaxMethod();
		switch (minmax)
		{
		case MIN:
			mm.MINMAX.setValue("min");
			break;
		case MAX:
			mm.MINMAX.setValue("max");
			break;
		default:
			throw new RuntimeException("Unexpected min/max: " + minmax.toString());
		}
		mm.expr1 = x1;
		mm.dot = new PunctuationPeriod();
		mm.leftParen = new PunctuationLeftParen();
		mm.expr2 = x2;
		mm.rightParen = new PunctuationRightParen();
		
		mm.setTransformationSource(source);
		return Rust_Generator.wrapExpression(mm);
	}
}
