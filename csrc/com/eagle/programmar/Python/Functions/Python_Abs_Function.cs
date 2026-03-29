// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 6, 2026

namespace com.eagle.programmar.Python.Functions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_Parenthesized_Expression = com.eagle.programmar.Python.Expressions.Python_Parenthesized_Expression;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Python_Abs_Function : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Terminals.Python_Keyword ABS = new com.eagle.programmar.Python.Terminals.Python_Keyword("abs");
		public Python_Keyword ABS = new Python_Keyword("abs");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE Python_Expression expression;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;

		public override void interpret(EagleInterpreter interpreter)
		{
			double x = interpreter.getDoubleValue(expression);
			interpreter.pushDouble(Math.Abs(x));
		}

		public static Python_Expression generateAbsFunc(Python_Expression expr, AbstractToken source)
		{
			Python_Abs_Function absFn = new Python_Abs_Function();
			absFn.leftParen = new PunctuationLeftParen();
			absFn.rightParen = new PunctuationRightParen();
			if (expr.getWhich() is Python_Parenthesized_Expression)
			{
				// Don't create a second set of parens
				Python_Parenthesized_Expression parens = (Python_Parenthesized_Expression) expr.getWhich();
				absFn.expression = parens.list.expr;
			}
			else
			{
				absFn.expression = expr;
			}

			absFn.setTransformationSource(source);
			return Python_Generator.wrapExpression(absFn);
		}
	}

}
