// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.VB.Functions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using VB_Expression = com.eagle.programmar.VB.VB_Expression;
	using VB_KeywordChoice = com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class VB_UcaseFunction : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("functions/string-functions") com.eagle.programmar.VB.Terminals.VB_KeywordChoice UCASE = new com.eagle.programmar.VB.Terminals.VB_KeywordChoice("Lcase", "Ucase");
		public @DOC("functions/string-functions") VB_KeywordChoice UCASE = new VB_KeywordChoice("Lcase", "Ucase");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.VB.VB_Expression expr;
		public VB_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public void interpret(EagleInterpreter interpreter)
		{
			string str = interpreter.getStrValue(expr);
			switch (UCASE.ToString().ToLower())
			{
			case "lcase":
				interpreter.pushStr(str.ToLower());
				break;
			case "ucase":
				interpreter.pushStr(str.ToUpper());
				break;
			}
		}
	}

}
