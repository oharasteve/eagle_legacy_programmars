// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

package com.eagle.programmar.CSharp.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator.SubstringECEnum;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;

public class CSharp_StartsWithMethod extends PrecedenceOperator
		implements EagleRunnable
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

	public CSharp_Expression generateStartsWith(CSharp_Expression expr, CSharp_Expression patt,
			CSharp_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
	{
		this.left = expr;
		this.dot = new PunctuationPeriod();
		this.leftParen = new PunctuationLeftParen();
		this.pattExpr = patt;
		if (sc != null)
		{
			// C# does not support str.StartsWith("patt",sc)
			// Have to use Substring instead
			CSharp_SubstringMethod substr = CSharp_SubstringMethod.generateExpression(
					expr, sc, whichSC, SubstringECEnum.GIVEN_NEITHER, null, false, source);
			this.left = CSharp_Generator.wrapExpression(substr);
		}
		this.rightParen = new PunctuationRightParen();
		
		this.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(this);
	}
}
