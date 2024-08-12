// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_StartsWith extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Javascript_Variable variable;
	public @S(20) PunctuationPeriod dot;
	public @S(30) Javascript_Keyword STARTSWITH = new Javascript_Keyword("startsWith");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) Javascript_Expression pattExpr;
	public @S(60) @OPT PunctuationComma comma;
	public @S(70) @OPT Javascript_Expression scExpr;
	public @S(80) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken which = variable.firstId.getWhich();
		Javascript_Identifier_Reference id = (Javascript_Identifier_Reference) which;
		EagleValue val = interpreter.findSymbol(id.getValue());
		String str = val.forceStringValue();
		String patt = interpreter.getStrValue(pattExpr);
		if (scExpr != null && scExpr.isPresent())
		{
			int sc = interpreter.getIntValue(scExpr);
			interpreter.pushBool(str.startsWith(patt, sc));
		}
		else
		{
			interpreter.pushBool(str.startsWith(patt));
		}
	}
}
