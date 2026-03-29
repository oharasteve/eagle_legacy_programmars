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
	using EagleValue = com.eagle.math.EagleValue;
	using Lisp_Expression = com.eagle.programmar.Lisp.Lisp_Expression;
	using Lisp_KeywordChoice = com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
	using Lisp_PunctuationChoice = com.eagle.programmar.Lisp.Terminals.Lisp_PunctuationChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Lisp_RelationalOperator : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Lisp_RelOp relOp;
		public Lisp_RelOp relOp;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.Lisp.Lisp_Expression> exprs;
		public TokenList<Lisp_Expression> exprs;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public class Lisp_RelOp : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_PunctuationChoice XXLESS = new com.eagle.programmar.Lisp.Terminals.Lisp_PunctuationChoice(">", ">=", "=", "/=", "<", "<=");
			public Lisp_PunctuationChoice XXLESS = new Lisp_PunctuationChoice(">", ">=", "=", "/=", "<", "<=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_KeywordChoice XXEQUAL = new com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice("EQ", "EQUAL");
			public Lisp_KeywordChoice XXEQUAL = new Lisp_KeywordChoice("EQ", "EQUAL");
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue firstValue = interpreter.getEagleValue(exprs._elements.get(0));
			bool first = true;

			// String compare
			if (firstValue.isString())
			{
				string prevStr = firstValue.forceStringValue();
				foreach (Lisp_Expression expr in exprs._elements)
				{
					if (first)
					{
						first = false;
					}
					else
					{
						string currStr = interpreter.getStrValue(expr);
						switch (relOp.getWhich().ToString())
						{
						case "=", "EQ", "EQUAL":
							if (!currStr.Equals(prevStr))
							{
								interpreter.pushBool(false);
								return;
							}
							break;
						default:
							throw new Exception("Unable to handle string operator: " + relOp.getWhich());
						}
						prevStr = currStr;
					}
				}
				interpreter.pushBool(true);
				return;
			}

			// Integer compare
			int previous = firstValue.forceIntegerValue();
			foreach (Lisp_Expression expr in exprs._elements)
			{
				if (first)
				{
					first = false;
				}
				else
				{
					int current = interpreter.getIntValue(expr);
					bool test;
					switch (relOp.getWhich().ToString())
					{
					case "<":
						test = previous < current;
						break;
					case "<=":
						test = previous <= current;
						break;
					case "=", "EQ", "EQUAL":
						test = previous == current;
						break;
					case "/=":
						test = previous != current;
						break;
					case ">=":
						test = previous >= current;
						break;
					case ">":
						test = previous > current;
						break;
					default:
						throw new Exception("Unable to handle int operator: " + relOp.getWhich());
					}

					if (!test)
					{
						interpreter.pushBool(false);
						return;
					}
					previous = current;
				}
			}
			interpreter.pushBool(true);
		}
	}

}
