// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.transform.EagleGenerator.ShiftEnum;

public class Python_Shift_Expression extends PrecedenceOperator
{
	public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Python_PunctuationChoice operator = new Python_PunctuationChoice("<<", ">>");
	public @S(30) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);

	public static Python_Expression generateShift(Python_Expression leftExpr, ShiftEnum shift,
			Python_Expression rightExpr, AbstractToken source)
	{
		Python_Shift_Expression shiftExpr = new Python_Shift_Expression();
		String oper;
		switch (shift)
		{
		case LEFT:
			oper = "<<";
			break;
		case RIGHT:
			oper = ">>";
			break;
		default:
			throw new RuntimeException("Unable to handle shift operator: " + shift);
		}

		shiftExpr.left = leftExpr;
		shiftExpr.right = rightExpr;
		shiftExpr.operator = new Python_PunctuationChoice(oper);
		shiftExpr.setTransformationSource(source);
		return Python_Generator.wrapExpression(shiftExpr);
	}
}
