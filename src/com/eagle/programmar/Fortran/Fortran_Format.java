// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 7, 2024

package com.eagle.programmar.Fortran;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator1Metrics.Oper1Types;
import com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformer;

public class Fortran_Format
{
	public static String format(EagleInterpreter interpreter, String format,
			SeparatedList<Fortran_Variable_Reference, PunctuationComma> parameters,
			ArrayList<String> argTypes)
	{
		StringBuffer sb = new StringBuffer();
		if (format.equals("'(I5)'"))
		{
			Fortran_Variable_Reference var = parameters.first();
			EagleValue val = interpreter.findSymbol(var.getValue());
			argTypes.add(EagleInteger.INTEGER);
			int num = val.forceIntegerValue();
			sb.append(String.format("%5d", Integer.valueOf(num))); // Boxing stinks in Java
		}
		else
		{
			throw new RuntimeException("Need to implement Fortran format: " + format);
		}
		return sb.toString();
	}

	public static AbstractExpression transform(EagleTransformer transformer, EagleGenerator generator,
			String format, SeparatedList<Fortran_Variable_Reference, PunctuationComma> args, ArrayList<String> metrics)
	{
		if (!format.equals("'(I5)'"))
		{
			throw new RuntimeException("Need to implement Fortran format: " + format);
		}

		Oper1Types valType = null;
		if (metrics != null)
		{
			valType = new Oper1Types();
			valType._type1 = metrics.get(0);
		}

		AbstractExpression newExpr = generator.newVariableExpression(args.first().getValue(),
				SubscriptEnum.FIRST_IS_ONE, null, args);
		return generator.newStringFunction(valType, newExpr, null);
	}
}
