// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.COBOL.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class COBOL_LengthFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) COBOL_Keyword FUNCTION = new COBOL_Keyword("FUNCTION");
	public @S(20) COBOL_Keyword LENGTH = new COBOL_Keyword("LENGTH");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) COBOL_Expression expr;
	public @S(50) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(expr);
		interpreter.pushInt(str.length());
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
		return generator.newLengthFunction(theExpr, this);
	}
}
