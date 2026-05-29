// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 1, 2022

package com.eagle.programmar.Rust.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Type;
import com.eagle.programmar.Rust.Rust_Variable;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice;
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
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Rust_ConstStatement extends TokenSequence
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) @OPT @NEWLINE Rust_Keyword PUB = new Rust_Keyword("pub");
	public @S(20) @DOC("items/static-items.html") Rust_KeywordChoice STATIC = new Rust_KeywordChoice("const", "static");
	public @S(30) Rust_Variable var;
	public @S(40) PunctuationColon colon = new PunctuationColon();
	public @S(50) Rust_Type type;
	public @S(60) @OPT Rust_Data_Initial init;
	public @S(70) @NOSPACE PunctuationSemicolon semicolon;

	public static class Rust_Data_Initial extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) Rust_Expression expr;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (init.isPresent())
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
		// See if the Definition has some assignments in the metrics file
		TypeEnum typ = transformer.findAssignMetric(var);
		AbstractType newType = generator.transformType(typ, null, null);

		AbstractExpression initial = null;
		if (init != null && init.isPresent())
		{
			initial = transformer.transformExpression(generator, init.expr);
		}

		String name = var.var.getValue();
		AbstractStatement stmt = generator.newDataDeclaration(false, name, null, newType, initial, this);
		return stmt;
	}

	public static Rust_ConstStatement newDataDeclaration(boolean isStatic, String name, Rust_Expression unusedSize,
			Rust_Type type, Rust_Expression initial, AbstractToken source)
	{
		if (type == null)
		{
			throw new RuntimeException("Can't create data without a type, for " + name);
		}

		Rust_ConstStatement data = new Rust_ConstStatement();
		data.semicolon = new PunctuationSemicolon();

		// Set data name and type
		data.var = Rust_Variable.generateVariable(name);
		data.type = type;

		if (isStatic)
		{
			data.STATIC.setValue("static");
		}

		// Set the initial value, if any
		if (initial != null)
		{
			Rust_Data_Initial init = new Rust_Data_Initial();
			init.setPresent(true);
			init.equals = new PunctuationEquals();
			init.expr = initial;
			data.init = init;
			data.init.setPresent(true);
		}

		data.setTransformationSource(source);
		return data;
	}
}
