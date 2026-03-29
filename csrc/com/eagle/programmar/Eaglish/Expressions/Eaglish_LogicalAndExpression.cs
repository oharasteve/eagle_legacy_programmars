// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2024

namespace com.eagle.programmar.Eaglish.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eaglish_Expression = com.eagle.programmar.Eaglish.Eaglish_Expression;
	using Eaglish_Keyword = com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Eaglish_LogicalAndExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Eaglish.Eaglish_Expression left = new com.eagle.programmar.Eaglish.Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
		public Eaglish_Expression left = new Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword andOperator = new com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword("AND");
		public Eaglish_Keyword andOperator = new Eaglish_Keyword("AND");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Eaglish.Eaglish_Expression right = new com.eagle.programmar.Eaglish.Eaglish_Expression(this, AllowedPrecedence.HIGHER);
		public Eaglish_Expression right = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);

		public override void interpret(EagleInterpreter interpreter)
		{
			bool leftValue = interpreter.getBoolValue(left);
			if (!leftValue)
			{
				// Short circuit operation. Don't bother with RHS
				interpreter.pushBool(false);
				return;
			}
			bool rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(rightValue);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			return generator.newLogicalAndExpression(leftExpr, rightExpr, this);
		}
	}
}
