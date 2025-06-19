// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 31, 2025

package com.eagle.programmar.CSharp.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_ToStringMethod extends PrecedenceOperator
		implements EagleRunnable
{
	public @S(10) CSharp_Expression expression = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE CSharp_Keyword TOSTRING = new CSharp_Keyword("ToString");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expression);
		interpreter.pushStr(val.toString());
	}

	public CSharp_Expression generateString(CSharp_Expression expr, AbstractToken source)
	{
		this.expression = expr;
		this.dot = new PunctuationPeriod();
		this.leftParen = new PunctuationLeftParen();
		this.rightParen = new PunctuationRightParen();
		
		this.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(this);
	}
}
