// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 4, 2014

namespace com.eagle.programmar.CSharp.Statements
{
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using CSharp_Syntax = com.eagle.programmar.CSharp.CSharp_Syntax;
	using CSharp_Identifier_Reference = com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
	using CSharp_Comment = com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
	using CSharp_HexNumber = com.eagle.programmar.CSharp.Terminals.CSharp_HexNumber;
	using CSharp_KeywordChoice = com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
	using CSharp_Literal = com.eagle.programmar.CSharp.Terminals.CSharp_Literal;
	using CSharp_Number = com.eagle.programmar.CSharp.Terminals.CSharp_Number;
	using CSharp_PunctuationChoice = com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using UnparsedElement = com.eagle.tokens.UnparsedElement;

	public class CSharp_UnparsedStatement : UnparsedElement
	{
		private static EagleSyntax SYNTAX = new CSharp_Syntax();
		internal static string[] KEYWORDS = SYNTAX.allReservedWords();
		internal static string[] PUNCTS = new string[] {"+", "-", "*", "/", ".", ",", "?", ":", "<", "=", ">", "(", ")", "[", "]", "&", "|"};

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// JAVA TO C# CONVERTER WARNING: Java wildcard generics have no direct equivalent in C#:
// ORIGINAL LINE: @Override public @SKIP TokenList<? extends com.eagle.tokens.AbstractToken> unparsedPieces()
		internal override TokenList<AbstractToken> unparsedPieces()
		{
			return elements;
		}

		public TokenList<CSharp_UnparsedElement> elements;

		public class CSharp_UnparsedElement : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_Identifier_Reference XXid;
			public CSharp_Identifier_Reference XXid;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST CSharp_PunctuationChoice XXpunct = new com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice(PUNCTS);
			public CSharp_PunctuationChoice XXpunct = new CSharp_PunctuationChoice(PUNCTS);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_Literal XXliteral;
			public CSharp_Literal XXliteral;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_Number XXnumber;
			public CSharp_Number XXnumber;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_HexNumber XXhex;
			public CSharp_HexNumber XXhex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_Comment XXcomment;
			public CSharp_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_KeywordChoice XXkeyword = new com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice(KEYWORDS);
			public CSharp_KeywordChoice XXkeyword = new CSharp_KeywordChoice(KEYWORDS);
		}
	}

}
