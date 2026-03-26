// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 22, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_BorrowExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_Punctuation ampersand = new Rust_Punctuation('&');
	public @S(20) @NOSPACE Rust_Expression expression;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expression);
		interpreter.pushEagleValue(value);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		return transformer.transformExpression(generator, expression);
	}
	
	public static Rust_Expression generateBorrow(Rust_Expression expr, AbstractToken source)
	{
		if (expr.getWhich() instanceof Rust_BorrowExpression)
		{
			// Unless you want two of them, as in &&x
			return expr;
		}
		
		Rust_BorrowExpression borrow = new Rust_BorrowExpression();
		borrow.expression = expr;

		borrow.setTransformationSource(source);
		return Rust_Generator.wrapExpression(borrow);
	}
}
