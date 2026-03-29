// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Python.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_Punctuation = com.eagle.programmar.Python.Terminals.Python_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Python_Power_Expression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Python_Expression left = new com.eagle.programmar.Python.Python_Expression(this, AllowedPrecedence.HIGHER);
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Terminals.Python_Punctuation stars = new com.eagle.programmar.Python.Terminals.Python_Punctuation("**");
		public Python_Punctuation stars = new Python_Punctuation("**");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Python.Python_Expression right = new com.eagle.programmar.Python.Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.ATLEAST);

		public override void interpret(EagleInterpreter interpreter)
		{
			double leftValue = interpreter.getDoubleValue(left);
			double rightValue = interpreter.getDoubleValue(right);
			interpreter.pushDouble(Math.Pow(leftValue, rightValue));
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			return generator.newExponentExpression(leftExpr, rightExpr, this);
		}

		public static Python_Expression generateExpression(AbstractExpression leftExpr, AbstractExpression rightExpr, AbstractToken source)
		{
			Python_Power_Expression expr = new Python_Power_Expression();
			expr.left = (Python_Expression) leftExpr;
			expr.right = (Python_Expression) rightExpr;
			expr.setTransformationSource(source);
			return Python_Generator.wrapExpression(expr);
		}
	}
}
