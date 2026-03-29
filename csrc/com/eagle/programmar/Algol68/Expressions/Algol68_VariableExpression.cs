// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

namespace com.eagle.programmar.Algol68.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Algol68_Variable = com.eagle.programmar.Algol68.Algol68_Variable;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using SubstringECEnum = com.eagle.transform.EagleGenerator.SubstringECEnum;
	using SubstringSCEnum = com.eagle.transform.EagleGenerator.SubstringSCEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Algol68_VariableExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Algol68.Algol68_Variable variable;
		public Algol68_Variable variable;

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(variable);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression subscript = null;
			if (variable.subscript != null && variable.subscript.isPresent())
			{
				subscript = transformer.transformExpression(generator, variable.subscript.expr);
				if (variable.subscript.colonSub != null && variable.subscript.colonSub.isPresent())
				{
					AbstractExpression ecExpr = transformer.transformExpression(generator, variable.subscript.colonSub.expr2);
					AbstractExpression varExpr = generator.newVariableExpression(variable.vars.first().getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ONE, null, variable);
					return generator.newSubstringFunction(varExpr, subscript, EagleGenerator.SubstringSCEnum.FIRST_CHAR_IS_ONE, EagleGenerator.SubstringECEnum.GIVEN_EC, ecExpr, false, this);
				}
			}
			// Actually, this depends on how the array is defined: Array[0..9] of String
			return generator.newVariableExpression(variable.vars.first().getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ONE, subscript, this);
		}
	}

}
