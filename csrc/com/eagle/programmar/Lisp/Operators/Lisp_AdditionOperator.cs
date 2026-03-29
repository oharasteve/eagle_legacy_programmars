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

	public class Lisp_AdditionOperator : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Lisp.Terminals.Lisp_PunctuationChoice PLUS = new com.eagle.programmar.Lisp.Terminals.Lisp_PunctuationChoice("+", "++", "+++", "-");
		public Lisp_PunctuationChoice PLUS = new Lisp_PunctuationChoice("+", "++", "+++", "-");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.Lisp.Lisp_Expression> exprs;
		public TokenList<Lisp_Expression> exprs;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public override void interpret(EagleInterpreter interpreter)
		{
			switch (PLUS.getValue())
			{
			case "+":
				int sum = 0;
				foreach (Lisp_Expression expr in exprs._elements)
				{
					sum += interpreter.getIntValue(expr);
				}
				interpreter.pushInt(sum);
				return;
			case "-":
				int diff = 0;
				bool first = true;
				if (exprs._elements.size() == 1)
				{
					// Special case, (- 5) is -5, not 5
					diff = -interpreter.getIntValue(exprs.first());
				}
				else
				{
					foreach (Lisp_Expression expr in exprs._elements)
					{
						if (first)
						{
							diff = interpreter.getIntValue(expr);
							first = false;
						}
						else
						{
							diff -= interpreter.getIntValue(expr);
						}
					}
				}

				interpreter.pushInt(diff);
				return;
			default:
				throw new Exception("Unable to handle operator: " + PLUS.getValue());
			}
		}
	}

}
