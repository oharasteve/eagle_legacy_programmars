// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

namespace com.eagle.programmar.Basic.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Basic_Expression = com.eagle.programmar.Basic.Basic_Expression;
	using Basic_KeywordChoice = com.eagle.programmar.Basic.Terminals.Basic_KeywordChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class Basic_PrintStatement : TokenSequence, EagleRunnable, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Basic.Terminals.Basic_KeywordChoice PRINT = new com.eagle.programmar.Basic.Terminals.Basic_KeywordChoice("PRINT", "PRI");
		public Basic_KeywordChoice PRINT = new Basic_KeywordChoice("PRINT", "PRI");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Basic_PrintItem> items;
		public  OPT;

		public class Basic_PrintItem : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Basic_Expression XXexpr;
			public Basic_Expression XXexpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationSemicolon XXsemicolon;
			public PunctuationSemicolon XXsemicolon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationComma XXcomma;
			public PunctuationComma XXcomma;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			if (items != null && items.size() > 0)
			{
				AbstractToken previous = null;
				foreach (Basic_PrintItem item in items._elements)
				{
					AbstractToken which = item.getWhich();
					if (which is Basic_Expression)
					{
						Basic_Expression expr = (Basic_Expression) item.getWhich();
						EagleValue val = interpreter.getEagleValue(expr);
						if (val.isInteger())
						{
							int num = val.forceIntegerValue();
							Console.Write(" " + num + " ");
						}
						else if (val.isDouble())
						{
							double number = val.forceDoubleValue();
							Console.Write(" " + number + " ");
						}
						else // string
						{
							string piece = val.forceStringValue();
							Console.Write(piece);
						}
					}
					else if (which is PunctuationSemicolon)
					{
						// No padding
					}
					else if (which is PunctuationComma)
					{
						Console.Write("    ");
					}
					else
					{
						throw new Exception("Unexpected PRINT item: " + which);
					}
					previous = which;
				}

				if (!(previous is PunctuationSemicolon))
				{
					Console.WriteLine();
				}
			}
			else
			{
				Console.WriteLine();
			}
		}
	}

}
