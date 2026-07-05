// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

package com.eagle.programmar.Java.Methods;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.SubstringSCEnum;
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
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Java_StartsWithMethod extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Java_Expression expression = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Java_Keyword STARTSWITH = new Java_Keyword("startsWith");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE Java_Expression patternExpr;
	public @S(60) @OPT @NOSPACE PunctuationComma comma;
	public @S(70) @OPT Java_Expression scExpr;
	public @S(80) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String leftStr = interpreter.getStrValue(expression);
		String pattern = interpreter.getStrValue(patternExpr);
		if (scExpr != null && scExpr.isPresent())
		{
			int sc = interpreter.getIntValue(scExpr);
			interpreter.pushBool(leftStr.startsWith(pattern, sc));
		}
		else
		{
			interpreter.pushBool(leftStr.startsWith(pattern));
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expression);
		AbstractExpression thePattern = transformer.transformExpression(generator, patternExpr);
		AbstractExpression theSC = null;
		if (scExpr != null && scExpr.isPresent())
		{
			theSC = transformer.transformExpression(generator, scExpr);
		}

		return generator.newStartsWithFunction(theExpr, thePattern, theSC,
				SubstringSCEnum.FIRST_CHAR_IS_ZERO, this);
	}

	public static Java_Expression generateStartsWith(Java_Expression expr, Java_Expression patt,
			Java_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
	{
		Java_StartsWithMethod startsMeth = new Java_StartsWithMethod();
		startsMeth.expression = expr;
		startsMeth.dot = new PunctuationPeriod();
		startsMeth.leftParen = new PunctuationLeftParen();
		startsMeth.patternExpr = patt;
		if (sc != null)
		{
			startsMeth.comma = new PunctuationComma();
			startsMeth.comma.setPresent(true);
			startsMeth.scExpr = sc;
			startsMeth.scExpr.setPresent(true);
		}
		startsMeth.rightParen = new PunctuationRightParen();

		startsMeth.setTransformationSource(source);
		return Java_Generator.wrapExpression(startsMeth);
	}
}
