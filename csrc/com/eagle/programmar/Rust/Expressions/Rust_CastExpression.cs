// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 22, 2026

namespace com.eagle.programmar.Rust.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Rust_Expression = com.eagle.programmar.Rust.Rust_Expression;
	using Rust_Generator = com.eagle.programmar.Rust.Rust_Generator;
	using Rust_Keyword = com.eagle.programmar.Rust.Terminals.Rust_Keyword;
	using Rust_KeywordChoice = com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice;
	using Rust_Punctuation = com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Rust_CastExpression : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice type = new com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice("i32", "f64");
		public Rust_KeywordChoice type = new Rust_KeywordChoice("i32", "f64");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE Rust_Punctuation colonColon = new com.eagle.programmar.Rust.Terminals.Rust_Punctuation("::");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE Rust_Keyword FROM = new com.eagle.programmar.Rust.Terminals.Rust_Keyword("from");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE Rust_Expression expression;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;

		public override void interpret(EagleInterpreter interpreter)
		{
			switch (type.getValue())
			{
			case "i32":
				int inum = interpreter.getIntValue(expression);
				interpreter.pushInt(inum);
				break;
			case "f64":
				double dnum = interpreter.getDoubleValue(expression);
				interpreter.pushDouble(dnum);
				break;
			default:
				throw new Exception("Unexpected cast function: " + type.getValue());
			}
		}

		public static Rust_Expression newCastExpression(string type, Rust_Expression expr, AbstractToken source)
		{
			Rust_CastExpression castExpr = new Rust_CastExpression();
			castExpr.type.setValue(type);
			castExpr.leftParen = new PunctuationLeftParen();
			castExpr.expression = expr;
			castExpr.rightParen = new PunctuationRightParen();

			return Rust_Generator.wrapExpression(castExpr);
		}
	}

}
