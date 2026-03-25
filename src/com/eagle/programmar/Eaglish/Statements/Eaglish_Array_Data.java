// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Variable_Definition;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Eaglish_Array_Data extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) Eaglish_Keyword ARRAY = new Eaglish_Keyword("ARRAY");
	public @S(20) Eaglish_Variable_Definition var;
	public @S(30) @OPT Eaglish_Array_InitialValues init;
	public @S(40) Eaglish_EndOfLine eoln;

	public static class Eaglish_Array_InitialValues extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) SeparatedList<Eaglish_Expression, PunctuationComma> values;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		ArrayList<EagleValue> vals = null;
		if (init.isPresent())
		{
			vals = new ArrayList<EagleValue>();
			for (int i = 0; i < init.values.getPrimaryCount(); i++)
			{
				Eaglish_Expression expr = init.values.getPrimaryElement(i);
				String val = interpreter.getStrValue(expr);
				vals.add(new EagleString(val));
			}
		}

		EagleArray array = new EagleArray();
		array.setValues(vals);
		interpreter.setSymbol(var, var.toString(), array);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractType newType = generator.transformType(TypeEnum.ARRAY, null, ARRAY);
		String name = var.getValue();
		AbstractExpression initial = null;
		if (init != null && init.isPresent())
		{
			ArrayList<AbstractExpression> vals = new ArrayList<AbstractExpression>();
			int numVals = init.values.getPrimaryCount();
			for (int i = 0; i < numVals; i++)
			{
				Eaglish_Expression expr = init.values.getPrimaryElement(i);
				AbstractExpression next = transformer.transformExpression(generator, expr);
				vals.add(next);
			}
			initial = generator.newArrayExpression(vals, ARRAY);
		}
		return generator.newDataDeclaration(false, name, null, newType, initial, this);
	}
}
