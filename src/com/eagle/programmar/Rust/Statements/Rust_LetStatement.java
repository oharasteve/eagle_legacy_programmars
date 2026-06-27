// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Type;
import com.eagle.programmar.Rust.Rust_Variable;
import com.eagle.programmar.Rust.Functions.Rust_ToStringMethod;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.programmar.Rust.Terminals.Rust_Literal;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.StaticEnum;
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
	public @S(40) @OPT Rust_ColonType colonType;
	public @S(50) @OPT Rust_DataInitialize init;
	public @S(60) @OPT @NOSPACE PunctuationSemicolon semicolon;

	public static class Rust_DataInitialize extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) Rust_Expression expr;
	}

	public static class Rust_ColonType extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) Rust_Type type;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (init != null && init.isPresent())
		{
			String id = var.var.getValue();
			EagleValue val = interpreter.getEagleValue(init.expr);
			interpreter.setSymbol(var, id, val);
		}
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression initial = null;
		if (init != null && init.isPresent())
		{
			initial = transformer.transformExpression(generator, init.expr);
		}

		// See if the Definition has some assignments in the metrics file
		TypeEnum typ = transformer.findAssignMetric(var.var);
		AbstractType newType = generator.transformType(typ, null, null);

		String name = var.var.getValue();
		AbstractStatement stmt = generator.newDataDeclaration(StaticEnum.NONE, name, null, newType, initial, this);
		return stmt;
	}
	
	public static Rust_LetStatement newDataDeclaration(StaticEnum isStatic, String name,
			Rust_Expression size, Rust_Type typ, Rust_Expression initial, AbstractToken source)
	{
		if (typ == null)
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
		letStmt.var = Rust_Variable.generateVariable(name);
		
		letStmt.colonType = new Rust_ColonType();
		letStmt.colonType.colon = new PunctuationColon();
		letStmt.colonType.type = typ;
		letStmt.colonType.setPresent(true);

		if (initial != null)
		{
			letStmt.init = new Rust_DataInitialize();
			letStmt.init.equals = new PunctuationEquals();
			
			if (initial.getWhich() instanceof Rust_Literal)
			{
				letStmt.init.expr = Rust_ToStringMethod.generateString(TypeEnum.STRING, initial, null);
			}
			else
			{
				letStmt.init.expr = initial;
			}
			
			letStmt.init.setPresent(true);
		}

		letStmt.setTransformationSource(source);
		return letStmt;
	}
}
