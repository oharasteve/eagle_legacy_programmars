// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 16, 2024

package com.eagle.programmar.Ada.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.programmar.Ada.Terminals.Ada_KeywordChoice;
import com.eagle.programmar.Ada.Terminals.Ada_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Ada_PutIntegerStatement extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) Ada_Keyword INTEGER_IO = new Ada_Keyword("Integer_IO");
	public @S(20) PunctuationPeriod dot;
	public @S(30) Ada_KeywordChoice PUT = new Ada_KeywordChoice("put");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) Ada_Expression expr;
	public @S(60) PunctuationComma comma;
	public @S(70) Ada_Keyword WIDTH = new Ada_Keyword("Width");
	public @S(80) Ada_Punctuation arrow = new Ada_Punctuation("=>");
	public @S(90) Ada_Expression width;
	public @S(100) PunctuationRightParen rightParen;
	public @S(110) PunctuationSemicolon semicolon;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue result = interpreter.getEagleValue(expr);
		System.out.print(result.toString());
	}
}
