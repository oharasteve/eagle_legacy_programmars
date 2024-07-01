// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Rust_PrintlnStatement extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) Rust_Keyword PRINTLN = new Rust_Keyword("println");
	public @S(20) Rust_Punctuation bang = new Rust_Punctuation("!");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) SeparatedList<Rust_Expression, PunctuationComma> items;
	public @S(50) PunctuationRightParen rightParen;
	public @S(60) @OPT PunctuationSemicolon semicolon;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String fmt = interpreter.getStrValue(items.getPrimaryElement(0));
		int sc = fmt.indexOf("{}");
		if (sc < 0)
		{
			// Nothing to insert in the string
			System.out.println(fmt);
		}
		else
		{
			StringBuffer result = new StringBuffer();
			int prev = 0;
			for (int i = 1; i < items.getPrimaryCount(); i++)
			{
				result.append(fmt.substring(prev, sc));
				String piece = interpreter.getStrValue(items.getPrimaryElement(i));
				result.append(piece);
				prev = sc + 2;
				sc = fmt.indexOf("{}", prev);
				if (sc < 0) break; // Ran out of {} insertion points
			}
			result.append(fmt.substring(prev));
			System.out.println(result.toString());
		}
	}
}
