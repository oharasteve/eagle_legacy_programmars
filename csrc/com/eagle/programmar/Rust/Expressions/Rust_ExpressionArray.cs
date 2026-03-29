// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Rust.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using Rust_Expression = com.eagle.programmar.Rust.Rust_Expression;
	using Rust_Generator = com.eagle.programmar.Rust.Rust_Generator;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationAmpersand = com.eagle.tokens.punctuation.PunctuationAmpersand;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Rust_ExpressionArray : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationAmpersand ampersand;
		public PunctuationAmpersand ampersand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
		public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<com.eagle.programmar.Rust.Rust_Expression, com.eagle.tokens.punctuation.PunctuationComma> values;
		public SeparatedList<Rust_Expression, PunctuationComma> values;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
		public PunctuationRightBracket rightBracket;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleArray vals = new EagleArray();
			for (int i = 0; i < values.getPrimaryCount(); i++)
			{
				Rust_Expression expr = values.getPrimaryElement(i);
				EagleValue val = interpreter.getEagleValue(expr);
				vals.addValue(val);
			}

			interpreter.pushEagleValue(vals);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractExpression> exprs = new List<AbstractExpression>();
			int nValues = values.getPrimaryCount();
			for (int i = 0; i < nValues; i++)
			{
				Rust_Expression expr = values.getPrimaryElement(i);
				AbstractExpression newExpr = transformer.transformExpression(generator, expr);
				exprs.Add(newExpr);
			}
			return generator.newArrayExpression(exprs, this);
		}

		public static Rust_Expression generateArray(List<AbstractExpression> exprs, AbstractToken source)
		{
			Rust_ExpressionArray brack = new Rust_ExpressionArray();
			brack.ampersand = new PunctuationAmpersand();
			brack.leftBracket = new PunctuationLeftBracket();
			brack.rightBracket = new PunctuationRightBracket();
			brack.values = new SeparatedList<Rust_Expression, PunctuationComma>();
			brack.values.setPresent(true);

			for (int i = 0; i < exprs.Count; i++)
			{
				if (i > 0)
				{
					brack.values.addSecondaryElement(new PunctuationComma());
				}
				brack.values.addPrimaryElement((Rust_Expression) exprs[i]);
			}

			brack.setTransformationSource(source);
			return Rust_Generator.wrapExpression(brack);
		}
	}

}
