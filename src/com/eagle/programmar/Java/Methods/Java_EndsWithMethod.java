// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Oct 5, 2025

package com.eagle.programmar.Java.Methods;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
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

public class Java_EndsWithMethod extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Java_Expression expression = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Java_Keyword ENDSWITH = new Java_Keyword("endsWith");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE Java_Expression patternExpr;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String leftStr = interpreter.getStrValue(expression);
		String pattern = interpreter.getStrValue(patternExpr);
		interpreter.pushBool(leftStr.endsWith(pattern));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expression);
		AbstractExpression thePattern = transformer.transformExpression(generator, patternExpr);
		return generator.newEndsWithFunction(theExpr, thePattern, this);
	}

	public static Java_Expression generateEndsWith(Java_Expression expr, Java_Expression patt,
			AbstractToken source)
	{
		Java_EndsWithMethod endsMeth = new Java_EndsWithMethod();
		endsMeth.expression = expr;
		endsMeth.dot = new PunctuationPeriod();
		endsMeth.leftParen = new PunctuationLeftParen();
		endsMeth.patternExpr = patt;
		endsMeth.rightParen = new PunctuationRightParen();

		endsMeth.setTransformationSource(source);
		return Java_Generator.wrapExpression(endsMeth);
	}
}
