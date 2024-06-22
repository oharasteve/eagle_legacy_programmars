// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 22, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Rust_BuiltinFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Rust_KeywordChoice function = new Rust_KeywordChoice("format");
	public @S(20) @OPT Rust_Punctuation bang = new Rust_Punctuation("!");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) @OPT SeparatedList<Rust_Expression, PunctuationComma> argList;
	public @S(50) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String fnName = function.getValue();
		switch (fnName)
		{
		case "format":
			StringBuffer sb = new StringBuffer();
			// TODO: Cheat -- ignore the actual format for the moment
			for (int i = 1; i < argList.getPrimaryCount(); i++)
			{
				Rust_Expression expr = argList.getPrimaryElement(i);
				sb.append(interpreter.getStrValue(expr));
			}
			interpreter.pushStr(sb.toString());
			break;
		default:
			throw new RuntimeException("Unable to handle " + fnName);
		}
	}
}
