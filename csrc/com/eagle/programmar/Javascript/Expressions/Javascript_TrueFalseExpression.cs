// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Javascript.Expressions
{
	using Javascript_Expression = com.eagle.programmar.Javascript.Javascript_Expression;
	using Javascript_Comment = com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
	using Javascript_Punctuation = com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using TokenList = com.eagle.tokens.TokenList;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;

	public class Javascript_TrueFalseExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Javascript.Javascript_Expression left = new com.eagle.programmar.Javascript.Javascript_Expression(this, AllowedPrecedence.HIGHER);
		public Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation questionMark = new com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation('?');
		public Javascript_Punctuation questionMark = new Javascript_Punctuation('?');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.Javascript.Terminals.Javascript_Comment> comments1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Javascript.Javascript_Expression middle = new com.eagle.programmar.Javascript.Javascript_Expression(this, AllowedPrecedence.ANY);
		public Javascript_Expression middle = new Javascript_Expression(this, AllowedPrecedence.ANY);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationColon colon;
		public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT TokenList<com.eagle.programmar.Javascript.Terminals.Javascript_Comment> comments2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Javascript.Javascript_Expression right = new com.eagle.programmar.Javascript.Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
	}

}
