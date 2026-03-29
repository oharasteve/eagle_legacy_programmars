// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.AWK.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using AWK_Variable = com.eagle.programmar.AWK.AWK_Variable;
	using AWK_VarSubscript = com.eagle.programmar.AWK.AWK_Variable.AWK_VarSubscript;
	using AWK_Number = com.eagle.programmar.AWK.Terminals.AWK_Number;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using BuiltInEnum = com.eagle.transform.EagleGenerator.BuiltInEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class AWK_VariableExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.AWK.AWK_Variable variable;
		public AWK_Variable variable;

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(variable);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression subscrExpr = null;
			if (variable.subscripts != null && variable.subscripts.size() == 1)
			{
				AWK_Variable.AWK_VarSubscript varSub = variable.subscripts.first();
				AbstractToken which = varSub.expr.getWhich();
				if (which is AWK_Number)
				{
					AWK_Number number = (AWK_Number) which;
					subscrExpr = generator.newNumberExpression(number.getValue(), varSub);
				}
				else if (which is AWK_VariableExpression)
				{
					AWK_VariableExpression varExpr = (AWK_VariableExpression) which;
					subscrExpr = generator.newVariableExpression(varExpr.variable.id.getValue(), EagleGenerator.SubscriptEnum.IT_IS_A_HASHMAP, null, varSub);
				}
				else
				{
					throw new Exception("Unexpected subscript: " + which);
				}
			}

			string name = variable.id.getValue();
			if (name.Equals("true", StringComparison.OrdinalIgnoreCase))
			{
				return generator.newBuiltInExpression(EagleGenerator.BuiltInEnum.TRUE, variable);
			}
			if (name.Equals("false", StringComparison.OrdinalIgnoreCase))
			{
				return generator.newBuiltInExpression(EagleGenerator.BuiltInEnum.FALSE, variable);
			}
			return generator.newVariableExpression(name, EagleGenerator.SubscriptEnum.IT_IS_A_HASHMAP, subscrExpr, this);
		}
	}

}
