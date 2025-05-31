// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 31, 2025

package com.eagle.programmar.VB.Functions;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class VB_CStrFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) @DOC("functions/string-functions") VB_Keyword CSTR = new VB_Keyword("CStr");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) VB_Expression expr;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expr);
		interpreter.pushStr(val.forceStringValue());
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
		return generator.newStringFunction(theExpr, this);
	}
}
