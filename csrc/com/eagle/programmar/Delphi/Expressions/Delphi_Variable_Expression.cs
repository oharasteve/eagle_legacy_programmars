// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Delphi.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Delphi_Variable = com.eagle.programmar.Delphi.Delphi_Variable;
	using Delphi_Subscript = com.eagle.programmar.Delphi.Delphi_Variable.Delphi_Subscript;
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

	public class Delphi_Variable_Expression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Delphi.Delphi_Variable variable;
		public Delphi_Variable variable;

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(variable);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression subscript = null;
			if (variable.extensions != null && variable.extensions.size() > 0)
			{
				AbstractToken first = variable.extensions.first().getWhich();
				if (first is Delphi_Variable.Delphi_Subscript)
				{
					Delphi_Variable.Delphi_Subscript sub = (Delphi_Variable.Delphi_Subscript) first;
					subscript = transformer.transformExpression(generator, sub.exprs.first());
				}
			}
			// Actually, this depends on how the array is defined: Array[0..9] of String
			return generator.newVariableExpression(variable.var.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscript, this);
		}
	}

}
