// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 5, 2014

namespace com.eagle.programmar.CSharp.Directives
{
	using CSharp_ClassElement = com.eagle.programmar.CSharp.CSharp_Class.CSharp_ClassElement;
	using CSharp_CommentToEndOfLine = com.eagle.programmar.CSharp.Terminals.CSharp_CommentToEndOfLine;
	using CSharp_Keyword = com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
	using CSharp_Punctuation = com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class CSharp_RegionDirective : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation pound1 = new com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation('#');
		public CSharp_Punctuation pound1 = new CSharp_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSharp.Terminals.CSharp_Keyword REGION = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("region");
		public CSharp_Keyword REGION = new CSharp_Keyword("region");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CSharp.Terminals.CSharp_CommentToEndOfLine regionName;
		public CSharp_CommentToEndOfLine regionName;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<com.eagle.programmar.CSharp.CSharp_Class.CSharp_ClassElement> elements;
		public TokenList<CSharp_ClassElement> elements;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation pound2 = new com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation('#');
		public CSharp_Punctuation pound2 = new CSharp_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.CSharp.Terminals.CSharp_Keyword ENDREGION = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("endregion");
		public CSharp_Keyword ENDREGION = new CSharp_Keyword("endregion");
	}

}
