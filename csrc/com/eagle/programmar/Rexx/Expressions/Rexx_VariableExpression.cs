// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

namespace com.eagle.programmar.Rexx.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Rexx_Variable = com.eagle.programmar.Rexx.Rexx_Variable;
	using Rexx_Number = com.eagle.programmar.Rexx.Terminals.Rexx_Number;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Rexx_VariableExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rexx.Rexx_Variable variable;
		public Rexx_Variable variable;

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(variable);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression subscrExpr = null;
			if (variable.subscript != null && variable.subscript.isPresent())
			{
				AbstractToken which = variable.subscript.subscr.getWhich();
				if (which is Rexx_Number)
				{
					Rexx_Number number = (Rexx_Number) which;
					subscrExpr = generator.newNumberExpression(number.getValue(), variable.subscript.subscr);
				}
				else if (which is Rexx_Variable)
				{
					Rexx_Variable var = (Rexx_Variable) which;
					subscrExpr = generator.newVariableExpression(var.var.getValue(), EagleGenerator.SubscriptEnum.IT_IS_A_HASHMAP, null, variable.subscript.subscr);
				}
				else
				{
					throw new Exception("Unexpected subscript: " + which);
				}
			}
			return generator.newVariableExpression(variable.var.getValue(), EagleGenerator.SubscriptEnum.IT_IS_A_HASHMAP, subscrExpr, this);
		}
	}

}
