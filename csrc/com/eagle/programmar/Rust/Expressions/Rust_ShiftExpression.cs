// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Rust.Expressions
{
	using Rust_Expression = com.eagle.programmar.Rust.Rust_Expression;
	using Rust_Generator = com.eagle.programmar.Rust.Rust_Generator;
	using Rust_PunctuationChoice = com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using ShiftEnum = com.eagle.transform.EagleGenerator.ShiftEnum;

	public class Rust_ShiftExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Rust_Expression left = new com.eagle.programmar.Rust.Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice operator = new com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice(">>>", "<<", ">>");
		public Rust_PunctuationChoice @operator = new Rust_PunctuationChoice(">>>", "<<", ">>");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Rust.Rust_Expression right = new com.eagle.programmar.Rust.Rust_Expression(this, AllowedPrecedence.HIGHER);
		public Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);

		public static Rust_Expression generateShift(Rust_Expression leftExpr, ShiftEnum oper, Rust_Expression rightExpr, AbstractToken source)
		{
			Rust_ShiftExpression shift = new Rust_ShiftExpression();
			string op;
			switch (oper)
			{
			case LEFT:
				op = "<<";
				break;
			case RIGHT:
				op = ">>";
				break;
			case RIGHTSIGNEXTEND:
				op = ">>>";
				break;
			default:
				return null;
			}

			shift.left = leftExpr;
			shift.right = rightExpr;
			shift.@operator = new Rust_PunctuationChoice(op);
			shift.setTransformationSource(source);
			return Rust_Generator.wrapExpression(shift);
		}
	}

}
