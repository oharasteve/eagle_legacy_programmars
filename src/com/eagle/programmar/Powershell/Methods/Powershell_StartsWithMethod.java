// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 9, 2025

package com.eagle.programmar.Powershell.Methods;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Powershell_StartsWithMethod extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Powershell_Expression strExpr = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationPeriod dot;
	public @S(30) Powershell_Keyword STARTSWITH = new Powershell_Keyword("startswith");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) Powershell_Expression pattExpr;
	public @S(60) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(strExpr);
		String patt = interpreter.getStrValue(pattExpr);
		interpreter.pushBool(str.startsWith(patt));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, strExpr);
		AbstractExpression thePattern = transformer.transformExpression(generator, pattExpr);

		AbstractExpression theSC = null;
		return generator.newStartsWithFunction(theExpr, thePattern, theSC,
				SubstringSCEnum.FIRST_CHAR_IS_ZERO, this);
	}
}
