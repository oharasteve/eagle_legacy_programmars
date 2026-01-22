// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Rust_Type;
import com.eagle.programmar.Rust.Rust_Variable;
import com.eagle.programmar.Rust.Expressions.Rust_AssignmentExpression;
import com.eagle.programmar.Rust.Expressions.Rust_VariableExpression;
import com.eagle.programmar.Rust.Symbols.Rust_Identifier_Reference;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.AbstractToken;
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
	
	public static Rust_LetStatement newDataDeclaration(boolean isStatic, String name,
			Rust_Expression size, Rust_Type type, Rust_Expression initial, AbstractToken source)
	{
		if (type == null)
		{
			throw new RuntimeException("Can't create data without a type, for " + name);
		}

		Rust_LetStatement letStmt = new Rust_LetStatement();
		letStmt.MUT.setPresent(true);
		letStmt.semicolon = new PunctuationSemicolon();
		letStmt.semicolon.setPresent(true);

		// Set data name, value and type
		letStmt.asgExpr = new Rust_AssignmentExpression();
		Rust_VariableExpression varExpr = new Rust_VariableExpression();
		varExpr.variable = new Rust_Variable();
		varExpr.variable.var = new Rust_Identifier_Reference();
		varExpr.variable.var.setValue(name);
		letStmt.asgExpr.var = Rust_Generator.wrapExpression(varExpr);
		letStmt.asgExpr.operator.setValue("=");
		letStmt.asgExpr.expr = initial;
		
		letStmt.letAs = new Rust_LetAs();
		letStmt.letAs.type = type;
		letStmt.letAs.setPresent(true);

		letStmt.setTransformationSource(source);
		return letStmt;
	}
}
