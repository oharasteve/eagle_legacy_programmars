// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 3, 2026

package com.eagle.programmar.Rust.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Type;
import com.eagle.programmar.Rust.Rust_Type.Rust_TypePrimitive;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;

public class Rust_AsExpression extends PrecedenceOperator
		implements EagleRunnable
{
	public @S(10) Rust_Expression expression = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Rust_Keyword AS = new Rust_Keyword("as");
	public @S(30) Rust_Type type = new Rust_Type();
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken which = type.getWhich();
		if (which instanceof Rust_TypePrimitive)
		{
			Rust_TypePrimitive primitive = (Rust_TypePrimitive) which;
			String prim = primitive.PRIMITIVE.getValue();
			switch (prim)
			{
			case "i32":
				int inum = interpreter.getIntValue(expression);
				interpreter.pushDouble(inum);
				break;
			case "f64":
				double dnum = interpreter.getIntValue(expression);
				interpreter.pushDouble(dnum);
				break;
			}
			throw new RuntimeException("Unable to cast to " + prim);
		}
		throw new RuntimeException("Unable to cast to " + which);
	}
	
	public static Rust_AsExpression generateAsExpr(Rust_Expression expr, Rust_Type type, AbstractToken source)
	{
		Rust_AsExpression as = new Rust_AsExpression();
		as.expression = expr;
		as.type = type;

		as.setTransformationSource(source);
		return as;
	}
}
