// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 26, 2024

package com.eagle.programmar.Lisp.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Symbols.Lisp_Variable_Definition;
import com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_SetfFunction extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) Lisp_KeywordChoice SETF = new Lisp_KeywordChoice("setf");
	public @S(30) Lisp_Variable_Definition variable;
	public @S(40) Lisp_Expression value;
	public @S(50) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(value);
		interpreter.setSymbol(variable, variable.getValue(), val);
	}
}
