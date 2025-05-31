// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

package com.eagle.programmar.CSharp.Functions;

import com.eagle.generate.Functions.EagleGenerateLength;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class CSharp_LengthMethod extends PrecedenceOperator
		implements EagleRunnable, EagleGenerateLength<CSharp_Expression>
{
	public @S(10) CSharp_Expression expression = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE CSharp_Keyword LENGTH = new CSharp_Keyword("Length");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String leftStr = interpreter.getStrValue(expression);
		interpreter.pushInt(leftStr.length());
	}
	
	@Override
	public CSharp_Expression generateLength(CSharp_Expression expr, AbstractToken source)
	{
		this.dot = new PunctuationPeriod();
		this.expression = expr;
		this.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(this);
	}
}
