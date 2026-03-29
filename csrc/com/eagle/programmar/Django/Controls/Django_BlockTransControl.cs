// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 5, 2015

namespace com.eagle.programmar.Django.Controls
{
	using Django_Element = com.eagle.programmar.Django.Django_Element;
	using Django_Variable = com.eagle.programmar.Django.Django_Variable;
	using Django_Keyword = com.eagle.programmar.Django.Terminals.Django_Keyword;
	using Django_Literal = com.eagle.programmar.Django.Terminals.Django_Literal;
	using HTML_Punctuation = com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationHyphen = com.eagle.tokens.punctuation.PunctuationHyphen;

	public class Django_BlockTransControl : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Django.Terminals.Django_Keyword BLOCK = new com.eagle.programmar.Django.Terminals.Django_Keyword("blocktrans");
		public Django_Keyword BLOCK = new Django_Keyword("blocktrans");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Django_BlockControlWith with;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PunctuationHyphen dash2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.HTML.Terminals.HTML_Punctuation percentBrace1 = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("%}");
		public HTML_Punctuation percentBrace1 = new HTML_Punctuation("%}");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<com.eagle.programmar.Django.Django_Element> html;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.HTML.Terminals.HTML_Punctuation bracePercent2 = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("{%");
		public HTML_Punctuation bracePercent2 = new HTML_Punctuation("{%");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT PunctuationHyphen dash3;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.Django.Terminals.Django_Keyword ENDBLOCK = new com.eagle.programmar.Django.Terminals.Django_Keyword("endblocktrans");
		public Django_Keyword ENDBLOCK = new Django_Keyword("endblocktrans");

		public class Django_BlockControlWith : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Django.Terminals.Django_Keyword WITH = new com.eagle.programmar.Django.Terminals.Django_Keyword("with");
			public Django_Keyword WITH = new Django_Keyword("with");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Django.Terminals.Django_Literal literal;
			public Django_Literal literal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Django.Terminals.Django_Keyword AS = new com.eagle.programmar.Django.Terminals.Django_Keyword("as");
			public Django_Keyword AS = new Django_Keyword("as");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Django.Django_Variable var;
			public Django_Variable var;
		}
	}

}
