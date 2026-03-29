// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 6, 2012

namespace com.eagle.programmar.COBOL.Statements
{
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using COBOL_Fixed_Format_Syntax = com.eagle.programmar.COBOL.COBOL_Syntax.COBOL_Fixed_Format_Syntax;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Comment = com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
	using COBOL_HexNumber = com.eagle.programmar.COBOL.Terminals.COBOL_HexNumber;
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using COBOL_Literal = com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
	using COBOL_Number = com.eagle.programmar.COBOL.Terminals.COBOL_Number;
	using COBOL_PunctuationChoice = com.eagle.programmar.COBOL.Terminals.COBOL_PunctuationChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using UnparsedElement = com.eagle.tokens.UnparsedElement;

	public class COBOL_UnparsedStatement : UnparsedElement
	{
		private static EagleSyntax SYNTAX = new COBOL_Fixed_Format_Syntax(); // Any COBOL syntax works, just need reserved
																				// words
		internal static string[] KEYWORDS = SYNTAX.allReservedWords();
		internal static string[] PUNCTS = new string[] {"+", "-", "*", "/", ",", "(", ")"};

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// JAVA TO C# CONVERTER WARNING: Java wildcard generics have no direct equivalent in C#:
// ORIGINAL LINE: @Override public @SKIP TokenList<? extends com.eagle.tokens.AbstractToken> unparsedPieces()
		internal override TokenList<AbstractToken> unparsedPieces()
		{
			return elements;
		}

		public TokenList<COBOL_UnparsedElement> elements;

		public class COBOL_UnparsedElement : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Identifier_Reference XXid;
			public COBOL_Identifier_Reference XXid;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_PunctuationChoice XXpunct = new com.eagle.programmar.COBOL.Terminals.COBOL_PunctuationChoice(PUNCTS);
			public COBOL_PunctuationChoice XXpunct = new COBOL_PunctuationChoice(PUNCTS);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Literal XXliteral;
			public COBOL_Literal XXliteral;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Number XXnumber;
			public COBOL_Number XXnumber;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_HexNumber XXhex;
			public COBOL_HexNumber XXhex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Comment XXcomment;
			public COBOL_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_KeywordChoice XXkeyword = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice(KEYWORDS);
			public COBOL_KeywordChoice XXkeyword = new COBOL_KeywordChoice(KEYWORDS);
		}
	}

}
