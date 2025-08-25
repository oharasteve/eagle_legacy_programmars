// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 22, 2024

package com.eagle.programmar.Rust.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Rust_ToStringMethod extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationPeriod dot;
	public @S(30) Rust_KeywordChoice TOSTRING = new Rust_KeywordChoice("as_str", "to_string");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String val = interpreter.getStrValue(left);
		interpreter.pushStr(val);
	}
}
