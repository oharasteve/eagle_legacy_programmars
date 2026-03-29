// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Javascript.Expressions
{
	using Javascript_ArgumentList = com.eagle.programmar.Javascript.Javascript_ArgumentList;
	using Javascript_Type = com.eagle.programmar.Javascript.Javascript_Type;
	using Javascript_Keyword = com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;

	public class Javascript_ClassCreationWithInitializers : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Javascript.Terminals.Javascript_Keyword NEW = new com.eagle.programmar.Javascript.Terminals.Javascript_Keyword("new");
		public Javascript_Keyword NEW = new Javascript_Keyword("new");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Javascript.Javascript_Type jtype;
		public Javascript_Type jtype;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
		public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Javascript.Javascript_ArgumentList valueList;
		public Javascript_ArgumentList valueList;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
		public PunctuationRightBrace rightBrace;
	}

}
