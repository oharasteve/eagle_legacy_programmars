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
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_PunctuationChoice = com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using ShiftEnum = com.eagle.transform.EagleGenerator.ShiftEnum;

	public class Python_Shift_Expression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Python_Expression left = new com.eagle.programmar.Python.Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Terminals.Python_PunctuationChoice operator = new com.eagle.programmar.Python.Terminals.Python_PunctuationChoice("<<", ">>");
		public Python_PunctuationChoice @operator = new Python_PunctuationChoice("<<", ">>");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Python.Python_Expression right = new com.eagle.programmar.Python.Python_Expression(this, AllowedPrecedence.HIGHER);
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);

		public static Python_Expression generateShift(Python_Expression leftExpr, ShiftEnum shift, Python_Expression rightExpr, AbstractToken source)
		{
			Python_Shift_Expression shiftExpr = new Python_Shift_Expression();
			string oper;
			switch (shift)
			{
			case LEFT:
				oper = "<<";
				break;
			case RIGHT:
				oper = ">>";
				break;
			default:
				throw new Exception("Unable to handle shift operator: " + shift);
			}

			shiftExpr.left = leftExpr;
			shiftExpr.right = rightExpr;
			shiftExpr.@operator = new Python_PunctuationChoice(oper);
			shiftExpr.setTransformationSource(source);
			return Python_Generator.wrapExpression(shiftExpr);
		}
	}

}
