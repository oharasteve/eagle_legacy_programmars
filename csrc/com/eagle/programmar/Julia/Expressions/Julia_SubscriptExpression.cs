// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Julia.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Julia_Expression = com.eagle.programmar.Julia.Julia_Expression;
	using Julia_Keyword = com.eagle.programmar.Julia.Terminals.Julia_Keyword;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;

	public class Julia_SubscriptExpression : PrecedenceOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Julia.Julia_Expression expr = new com.eagle.programmar.Julia.Julia_Expression(this, AllowedPrecedence.HIGHER);
		public Julia_Expression expr = new Julia_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
		public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Julia_Expression subscr1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PunctuationColon colon;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Julia_SubscriptionEnd subscr2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
		public PunctuationRightBracket rightBracket;

		public class Julia_SubscriptionEnd : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Julia_Keyword XXEND = new com.eagle.programmar.Julia.Terminals.Julia_Keyword("end");
			public Julia_Keyword XXEND = new Julia_Keyword("end");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Julia_Expression XXsubscr;
			public Julia_Expression XXsubscr;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			string str = interpreter.getStrValue(expr);
			int start = interpreter.getIntValue(subscr1) - 1;
			if (subscr2.getWhich() is Julia_Expression)
			{
				int stop = interpreter.getIntValue(subscr2.getWhich()) - 1;
				interpreter.pushStr(str.Substring(start, stop - start));
			}
			else
			{
				interpreter.pushStr(str.Substring(start));
			}
		}
	}

}
