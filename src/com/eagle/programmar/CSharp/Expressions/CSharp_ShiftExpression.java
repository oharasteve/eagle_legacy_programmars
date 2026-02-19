// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.transform.EagleGenerator.ShiftEnum;

public class CSharp_ShiftExpression extends PrecedenceOperator
{
	public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("<<", ">>", ">>>");
	public @S(30) CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);

	public static CSharp_Expression generateShift(CSharp_Expression leftExpr, ShiftEnum shift,
			CSharp_Expression rightExpr, AbstractToken source)
	{
		CSharp_ShiftExpression shiftExpr = new CSharp_ShiftExpression();
		String oper;
		switch (shift)
		{
		case LEFT:
			oper = "<<";
			break;
		case RIGHT:
			oper = ">>";
			break;
		case RIGHTSIGNEXTEND:
			oper = ">>>";
			break;
		default:
			return null;
		}

		shiftExpr.left = leftExpr;
		shiftExpr.right = rightExpr;
		shiftExpr.operator = new CSharp_PunctuationChoice(oper);
		shiftExpr.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(shiftExpr);
	}
}
