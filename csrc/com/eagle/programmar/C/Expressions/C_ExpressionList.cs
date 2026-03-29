// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.C.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using C_ArgumentList = com.eagle.programmar.C.C_ArgumentList;
	using C_MoreArgument = com.eagle.programmar.C.C_ArgumentList.C_MoreArgument;
	using C_Expression = com.eagle.programmar.C.C_Expression;
	using C_Comment = com.eagle.programmar.C.Terminals.C_Comment;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class C_ExpressionList : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
		public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT C_ArgumentList valueList;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT C_Comment comment;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
		public PunctuationRightBrace rightBrace;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleArray array = new EagleArray();
			EagleValue val = interpreter.getEagleValue(valueList.arg.getWhich());
			array.addValue(val);
			foreach (C_ArgumentList.C_MoreArgument arg in valueList.moreArgs._elements)
			{
				val = interpreter.getEagleValue(arg.arg);
				array.addValue(val);
			}
			interpreter.pushEagleValue(array);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractExpression> exprs = new List<AbstractExpression>();
			if (valueList != null && valueList.isPresent())
			{
				// First arg
				AbstractToken which1 = valueList.arg.getWhich();
				if (which1 is C_Expression)
				{
					C_Expression expr1 = (C_Expression) which1;
					AbstractExpression newExpr1 = transformer.transformExpression(generator, expr1);
					exprs.Add(newExpr1);
				}

				// Remaining args
				if (valueList.moreArgs != null && valueList.moreArgs.size() > 0)
				{
					foreach (C_ArgumentList.C_MoreArgument more in valueList.moreArgs._elements)
					{
						AbstractToken which2 = more.arg.getWhich();
						if (which2 is C_Expression)
						{
							C_Expression expr2 = (C_Expression) which2;
							AbstractExpression newExpr2 = transformer.transformExpression(generator, expr2);
							exprs.Add(newExpr2);
						}
					}
				}
			}
			return generator.newArrayExpression(exprs, this);
		}
	}

}
