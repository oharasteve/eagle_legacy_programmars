// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 22, 2024

namespace com.eagle.programmar.Rust.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Rust_Expression = com.eagle.programmar.Rust.Rust_Expression;
	using Rust_Generator = com.eagle.programmar.Rust.Rust_Generator;
	using Rust_Punctuation = com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Rust_BorrowExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Terminals.Rust_Punctuation ampersand = new com.eagle.programmar.Rust.Terminals.Rust_Punctuation('&');
		public Rust_Punctuation ampersand = new Rust_Punctuation('&');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE Rust_Expression expression;
		public  NOSPACE;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.getEagleValue(expression);
			interpreter.pushEagleValue(value);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			return transformer.transformExpression(generator, expression);
		}

		public static Rust_Expression generateBorrow(Rust_Expression expr, AbstractToken source)
		{
			if (expr.getWhich() is Rust_BorrowExpression)
			{
				// Unless you want two of them, as in &&x
				return expr;
			}

			Rust_BorrowExpression borrow = new Rust_BorrowExpression();
			borrow.expression = expr;

			borrow.setTransformationSource(source);
			return Rust_Generator.wrapExpression(borrow);
		}
	}

}
