// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

namespace com.eagle.programmar.Django.Controls
{
	using Django_Element = com.eagle.programmar.Django.Django_Element;
	using Django_Variable_Definition = com.eagle.programmar.Django.Symbols.Django_Variable_Definition;
	using Django_Keyword = com.eagle.programmar.Django.Terminals.Django_Keyword;
	using HTML_Punctuation = com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationHyphen = com.eagle.tokens.punctuation.PunctuationHyphen;

	public class Django_BlockControl : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Django.Terminals.Django_Keyword BLOCK = new com.eagle.programmar.Django.Terminals.Django_Keyword("block");
		public Django_Keyword BLOCK = new Django_Keyword("block");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Django.Symbols.Django_Variable_Definition variable;
		public Django_Variable_Definition variable;
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
// ORIGINAL LINE: public @S(80) com.eagle.programmar.Django.Terminals.Django_Keyword ENDBLOCK = new com.eagle.programmar.Django.Terminals.Django_Keyword("endblock");
		public Django_Keyword ENDBLOCK = new Django_Keyword("endblock");
	}

}
