// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

/* In Rust, the bitwise NOT operator is the exclamation mark !.
 * This is unlike many other languages (like C and C++) where the tilde ~ symbol is used. 
 * The ! operator is used for both bitwise negation on integers and logical negation on boolean values.
 * Rust's strong type system prevents ambiguity, as an integer will always be treated with bitwise NOT,
 * and a boolean with logical NOT. 
*/

public class Rust_NotExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_Punctuation notOperator = new Rust_Punctuation('!');
	public @S(20) Rust_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean value = interpreter.getBoolValue(expr);
		interpreter.pushBool(!value);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
		return generator.newLogicalNotExpression(theExpr, this);
	}

	public Rust_Expression generateNot(Rust_Expression theExpr, AbstractToken source)
	{
		this.expr = theExpr;
		this.setTransformationSource(source);
		return Rust_Generator.wrapExpression(this);
	}
}
