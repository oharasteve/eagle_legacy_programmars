// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_ReturnStatement extends TokenSequence implements EagleRunnableWithResult, AbstractStatement
{
	public @S(10) @NEWLINE @DOC("statements.html#14.17") Java_Keyword RETURN = new Java_Keyword("return");
	public @S(20) @OPT Java_Expression expression;
	public @S(30) @NOSPACE PunctuationSemicolon semicolon;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (expression != null && expression.isPresent())
		{
			EagleValue val = interpreter.getEagleValue(expression);
			interpreter.pushEagleValue(val);
		}
		return Eagle_Statement_Result.RETURN;
	}
}
