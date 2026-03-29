// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 5, 2015

namespace com.eagle.programmar.Django.Controls
{
	using Django_CommentUntilBrace = com.eagle.programmar.Django.Terminals.Django_CommentUntilBrace;
	using Django_Keyword = com.eagle.programmar.Django.Terminals.Django_Keyword;
	using HTML_Punctuation = com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationHyphen = com.eagle.tokens.punctuation.PunctuationHyphen;

	public class Django_CommentControl : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Django.Terminals.Django_Keyword COMMENT = new com.eagle.programmar.Django.Terminals.Django_Keyword("comment");
		public Django_Keyword COMMENT = new Django_Keyword("comment");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PunctuationHyphen dash2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.HTML.Terminals.HTML_Punctuation percentBrace1 = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("%}");
		public HTML_Punctuation percentBrace1 = new HTML_Punctuation("%}");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Django.Terminals.Django_CommentUntilBrace comment;
		public Django_CommentUntilBrace comment;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.HTML.Terminals.HTML_Punctuation bracePercent2 = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("{%");
		public HTML_Punctuation bracePercent2 = new HTML_Punctuation("{%");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT PunctuationHyphen dash3;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Django.Terminals.Django_Keyword ENDCOMMENT = new com.eagle.programmar.Django.Terminals.Django_Keyword("endcomment");
		public Django_Keyword ENDCOMMENT = new Django_Keyword("endcomment");
	}

}
