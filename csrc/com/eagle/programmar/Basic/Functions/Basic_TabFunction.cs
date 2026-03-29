// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Text;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 13, 2025

namespace com.eagle.programmar.Basic.Functions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Basic_Expression = com.eagle.programmar.Basic.Basic_Expression;
	using Basic_Keyword = com.eagle.programmar.Basic.Terminals.Basic_Keyword;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Basic_TabFunction : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Basic.Terminals.Basic_Keyword TAB = new com.eagle.programmar.Basic.Terminals.Basic_Keyword("TAB");
		public Basic_Keyword TAB = new Basic_Keyword("TAB");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Basic.Basic_Expression expr;
		public Basic_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public override void interpret(EagleInterpreter interpreter)
		{
			// Wonder why it is negative in Banner.bas? 3*E - 35 when E is 5 or 6
			int tab = Math.Abs(interpreter.getIntValue(expr));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < tab; i++)
			{
				sb.Append(' ');
			}
			interpreter.pushStr(sb.ToString());
		}
	}

}
