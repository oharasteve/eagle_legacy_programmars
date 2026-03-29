// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Javascript.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Javascript_Subscript = com.eagle.programmar.Javascript.Javascript_Subscript;
	using Javascript_Variable = com.eagle.programmar.Javascript.Javascript_Variable;
	using Javascript_VariableQualifier = com.eagle.programmar.Javascript.Javascript_Variable.Javascript_VariableQualifier;
	using Javascript_Identifier_Reference = com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
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

	public class Javascript_VariableExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Javascript.Javascript_Variable variable;
		public Javascript_Variable variable;

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(variable);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression newSub = null;
			if (variable.qualifiers != null && variable.qualifiers.size() == 1)
			{
				Javascript_Variable.Javascript_VariableQualifier qual = variable.qualifiers.first();
				if (qual.getWhich() is Javascript_Subscript)
				{
					Javascript_Subscript sub = (Javascript_Subscript) qual.getWhich();
					newSub = transformer.transformExpression(generator, sub.expr);
				}
			}

			AbstractToken which = variable.firstId.getWhich();
			if (!(which is Javascript_Identifier_Reference))
			{
				throw new Exception("Cannot handle variable: " + which);
			}
			Javascript_Identifier_Reference id = (Javascript_Identifier_Reference) which;
			return generator.newVariableExpression(id.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, newSub, this);
		}
	}

}
