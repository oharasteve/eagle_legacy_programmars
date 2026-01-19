// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Rust_Statement;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Rust_ReturnStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
{
	public @S(10) @DOC("expressions/return-expr.html") Rust_Keyword RETURN = new Rust_Keyword("return");
	public @S(20) Rust_Expression expr;
	public @S(30) @OPT PunctuationSemicolon semicolon;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expr);
		interpreter.pushEagleValue(val);
		return Eagle_Statement_Result.RETURN;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression retExpr = null;
		if (expr != null && expr.isPresent())
		{
			retExpr = transformer.transformExpression(generator, expr);
		}
		return generator.newReturnStatement(retExpr, this);
	}

	public Rust_Statement generateReturn(Rust_Expression ret, AbstractToken source)
	{
		if (ret != null)
		{
			this.expr = ret;
			this.expr.setPresent(true);
		}
		this.semicolon = new PunctuationSemicolon();
		this.setTransformationSource(source);
		return Rust_Generator.wrapStatement(this);
	}
}
