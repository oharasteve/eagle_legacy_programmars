// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.Java.Expressions
{
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_PunctuationChoice = com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using ShiftEnum = com.eagle.transform.EagleGenerator.ShiftEnum;

	public class Java_ShiftExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Java_Expression left = new com.eagle.programmar.Java.Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Java.Terminals.Java_PunctuationChoice operator = new com.eagle.programmar.Java.Terminals.Java_PunctuationChoice(">>>", "<<", ">>");
		public Java_PunctuationChoice @operator = new Java_PunctuationChoice(">>>", "<<", ">>");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Java.Java_Expression right = new com.eagle.programmar.Java.Java_Expression(this, AllowedPrecedence.HIGHER);
		public Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);

		public static Java_Expression generateShift(Java_Expression leftExpr, ShiftEnum shift, Java_Expression rightExpr, AbstractToken source)
		{
			Java_ShiftExpression shiftExpr = new Java_ShiftExpression();
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
			shiftExpr.@operator = new Java_PunctuationChoice(oper);
			shiftExpr.setTransformationSource(source);
			return Java_Generator.wrapExpression(shiftExpr);
		}
	}

}
