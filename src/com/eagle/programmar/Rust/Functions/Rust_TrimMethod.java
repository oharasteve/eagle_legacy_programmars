// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 19, 2026

package com.eagle.programmar.Rust.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Rust_TrimMethod extends PrecedenceOperator
		implements EagleRunnable
{
	public @S(10) Rust_Expression expression = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Rust_Keyword TRIM = new Rust_Keyword("trim");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String leftStr = interpreter.getStrValue(expression);
		interpreter.pushStr(leftStr.trim());
	}

	public static Rust_Expression generateTrim(Rust_Expression expr, AbstractToken source)
	{
		Rust_TrimMethod trimMeth = new Rust_TrimMethod();
		trimMeth.expression = expr;
		trimMeth.dot = new PunctuationPeriod();
		trimMeth.leftParen = new PunctuationLeftParen();
		trimMeth.rightParen = new PunctuationRightParen();

		trimMeth.setTransformationSource(source);
		return Rust_Generator.wrapExpression(trimMeth);
	}
}
