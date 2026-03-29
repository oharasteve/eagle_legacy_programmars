// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Powershell.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using Powershell_Expression = com.eagle.programmar.Powershell.Powershell_Expression;
	using Powershell_ExpressionList = com.eagle.programmar.Powershell.Powershell_ExpressionList;
	using Powershell_MoreExpression = com.eagle.programmar.Powershell.Powershell_ExpressionList.Powershell_MoreExpression;
	using Powershell_Punctuation = com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Powershell_List : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Powershell_Punctuation at = new com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation("@");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Powershell_ExpressionList expressions;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleArray array = new EagleArray();
			if (expressions != null && expressions.isPresent())
			{
				EagleValue val = interpreter.getEagleValue(expressions.expr);
				array.addValue(val);
				for (int i = 0; i < expressions.more.size(); i++)
				{
					Powershell_ExpressionList.Powershell_MoreExpression expr = expressions.more._elements.get(i);
					val = interpreter.getEagleValue(expr.expr);
					array.addValue(val);
				}
			}
			interpreter.pushEagleValue(array);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractExpression> values = new List<AbstractExpression>();
			if (expressions != null && expressions.isPresent())
			{
				Powershell_Expression expr1 = expressions.expr;
				AbstractExpression newExpr1 = transformer.transformExpression(generator, expr1);
				values.Add(newExpr1);
				for (int i = 0; i < expressions.more.size(); i++)
				{
					Powershell_ExpressionList.Powershell_MoreExpression expr2 = expressions.more._elements.get(i);
					AbstractExpression newExpr2 = transformer.transformExpression(generator, expr2.expr);
					values.Add(newExpr2);
				}
			}
			return generator.newArrayExpression(values, this);
		}
	}

}
