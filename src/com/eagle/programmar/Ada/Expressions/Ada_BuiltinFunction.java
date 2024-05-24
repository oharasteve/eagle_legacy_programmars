// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 16, 2024

package com.eagle.programmar.Ada.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Ada.Terminals.Ada_KeywordChoice;
import com.eagle.programmar.Ada.Terminals.Ada_Literal;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Ada_BuiltinFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Ada_KeywordChoice func = new Ada_KeywordChoice("To_Unbounded_String");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Ada_Literal literal;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String val = literal.getValue();
		interpreter.pushStr(val);
	}
}
