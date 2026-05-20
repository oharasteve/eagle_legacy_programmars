// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 19, 2026

package com.eagle.programmar.Java.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.MultiplicativeEnum;

public class Java_MathFloorModFunc extends TokenSequence
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Java_Keyword FLOORMOD = new Java_Keyword("floorMod");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE Java_Expression numer;
	public @S(40) @NOSPACE PunctuationComma comma;
	public @S(50) Java_Expression denom;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int x = interpreter.getIntValue(numer);
		int y = interpreter.getIntValue(denom);
		interpreter.pushInt(Math.floorMod(x, y));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression numerExpr = transformer.transformExpression(generator, numer);
		AbstractExpression denomExpr = transformer.transformExpression(generator, denom);
		return generator.newMultiplicativeExpression(numerExpr, MultiplicativeEnum.MODULUS, denomExpr, FLOORMOD);
	}

	public static Java_Expression generateMathFloorFunc(AbstractExpression numer, AbstractExpression denom,
			AbstractToken source)
	{
		Java_MathFloorModFunc mod = new Java_MathFloorModFunc();
		mod.leftParen = new PunctuationLeftParen();
		mod.numer = (Java_Expression) numer;
		mod.comma = new PunctuationComma();
		mod.denom = (Java_Expression) denom;
		mod.rightParen = new PunctuationRightParen();
		mod.setTransformationSource(source);
		return Java_MathFunction.wrapMathFunction(mod, source);
	}
}
