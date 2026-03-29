// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;
using System.Text;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 7, 2024

namespace com.eagle.programmar.Fortran
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleValue = com.eagle.math.EagleValue;
	using Oper1Types = com.eagle.metrics.Operator1Metrics.Oper1Types;
	using Fortran_Variable_Reference = com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Fortran_Format
	{
		public static string format(EagleInterpreter interpreter, string format, SeparatedList<Fortran_Variable_Reference, PunctuationComma> parameters, List<EagleGenerator.TypeEnum> argTypes)
		{
			StringBuilder sb = new StringBuilder();
			if (format.Equals("'(I5)'"))
			{
				Fortran_Variable_Reference var = parameters.first();
				EagleValue val = interpreter.findSymbol(var.getValue());
				argTypes.Add(EagleGenerator.TypeEnum.INTEGER);
				int num = val.forceIntegerValue();
				sb.Append(string.Format("{0,5:D}", Convert.ToInt32(num))); // Boxing stinks in Java
			}
			else
			{
				throw new Exception("Need to implement Fortran format: " + format);
			}
			return sb.ToString();
		}

		public static AbstractExpression transform(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, string format, SeparatedList<Fortran_Variable_Reference, PunctuationComma> args, List<EagleGenerator.TypeEnum> metrics)
		{
			if (!format.Equals("'(I5)'"))
			{
				throw new Exception("Need to implement Fortran format: " + format);
			}

			Oper1Types valType = null;
			if (metrics != null)
			{
				valType = new Oper1Types();
				valType._type1 = metrics[0];
			}

			AbstractExpression newExpr = generator.newVariableExpression(args.first().getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ONE, null, args);
			return generator.newStringFunction(valType, newExpr, null);
		}
	}

}
