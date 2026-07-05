// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Oct 5, 2025

package com.eagle.programmar.CSharp.Methods;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class CSharp_EndsWithMethod extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE CSharp_Keyword STARTSWITH = new CSharp_Keyword("StartsWith");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE CSharp_Expression pattExpr;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String leftStr = interpreter.getStrValue(left);
		String pattern = interpreter.getStrValue(pattExpr);
		interpreter.pushBool(leftStr.endsWith(pattern));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, left);
		AbstractExpression thePattern = transformer.transformExpression(generator, pattExpr);
		return generator.newEndsWithFunction(theExpr, thePattern, this);
	}

	public static CSharp_Expression generateEndsWith(CSharp_Expression expr, CSharp_Expression patt,
			AbstractToken source)
	{
		CSharp_EndsWithMethod endsMeth = new CSharp_EndsWithMethod();
		endsMeth.left = expr;
		endsMeth.dot = new PunctuationPeriod();
		endsMeth.leftParen = new PunctuationLeftParen();
		endsMeth.pattExpr = patt;
		endsMeth.rightParen = new PunctuationRightParen();
		endsMeth.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(endsMeth);
	}
}
