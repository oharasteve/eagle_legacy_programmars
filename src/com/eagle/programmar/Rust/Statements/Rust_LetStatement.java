// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Type;
import com.eagle.programmar.Rust.Rust_Variable;
import com.eagle.programmar.Rust.Symbols.Rust_Identifier_Reference;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationEquals;
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
	public @S(30) Rust_Variable var;
	public @S(40) PunctuationEquals equals;
	public @S(50) Rust_Expression expr;
	public @S(60) @OPT Rust_LetAs letAs;
	public @S(70) @OPT @NOSPACE PunctuationSemicolon semicolon;

	public static class Rust_LetAs extends TokenSequence
	{
		public @S(10) Rust_Keyword AS = new Rust_Keyword("as");
		public @S(20) Rust_Type type;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String id = var.var.getValue();
		EagleValue val = interpreter.getEagleValue(expr);
		interpreter.setSymbol(var, id, val);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		// See if the Definition has some assignments in the metrics file
		TypeEnum type = transformer.findAssignMetric(var);
		AbstractType newType = generator.transformType(type, null, null);

		AbstractExpression initial = transformer.transformExpression(generator, expr);

		String name = var.var.getValue();
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
		letStmt.equals = new PunctuationEquals();
		letStmt.semicolon = new PunctuationSemicolon();
		letStmt.semicolon.setPresent(true);

		// Set data name, value and type
		letStmt.var = new Rust_Variable();
		letStmt.var.var = new Rust_Identifier_Reference();
		letStmt.var.var.setValue(name);
		letStmt.expr = initial;
		
		letStmt.letAs = new Rust_LetAs();
		letStmt.letAs.type = type;
		letStmt.letAs.setPresent(true);

		letStmt.setTransformationSource(source);
		return letStmt;
	}
}
