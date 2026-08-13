// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Nov 24, 2019

package com.eagle.programmar.Rust.Terminals;

import java.util.ArrayList;

import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Expressions.Rust_ParenthesizedExpression;
import com.eagle.programmar.Rust.Functions.Rust_FormatFunction;
import com.eagle.programmar.Rust.Functions.Rust_ToStringMethod;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Rust_Literal extends TerminalLiteralToken
{
	public Rust_Literal()
	{
		super("\"", true, '\\', false, false);
	}
	
	public static Rust_Literal generateLiteral(String value, AbstractToken source)
	{
		Rust_Literal lit = new Rust_Literal();
		String val = '"' + value
				.replaceAll("\\\\", "\\\\\\\\")
				.replaceAll("\\\\\\\\n", "\\\\n")
				.replaceAll("\"", "\\\\\"")
				+ '"';
		lit.setValue(val);
		lit.setTransformationSource(source);
		return lit;
	}

	public Rust_Expression generateConcatLiteral(ArrayList<Rust_Expression> pieces, AbstractToken source)
	{
		if (pieces.size() == 1)
		{
			return pieces.get(0);
		}
		
		StringBuffer sb = new StringBuffer();
		ArrayList<Rust_Expression> args = new ArrayList<Rust_Expression>();
		for (Rust_Expression piece : pieces)
		{
			AbstractToken which = piece.getWhich();
			if (which instanceof Rust_ToStringMethod)
			{
				Rust_ToStringMethod toStr = (Rust_ToStringMethod) which;
				which = toStr.left.getWhich();
			}
			if (which instanceof Rust_ParenthesizedExpression)
			{
				Rust_ParenthesizedExpression paren = (Rust_ParenthesizedExpression) which;
				which = paren.expressions.first().getWhich();
			}
			
			if (which instanceof Rust_Literal)
			{
				Rust_Literal lit = (Rust_Literal) which;
				sb.append(lit.removeQuotes().replaceAll("\\\"", "\\\\\""));
			}
			else
			{
				sb.append("{}");
				args.add(piece);
			}
		}
		
		this.setValue("\"" + sb.toString() + "\"");
		this.setTransformationSource(source);
		Rust_Expression fmtExpr = Rust_Generator.wrapExpression(this);
		return Rust_FormatFunction.generateFormat(fmtExpr, args, source);
	}
	
	public static Rust_Expression generateLiteralExpression(String value, AbstractToken source)
	{
		return Rust_Generator.wrapExpression(generateLiteral(value, source));
	}
}
