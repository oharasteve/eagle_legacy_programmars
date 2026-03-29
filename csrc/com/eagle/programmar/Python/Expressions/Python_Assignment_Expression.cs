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
	using Python_Variable = com.eagle.programmar.Python.Python_Variable;
	using Python_Identifier_Reference = com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using Python_PunctuationChoice = com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;

	public class Python_Assignment_Expression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Python_Expression left = new com.eagle.programmar.Python.Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Terminals.Python_PunctuationChoice operator = new com.eagle.programmar.Python.Terminals.Python_PunctuationChoice("=", "+=", "-=", ":=");
		public Python_PunctuationChoice @operator = new Python_PunctuationChoice("=", "+=", "-=", ":=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Python_Keyword AWAIT = new com.eagle.programmar.Python.Terminals.Python_Keyword("await");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Python.Python_Expression right = new com.eagle.programmar.Python.Python_Expression(this, AllowedPrecedence.HIGHER);
		public Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);

		public static Python_Expression generateAssignment(Python_Variable variable, Python_Expression subscript, AssignmentEnum oper, Python_Expression expression, AbstractToken source)
		{
			Python_Assignment_Expression asgExpr = new Python_Assignment_Expression();
			string punct;
			switch (oper)
			{
			case EQUALS:
				punct = "=";
				break;
			case PLUS_EQUALS:
				punct = "+=";
				break;
			case MINUS_EQUALS:
				punct = "-=";
				break;
			default:
				throw new Exception("Unexpected assignment operator: " + oper);
			}

			AbstractToken which = variable.var.getWhich();
			if (!(which is Python_Identifier_Reference))
			{
				throw new Exception("Unable to handle " + which);
			}
			Python_Identifier_Reference id = (Python_Identifier_Reference) which;

			asgExpr.left = Python_VariableExpression.generateVariableExpression(id.getValue(), SubscriptEnum.FIRST_IS_ZERO, subscript, source);
			asgExpr.@operator.setValue(punct);
			asgExpr.right = expression;
			asgExpr.setTransformationSource(source);
			return Python_Generator.wrapExpression(asgExpr);
		}
	}

}
