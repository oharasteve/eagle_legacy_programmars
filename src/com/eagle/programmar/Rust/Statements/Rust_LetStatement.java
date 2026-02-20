// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Type;
import com.eagle.programmar.Rust.Rust_Variable;
import com.eagle.programmar.Rust.Statements.Rust_LetStatement.Rust_DataInitialize.Rust_DataInit;
import com.eagle.programmar.Rust.Symbols.Rust_Identifier_Reference;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationColon;
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
	public @S(40) Rust_DataInitialize init;
	public @S(50) Rust_Type type;
	public @S(60) @OPT @NOSPACE PunctuationSemicolon semicolon;

	public static class Rust_DataInitialize extends TokenChooser
	{
		public @CHOICE PunctuationColon XXcolon;
		
		public @CHOICE static class Rust_DataInit extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) Rust_Expression expr;
			public @S(30) Rust_Keyword AS = new Rust_Keyword("as");
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (init.getWhich() instanceof Rust_DataInit)
		{
			Rust_DataInit dataInit = (Rust_DataInit) init.getWhich();
			String id = var.var.getValue();
			EagleValue val = interpreter.getEagleValue(dataInit.expr);
			interpreter.setSymbol(var, id, val);
		}
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		if (! (init.getWhich() instanceof Rust_DataInit))
		{
			return null;
		}

		Rust_DataInit dataInit = (Rust_DataInit) init.getWhich();
		// See if the Definition has some assignments in the metrics file
		TypeEnum type = transformer.findAssignMetric(var);
		AbstractType newType = generator.transformType(type, null, null);

		AbstractExpression initial = transformer.transformExpression(generator, dataInit.expr);

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

		if (name.equalsIgnoreCase("true") || name.equalsIgnoreCase("false"))
		{
			// Sorry, cannot redefine true or false
			return null;
		}

		Rust_LetStatement letStmt = new Rust_LetStatement();
		letStmt.MUT.setPresent(true);
		letStmt.semicolon = new PunctuationSemicolon();
		letStmt.semicolon.setPresent(true);

		// Set data name, value and type
		letStmt.var = new Rust_Variable();
		letStmt.var.var = new Rust_Identifier_Reference();
		letStmt.var.var.setValue(name);
		letStmt.type = type;
		letStmt.init = new Rust_DataInitialize();
		
		if (initial == null)
		{
			PunctuationColon colon = new PunctuationColon();
			letStmt.init.setWhich(colon);
		}
		else
		{
			Rust_DataInit dataInit = new Rust_DataInit();
			dataInit.equals = new PunctuationEquals();
			dataInit.expr = initial;
			letStmt.init.setWhich(dataInit);
		}

		letStmt.setTransformationSource(source);
		return letStmt;
	}
}
