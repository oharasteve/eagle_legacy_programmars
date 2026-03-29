// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2014

namespace com.eagle.programmar.CSS.Directives
{
	using CSS_Entry = com.eagle.programmar.CSS.CSS_Program.CSS_Entry;
	using CSS_Keyword = com.eagle.programmar.CSS.Terminals.CSS_Keyword;
	using CSS_Literal = com.eagle.programmar.CSS.Terminals.CSS_Literal;
	using CSS_Punctuation = com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationSlash = com.eagle.tokens.punctuation.PunctuationSlash;

	public class CSS_If_Directive : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSS.Terminals.CSS_Punctuation lessThan = new com.eagle.programmar.CSS.Terminals.CSS_Punctuation('<');
		public CSS_Punctuation lessThan = new CSS_Punctuation('<');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSS.Terminals.CSS_Keyword IF = new com.eagle.programmar.CSS.Terminals.CSS_Keyword("if");
		public CSS_Keyword IF = new CSS_Keyword("if");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CSS.Terminals.CSS_Keyword EXPR = new com.eagle.programmar.CSS.Terminals.CSS_Keyword("expr");
		public CSS_Keyword EXPR = new CSS_Keyword("expr");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.CSS.Terminals.CSS_Literal literal;
		public CSS_Literal literal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.CSS.Terminals.CSS_Punctuation greaterThan = new com.eagle.programmar.CSS.Terminals.CSS_Punctuation('>');
		public CSS_Punctuation greaterThan = new CSS_Punctuation('>');

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.TokenList<com.eagle.programmar.CSS.CSS_Program.CSS_Entry> entries;
		public TokenList<CSS_Entry> entries;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) CSS_EndIf endIf;
		public CSS_EndIf endIf;

		public class CSS_EndIf : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSS.Terminals.CSS_Punctuation lessThan = new com.eagle.programmar.CSS.Terminals.CSS_Punctuation('<');
			public CSS_Punctuation lessThan = new CSS_Punctuation('<');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationSlash slash;
			public PunctuationSlash slash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CSS.Terminals.CSS_Keyword IF = new com.eagle.programmar.CSS.Terminals.CSS_Keyword("if");
			public CSS_Keyword IF = new CSS_Keyword("if");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.CSS.Terminals.CSS_Punctuation greaterThan = new com.eagle.programmar.CSS.Terminals.CSS_Punctuation('>');
			public CSS_Punctuation greaterThan = new CSS_Punctuation('>');
		}
	}

}
