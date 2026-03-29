// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

namespace com.eagle.programmar.CSharp.Methods
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_KeywordChoice = com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class CSharp_ToUpperMethod : PrecedenceOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.CSharp_Expression left = new com.eagle.programmar.CSharp.CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot;
		public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice TOUPPER = new com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice("ToLower", "ToUpper");
		public CSharp_KeywordChoice TOUPPER = new CSharp_KeywordChoice("ToLower", "ToUpper");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT CSharp_Expression expr;
		public  OPT; // str.ToUpper() and Char.ToUpper(ch) are both ok
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public override void interpret(EagleInterpreter interpreter)
		{
			string leftStr = interpreter.getStrValue(left);
			switch (TOUPPER.getValue())
			{
			case "ToLower":
				interpreter.pushStr(leftStr.ToLower());
				break;
			case "ToUpper":
				interpreter.pushStr(leftStr.ToUpper());
				break;
			}
		}
	}

}
