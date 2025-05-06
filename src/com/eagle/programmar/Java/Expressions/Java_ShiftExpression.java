// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.generate.EagleGenerator.ShiftEnum;
import com.eagle.generate.Expressions.Eagle_Generate_Bit_Shift;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;

public class Java_ShiftExpression extends PrecedenceOperator
		implements Eagle_Generate_Bit_Shift<Java_Expression>
{
	public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Java_PunctuationChoice operator = new Java_PunctuationChoice(">>>", "<<", ">>");
	public @S(30) Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public Java_Expression generateShift(Java_Expression leftExpr, ShiftEnum shift,
			Java_Expression rightExpr, AbstractToken source)
	{
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

		this.left = leftExpr;
		this.right = rightExpr;
		this.operator = new Java_PunctuationChoice(oper);
		this.setTransformationSource(source);
		return Java_Generator.wrapExpression(this);
	}
}
