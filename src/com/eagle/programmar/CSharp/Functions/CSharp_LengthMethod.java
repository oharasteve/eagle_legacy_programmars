// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

package com.eagle.programmar.CSharp.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class CSharp_LengthMethod extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE CSharp_Keyword LENGTH = new CSharp_Keyword("Length");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String leftStr = interpreter.getStrValue(left);
		interpreter.pushInt(leftStr.length());
	}
	
	public static CSharp_LengthMethod generateExpression(AbstractExpression expr, AbstractToken source)
	{
		CSharp_LengthMethod len = new CSharp_LengthMethod();
		len.dot = new PunctuationPeriod();
		len.left = (CSharp_Expression) expr;
		len.setTransformationSource(source);
		return len;
	}
}
