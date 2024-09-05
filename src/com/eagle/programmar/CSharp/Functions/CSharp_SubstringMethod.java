// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

package com.eagle.programmar.CSharp.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.Expressions.CSharp_AdditiveExpression;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.SubstringEnum;

public class CSharp_SubstringMethod extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationPeriod dot;
	public @S(30) CSharp_Keyword SUBSTRING = new CSharp_Keyword("Substring");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) CSharp_Expression scExpr;
	public @S(60) @OPT PunctuationComma comma;
	public @S(70) @OPT CSharp_Expression ncExpr;
	public @S(80) PunctuationRightParen rightParen;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String leftStr = interpreter.getStrValue(left);
		int sc = interpreter.getIntValue(scExpr);
		if (ncExpr != null && ncExpr.isPresent())
		{
			int nc = interpreter.getIntValue(ncExpr);
			interpreter.pushStr(leftStr.substring(sc, sc + nc));
		}
		else
		{
			interpreter.pushStr(leftStr.substring(sc));
		}
	}
	
	public static CSharp_SubstringMethod generateExpression(AbstractExpression theExpr, AbstractExpression sc,
			SubstringEnum which, AbstractExpression ecOrnc, AbstractToken source)
	{
		CSharp_SubstringMethod expr = new CSharp_SubstringMethod();
		expr.left = (CSharp_Expression) theExpr;
		expr.scExpr = (CSharp_Expression) sc;
		
		switch (which)
		{
		case GIVEN_EC:
			CSharp_AdditiveExpression ecMinusSc = CSharp_AdditiveExpression.generateExpression(ecOrnc, AdditiveEnum.MINUS, sc, source);
			CSharp_Expression ncExpr = CSharp_Generator.wrapExpression(ecMinusSc);
			expr.ncExpr = CSharp_Generator.wrapExpression(ncExpr);
			expr.ncExpr.setPresent(true);
			break;
		case GIVEN_NC:
			expr.ncExpr = (CSharp_Expression) ecOrnc;
			expr.ncExpr.setPresent(true);
			break;
		case GIVEN_NEITHER:
			expr.ncExpr = null;
			break;
		}
		expr.setTransformationSource(source);
		return expr;
	}
}
