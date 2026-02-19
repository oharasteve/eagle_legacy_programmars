// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

package com.eagle.programmar.CSharp.Methods;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator.SubstringECEnum;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class CSharp_StartsWithMethod extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE CSharp_Keyword STARTSWITH = new CSharp_Keyword("StartsWith");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE CSharp_Expression pattExpr;
	public @S(60) @OPT @NOSPACE PunctuationComma comma;
	public @S(70) @OPT CSharp_Expression scExpr;
	public @S(80) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String leftStr = interpreter.getStrValue(left);
		String pattern = interpreter.getStrValue(pattExpr);
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
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, left);
		AbstractExpression thePattern = transformer.transformExpression(generator, pattExpr);
		AbstractExpression theSC = null;
		if (scExpr != null && scExpr.isPresent())
		{
			theSC = transformer.transformExpression(generator, scExpr);
		}

		return generator.newStartsWithFunction(theExpr, thePattern, theSC,
				SubstringSCEnum.FIRST_CHAR_IS_ZERO, this);
	}

	public static CSharp_Expression generateStartsWith(CSharp_Expression expr, CSharp_Expression patt,
			CSharp_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
	{
		CSharp_StartsWithMethod startsMeth = new CSharp_StartsWithMethod();
		startsMeth.left = expr;
		startsMeth.dot = new PunctuationPeriod();
		startsMeth.leftParen = new PunctuationLeftParen();
		startsMeth.pattExpr = patt;
		if (sc != null)
		{
			// C# does not support str.StartsWith("patt",sc)
			// Have to use Substring instead
			startsMeth.left = CSharp_SubstringMethod.generateExpression(
					expr, sc, whichSC, SubstringECEnum.GIVEN_NEITHER, null, false, source);
		}
		startsMeth.rightParen = new PunctuationRightParen();

		startsMeth.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(startsMeth);
	}
}
