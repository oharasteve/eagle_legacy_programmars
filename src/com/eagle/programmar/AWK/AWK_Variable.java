// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.math.EagleHash;
import com.eagle.programmar.AWK.Symbols.AWK_Identifier_Reference;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class AWK_Variable extends TokenSequence implements AbstractVariable, EagleRunnable
{
	public @S(10) AWK_Identifier_Reference id;
	public @S(20) @OPT TokenList<AWK_VarSubscript> subscripts;

	public static class AWK_VarSubscript extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) AWK_Expression expr;
		public @S(30) PunctuationRightBracket rightBracket;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.findSymbol(id.toString());
		if (subscripts != null && subscripts.isPresent() && subscripts.size() == 1)
		{
			EagleHash hash = (EagleHash) val;
			AWK_VarSubscript sub = subscripts.first();
			String key = interpreter.getStrValue(sub.expr);
			interpreter.pushEagleValue(hash.getValue(key));
		}
		else
		{
			interpreter.pushEagleValue(val);
		}
	}
}
