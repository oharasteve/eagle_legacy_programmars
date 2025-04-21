// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.generate.EagleGenerator.ShiftEnum;
import com.eagle.generate.Expressions.Eagle_Generate_Bit_Shift;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;

public class Python_Shift_Expression extends PrecedenceOperator
		implements Eagle_Generate_Bit_Shift<Python_Expression>
{
	public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Python_PunctuationChoice operator = new Python_PunctuationChoice("<<", ">>");
	public @S(30) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public Python_Expression generateShift(Python_Expression left, ShiftEnum shift,
			Python_Expression right, AbstractToken source)
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

		this.left = left;
		this.right = right;
		this.operator = new Python_PunctuationChoice(oper);
		this.setTransformationSource(source);
		return Python_Generator.wrapExpression(this);
	}
}
