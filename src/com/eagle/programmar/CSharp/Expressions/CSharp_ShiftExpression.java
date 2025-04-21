// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.generate.EagleGenerator.ShiftEnum;
import com.eagle.generate.Expressions.Eagle_Generate_Bit_Shift;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;

public class CSharp_ShiftExpression extends PrecedenceOperator
		implements Eagle_Generate_Bit_Shift<CSharp_Expression>
{
	public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("<<", ">>", ">>>");
	public @S(30) CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public CSharp_Expression generateShift(CSharp_Expression left, ShiftEnum shift,
			CSharp_Expression right, AbstractToken source)
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
		this.operator = new CSharp_PunctuationChoice(oper);
		this.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(this);
	}
}
