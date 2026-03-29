// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 15, 2022

namespace com.eagle.programmar.Django.Controls
{
	using Django_Element = com.eagle.programmar.Django.Django_Element;
	using Django_Expression = com.eagle.programmar.Django.Django_Expression;
	using Django_Keyword = com.eagle.programmar.Django.Terminals.Django_Keyword;
	using HTML_Punctuation = com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationHyphen = com.eagle.tokens.punctuation.PunctuationHyphen;

	public class Django_MacroControl : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Django.Terminals.Django_Keyword MACRO = new com.eagle.programmar.Django.Terminals.Django_Keyword("macro");
		public Django_Keyword MACRO = new Django_Keyword("macro");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Django.Django_Expression expr;
		public Django_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PunctuationHyphen dash2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.HTML.Terminals.HTML_Punctuation percentBrace1 = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("%}");
		public HTML_Punctuation percentBrace1 = new HTML_Punctuation("%}");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.TokenList<com.eagle.programmar.Django.Django_Element> html;
		public TokenList<Django_Element> html;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.HTML.Terminals.HTML_Punctuation bracePercent2 = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("{%");
		public HTML_Punctuation bracePercent2 = new HTML_Punctuation("{%");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT PunctuationHyphen dash3;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.Django.Terminals.Django_Keyword ENDMACRO = new com.eagle.programmar.Django.Terminals.Django_Keyword("endmacro");
		public Django_Keyword ENDMACRO = new Django_Keyword("endmacro");
	}

}
