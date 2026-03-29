// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 31, 2024

namespace com.eagle.programmar.Lisp.Operators
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Lisp_Expression = com.eagle.programmar.Lisp.Lisp_Expression;
	using Lisp_PunctuationChoice = com.eagle.programmar.Lisp.Terminals.Lisp_PunctuationChoice;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Lisp_MultiplicationOperator : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Lisp.Terminals.Lisp_PunctuationChoice TIMES = new com.eagle.programmar.Lisp.Terminals.Lisp_PunctuationChoice("*", "**", "***", "/", "//", "///");
		public Lisp_PunctuationChoice TIMES = new Lisp_PunctuationChoice("*", "**", "***", "/", "//", "///");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.Lisp.Lisp_Expression> exprs;
		public TokenList<Lisp_Expression> exprs;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public override void interpret(EagleInterpreter interpreter)
		{
			switch (TIMES.getValue())
			{
			case "*":
				int product = 1;
				foreach (Lisp_Expression expr in exprs._elements)
				{
					product *= interpreter.getIntValue(expr);
				}
				interpreter.pushInt(product);
				return;
			case "/":
				int quotient = 0;
				bool first = true;
				foreach (Lisp_Expression expr in exprs._elements)
				{
					if (first)
					{
						quotient = interpreter.getIntValue(expr);
						first = false;
					}
					else
					{
						quotient /= interpreter.getIntValue(expr);
					}
				}
				interpreter.pushInt(quotient);
				return;
			default:
				throw new Exception("Unable to handle operator: " + TIMES.getValue());
			}
		}
	}

}
