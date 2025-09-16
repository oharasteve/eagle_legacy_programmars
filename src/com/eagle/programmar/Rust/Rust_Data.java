// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 1, 2022

package com.eagle.programmar.Rust;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rust.Symbols.Rust_Variable_Definition;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice;
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

public class Rust_Data extends TokenSequence
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) @OPT Rust_Keyword PUB = new Rust_Keyword("pub");
	public @S(20) @DOC("items/static-items.html") Rust_KeywordChoice STATIC = new Rust_KeywordChoice("const", "static");
	public @S(30) Rust_Variable_Definition var;
	public @S(40) PunctuationColon colon;
	public @S(50) Rust_Type type;
	public @S(60) @OPT Rust_Data_Initial init;
	public @S(70) PunctuationSemicolon semicolon;

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
			EagleValue val = interpreter.getEagleValue(init.expr);
			interpreter.setSymbol(var, var.getValue(), val);
		}
	}
	
	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		// See if the Definition has some assignments in the metrics file
		TypeEnum typ = transformer.findAssignMetric(var);
		AbstractType newType = generator.transformType(typ, null, null);
		
		AbstractExpression initial = null;
		if (init != null && init.isPresent())
		{
			initial = transformer.transformExpression(generator, init.expr);
		}
		
		String name = var.getValue();
		AbstractStatement stmt = generator.newDataDeclaration(false, name, null, newType, initial, this);
		return stmt;
	}
}
