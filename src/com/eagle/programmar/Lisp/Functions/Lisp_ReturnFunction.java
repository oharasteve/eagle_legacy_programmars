// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 28, 2024

package com.eagle.programmar.Lisp.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_ReturnFunction extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @DOC("s_return.htm") Lisp_Keyword RETURN = new Lisp_Keyword("return");
	public @S(30) @OPT Lisp_Expression expr;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (expr != null && expr.isPresent())
		{
			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.pushEagleValue(val);
		}
		return Eagle_Statement_Result.RETURN;
	}
}
