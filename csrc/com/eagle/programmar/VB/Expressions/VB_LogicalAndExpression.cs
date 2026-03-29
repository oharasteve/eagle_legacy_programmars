// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.VB.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using VB_Expression = com.eagle.programmar.VB.VB_Expression;
	using VB_KeywordChoice = com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class VB_LogicalAndExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.VB.VB_Expression left = new com.eagle.programmar.VB.VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("operators/logical-bitwise-operators") com.eagle.programmar.VB.Terminals.VB_KeywordChoice andOperator = new com.eagle.programmar.VB.Terminals.VB_KeywordChoice("and", "andalso");
		public @DOC("operators/logical-bitwise-operators") VB_KeywordChoice andOperator = new VB_KeywordChoice("and", "andalso");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.VB.VB_Expression right = new com.eagle.programmar.VB.VB_Expression(this, AllowedPrecedence.HIGHER);
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);

		public void interpret(EagleInterpreter interpreter)
		{
			bool leftValue = interpreter.getBoolValue(left);
			string oper = andOperator.getValue();
			switch (oper)
			{
			case "andalso":
				if (!leftValue)
				{
					// Short circuit operation. Don't bother with RHS
					interpreter.pushBool(false);
					return;
				}
				bool rightValue = interpreter.getBoolValue(right);
				interpreter.pushBool(rightValue);
				return;
			case "and":
				bool rightVal = interpreter.getBoolValue(right);
				interpreter.pushBool(leftValue && rightVal);
				return;
			default:
				throw new Exception("Unable to handle " + oper + " in VB_ConditionalAndExpression");
			}
		}

		public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			return generator.newLogicalAndExpression(leftExpr, rightExpr, this);
		}
	}

}
