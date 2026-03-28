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
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_AsExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_Expression expression = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Rust_Keyword AS = new Rust_Keyword("as");
	public @S(30) Rust_TypeExpression type;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken which = type.type.getWhich();
		if (which instanceof Rust_TypePrimitive)
		{
			Rust_TypePrimitive primitive = (Rust_TypePrimitive) which;
			String prim = primitive.PRIMITIVE.getValue();
			switch (prim)
			{
			case "i32":
			case "usize":
				int inum = interpreter.getIntValue(expression);
				interpreter.pushInt(inum);
				return;
			case "f64":
				double dnum = interpreter.getIntValue(expression);
				interpreter.pushDouble(dnum);
				return;
			case "String":
			case "&str":
				String str = interpreter.getStrValue(expression);
				interpreter.pushStr(str);
				return;
			}
			throw new RuntimeException("Unable to cast to " + prim);
		}
		throw new RuntimeException("Unable to cast to " + which);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// This is not really correct. Need to generate a cast, in some cases. 
		AbstractExpression newExpr = transformer.transformExpression(generator, expression);
		return newExpr;
	}
	
	public static Rust_AsExpression generateAsExpr(Rust_Expression expr, Rust_Type type, AbstractToken source)
	{
		Rust_TypeExpression typeExpr = new Rust_TypeExpression();
		typeExpr.type = type;
		Rust_AsExpression as = new Rust_AsExpression();
		as.expression = expr;
		as.type = typeExpr;

		as.setTransformationSource(source);
		return as;
	}
}
