// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.C.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using C_Variable = com.eagle.programmar.C.C_Variable;
	using C_Identifier_Reference = com.eagle.programmar.C.Symbols.C_Identifier_Reference;
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

	public class C_VariableExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.C_Variable variable;
		public C_Variable variable;

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(variable);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression subscrExpr = null;
			if (variable.subscript != null && variable.subscript.size() > 0)
			{
				subscrExpr = transformer.transformExpression(generator, variable.subscript.first().expr);
			}
			AbstractToken which = variable.firstId.getWhich();
			if (!(which is C_Identifier_Reference))
			{
				throw new Exception("Unable to handle " + which);
			}
			C_Identifier_Reference idRef = (C_Identifier_Reference) which;
			string varName = idRef.getValue();
			return generator.newVariableExpression(varName, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscrExpr, this);
		}
	}

}
