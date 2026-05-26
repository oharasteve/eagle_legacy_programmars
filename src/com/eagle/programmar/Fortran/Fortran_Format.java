// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 7, 2024

package com.eagle.programmar.Fortran;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformer;

public class Fortran_Format
{
	public static String format(EagleInterpreter interpreter, String format,
			SeparatedList<Fortran_Variable_Reference, PunctuationComma> parameters,
			ArrayList<TypeEnum> argTypes)
	{
		StringBuffer sb = new StringBuffer();
		if (format.equals("'(I5)'"))
		{
			Fortran_Variable_Reference var = parameters.first();
			EagleValue val = interpreter.findSymbol(var.getValue());
			argTypes.add(TypeEnum.INTEGER);
			int num = val.forceIntegerValue();
			sb.append(String.format("%5d", Integer.valueOf(num))); // Boxing stinks in Java
		}
		else
		{
			throw new RuntimeException("Need to implement Fortran format: " + format);
		}
		return sb.toString();
	}

	public static AbstractExpression transform(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
			String format, SeparatedList<Fortran_Variable_Reference, PunctuationComma> args,
			ArrayList<TypeEnum> metrics)
	{
		if (!format.equals("'(I5)'"))
		{
			throw new RuntimeException("Need to implement Fortran format: " + format);
		}

		TypeEnum type = TypeEnum.OTHER;
		if (metrics != null)
		{
			type = metrics.get(0);
		}

		AbstractExpression newExpr = generator.newVariableExpression(args.first().getValue(),
				SubscriptEnum.FIRST_IS_ONE, null, args);
		return generator.newStringFunction(type, newExpr, null);
	}
}
