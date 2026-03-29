// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 5, 2025

namespace com.eagle.programmar.Python.Methods
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_Variable = com.eagle.programmar.Python.Python_Variable;
	using Python_VariableExpression = com.eagle.programmar.Python.Expressions.Python_VariableExpression;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Python_EndsWith_Method : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Python_Variable string;
		public Python_Variable @string;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationPeriod dot;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE Python_Keyword ENDSWITH = new com.eagle.programmar.Python.Terminals.Python_Keyword("endswith");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE Python_Expression pattern;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;

		public override void interpret(EagleInterpreter interpreter)
		{
			string str = interpreter.getStrValue(@string);
			string patt = interpreter.getStrValue(pattern);
			interpreter.pushBool(str.EndsWith(patt, StringComparison.Ordinal));
		}

		public static Python_Expression generateEndsWith(Python_Expression expr, Python_Expression patt, AbstractToken source)
		{
			Python_EndsWith_Method endsFunc = new Python_EndsWith_Method();
			AbstractToken token = expr.getWhich();
			if (!(token is Python_VariableExpression))
			{
				throw new Exception("Python endswith must be a variable, not " + token);
			}

			Python_VariableExpression varExpr = (Python_VariableExpression) token;
			endsFunc.@string = varExpr.variable;
			endsFunc.dot = new PunctuationPeriod();
			endsFunc.leftParen = new PunctuationLeftParen();
			endsFunc.pattern = patt;
			endsFunc.rightParen = new PunctuationRightParen();

			endsFunc.setTransformationSource(source);
			return Python_Generator.wrapExpression(endsFunc);
		}
	}

}
