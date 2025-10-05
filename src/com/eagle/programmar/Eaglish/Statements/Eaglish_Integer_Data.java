// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Variable_Definition;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Eaglish_Integer_Data extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) Eaglish_Keyword INTEGER = new Eaglish_Keyword("INTEGER");
	public @S(20) Eaglish_Variable_Definition var;
	public @S(30) @OPT Eaglish_Integer_InitialValue init;
	public @S(40) Eaglish_EndOfLine eoln;

	public static class Eaglish_Integer_InitialValue extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) Eaglish_Expression expression;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (init != null && init.isPresent())
		{
			int x = interpreter.getIntValue(init.expression);
			EagleInteger val = new EagleInteger(x);
			interpreter.setSymbol(var, var.getValue(), val);
		}
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractType newType = generator.transformType(TypeEnum.INTEGER, null, INTEGER);
		String name = var.getValue();
		AbstractExpression initial = null;
		if (init != null && init.isPresent())
		{
			initial = transformer.transformExpression(generator, init.expression);
		}
		return generator.newDataDeclaration(false, name, null, newType, initial, this);
	}
}
