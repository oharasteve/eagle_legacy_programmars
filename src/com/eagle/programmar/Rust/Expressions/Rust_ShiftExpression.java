// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.transform.EagleGenerator.ShiftEnum;

public class Rust_ShiftExpression extends PrecedenceOperator
{
	public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Rust_PunctuationChoice operator = new Rust_PunctuationChoice(">>>", "<<", ">>");
	public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);

	public Rust_Expression generateShift(Rust_Expression leftExpr, ShiftEnum shift,
			Rust_Expression rightExpr, AbstractToken source)
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
		this.operator = new Rust_PunctuationChoice(oper);
		this.setTransformationSource(source);
		return Rust_Generator.wrapExpression(this);
	}
}
