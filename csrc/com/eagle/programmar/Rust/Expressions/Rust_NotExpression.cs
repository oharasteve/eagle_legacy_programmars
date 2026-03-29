// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Rust.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
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

	/* In Rust, the bitwise NOT operator is the exclamation mark !.
	 * This is unlike many other languages (like C and C++) where the tilde ~ symbol is used. 
	 * The ! operator is used for both bitwise negation on integers and logical negation on boolean values.
	 * Rust's strong type system prevents ambiguity, as an integer will always be treated with bitwise NOT,
	 * and a boolean with logical NOT. 
	*/

	public class Rust_NotExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Terminals.Rust_Punctuation notOperator = new com.eagle.programmar.Rust.Terminals.Rust_Punctuation('!');
		public Rust_Punctuation notOperator = new Rust_Punctuation('!');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rust.Rust_Expression expr;
		public Rust_Expression expr;

		public override void interpret(EagleInterpreter interpreter)
		{
			bool value = interpreter.getBoolValue(expr);
			interpreter.pushBool(!value);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression theExpr = transformer.transformExpression(generator, expr);
			return generator.newLogicalNotExpression(theExpr, this);
		}

		public static Rust_Expression generateNot(Rust_Expression theExpr, AbstractToken source)
		{
			Rust_NotExpression not = new Rust_NotExpression();
			not.expr = theExpr;
			not.setTransformationSource(source);
			return Rust_Generator.wrapExpression(not);
		}
	}

}
