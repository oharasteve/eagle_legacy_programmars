// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Type;
import com.eagle.programmar.Rust.Expressions.Rust_AssignmentExpression;
import com.eagle.programmar.Rust.Expressions.Rust_VariableExpression;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Rust_LetStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) @DOC("statements.html#let-statements") @NEWLINE Rust_Keyword LET =
			new Rust_Keyword("let");
	public @S(20) @OPT Rust_Keyword MUT = new Rust_Keyword("mut");
	public @S(30) Rust_AssignmentExpression asgExpr;
	public @S(40) @OPT Rust_LetAs letAs;
	public @S(50) @OPT @NOSPACE PunctuationSemicolon semicolon;

	public static class Rust_LetAs extends TokenSequence
	{
		public @S(10) Rust_Keyword AS = new Rust_Keyword("as");
		public @S(20) Rust_Type type;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(asgExpr);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		// See if the Definition has some assignments in the metrics file
		TypeEnum type = transformer.findAssignMetric(asgExpr.var);
		AbstractType newType = generator.transformType(type, null, null);

		AbstractExpression initial = transformer.transformExpression(generator, asgExpr.expr);

		if (!(asgExpr.var.getWhich() instanceof Rust_VariableExpression))
		{
			throw new RuntimeException("Unexpected assignment variable: " + asgExpr.var.getWhich());
		}
		Rust_VariableExpression varExpr = (Rust_VariableExpression) asgExpr.var.getWhich();

		String name = varExpr.variable.var.getValue();
		AbstractStatement stmt = generator.newDataDeclaration(false, name, null, newType, initial, this);
		return stmt;
	}
}
