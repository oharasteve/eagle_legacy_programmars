// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Algol68_Type;
import com.eagle.programmar.Algol68.Symbols.Algol68_Variable_Definition;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Algol68_Data extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) Algol68_Type type;
	public @S(20) SeparatedList<Algol68_Variable_Definition, PunctuationComma> ids;
	public @S(30) @OPT Algol68_DataInitialValue init;
	public @S(40) PunctuationSemicolon semicolon;

	public static class Algol68_DataInitialValue extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) Algol68_Expression value;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (init != null && init.isPresent())
		{
			EagleValue val = interpreter.getEagleValue(init.value);
			Algol68_Variable_Definition var = ids.first();
			interpreter.setSymbol(var, var.getValue(), val);
		}
	}
	
	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		// See if the Definition has some assignments in the metrics file
		Algol68_Variable_Definition var = ids.first();
		TypeEnum typ = transformer.findAssignMetric(var);
		AbstractType newType = generator.transformType(typ, null, null);
		
		AbstractExpression initial = null;
		if (init != null && init.isPresent())
		{
			initial = transformer.transformExpression(generator, init.value);
		}
		
		String name = var.getValue();
		AbstractStatement stmt = generator.newDataDeclaration(name, null, newType, initial, this);
		return stmt;
	}

}
