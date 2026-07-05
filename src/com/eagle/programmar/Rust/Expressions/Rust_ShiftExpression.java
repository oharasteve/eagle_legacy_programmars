// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.generate.ShiftEnum;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;

public class Rust_ShiftExpression extends PrecedenceOperator
{
	public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Rust_PunctuationChoice operator = new Rust_PunctuationChoice(">>>", "<<", ">>");
	public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);

	public static Rust_Expression generateShift(Rust_Expression leftExpr, ShiftEnum oper,
			Rust_Expression rightExpr, AbstractToken source)
	{
		Rust_ShiftExpression shift = new Rust_ShiftExpression();
		String op;
		switch (oper)
		{
		case LEFT:
			op = "<<";
			break;
		case RIGHT:
			op = ">>";
			break;
		case RIGHTSIGNEXTEND:
			op = ">>>";
			break;
		default:
			return null;
		}

		shift.left = leftExpr;
		shift.right = rightExpr;
		shift.operator = new Rust_PunctuationChoice(op);
		shift.setTransformationSource(source);
		return Rust_Generator.wrapExpression(shift);
	}
}
