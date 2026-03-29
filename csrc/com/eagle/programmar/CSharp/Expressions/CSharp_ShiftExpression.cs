// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.CSharp.Expressions
{
	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_Generator = com.eagle.programmar.CSharp.CSharp_Generator;
	using CSharp_PunctuationChoice = com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using ShiftEnum = com.eagle.transform.EagleGenerator.ShiftEnum;

	public class CSharp_ShiftExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.CSharp_Expression left = new com.eagle.programmar.CSharp.CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice operator = new com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice("<<", ">>", ">>>");
		public CSharp_PunctuationChoice @operator = new CSharp_PunctuationChoice("<<", ">>", ">>>");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CSharp.CSharp_Expression right = new com.eagle.programmar.CSharp.CSharp_Expression(this, AllowedPrecedence.HIGHER);
		public CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);

		public static CSharp_Expression generateShift(CSharp_Expression leftExpr, ShiftEnum shift, CSharp_Expression rightExpr, AbstractToken source)
		{
			CSharp_ShiftExpression shiftExpr = new CSharp_ShiftExpression();
			string oper;
			switch (shift)
			{
			case LEFT:
				oper = "<<";
				break;
			case RIGHT:
				oper = ">>";
				break;
			case RIGHTSIGNEXTEND:
				oper = ">>>";
				break;
			default:
				return null;
			}

			shiftExpr.left = leftExpr;
			shiftExpr.right = rightExpr;
			shiftExpr.@operator = new CSharp_PunctuationChoice(oper);
			shiftExpr.setTransformationSource(source);
			return CSharp_Generator.wrapExpression(shiftExpr);
		}
	}

}
