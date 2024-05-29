// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Julia.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Julia_Variable;
import com.eagle.programmar.Julia.Symbols.Julia_Identifier_Reference;
import com.eagle.programmar.Julia.Terminals.Julia_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Julia_MethodInvocation extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Julia_Variable methodName;
	public @S(20) @OPT Julia_Punctuation question = new Julia_Punctuation("?");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) @OPT SeparatedList<Julia_Expression, PunctuationComma> argList;
	public @S(50) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Julia_Identifier_Reference id = (Julia_Identifier_Reference) methodName.vars.first();
		if (! id.getValue().equals("string"))
		{
			throw new RuntimeException("Unexpected method: " + id.getValue());
		}

		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < argList.getPrimaryCount(); i++)
		{
			Julia_Expression expr = argList.getPrimaryElement(i);
			String val = interpreter.getStrValue(expr);
			buff.append(val);
		}
		interpreter.pushStr(buff.toString());
	}
}
