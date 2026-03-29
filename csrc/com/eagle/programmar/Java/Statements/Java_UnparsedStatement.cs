// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 4, 2014

namespace com.eagle.programmar.Java.Statements
{
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using Java_Syntax = com.eagle.programmar.Java.Java_Syntax;
	using Java_Identifier_Reference = com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
	using Java_Comment = com.eagle.programmar.Java.Terminals.Java_Comment;
	using Java_HexNumber = com.eagle.programmar.Java.Terminals.Java_HexNumber;
	using Java_KeywordChoice = com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
	using Java_Literal = com.eagle.programmar.Java.Terminals.Java_Literal;
	using Java_Number = com.eagle.programmar.Java.Terminals.Java_Number;
	using Java_PunctuationChoice = com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using UnparsedElement = com.eagle.tokens.UnparsedElement;

	public class Java_UnparsedStatement : UnparsedElement
	{
		private static EagleSyntax SYNTAX = new Java_Syntax();
		internal static string[] KEYWORDS = SYNTAX.allReservedWords();
		internal static string[] PUNCTS = new string[] {"+", "-", "*", "/", ".", ",", "?", ":", "<", "=", ">", "(", ")", "[", "]", "&", "|"};

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// JAVA TO C# CONVERTER WARNING: Java wildcard generics have no direct equivalent in C#:
// ORIGINAL LINE: @Override public @SKIP TokenList<? extends com.eagle.tokens.AbstractToken> unparsedPieces()
		internal override TokenList<AbstractToken> unparsedPieces()
		{
			return elements;
		}

		public TokenList<Java_UnparsedElement> elements;

		public class Java_UnparsedElement : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_Identifier_Reference XXid;
			public Java_Identifier_Reference XXid;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_PunctuationChoice XXpunct = new com.eagle.programmar.Java.Terminals.Java_PunctuationChoice(PUNCTS);
			public Java_PunctuationChoice XXpunct = new Java_PunctuationChoice(PUNCTS);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_Literal XXliteral;
			public Java_Literal XXliteral;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_Number XXnumber;
			public Java_Number XXnumber;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_HexNumber XXhex;
			public Java_HexNumber XXhex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_Comment XXcomment;
			public Java_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_KeywordChoice XXkeyword = new com.eagle.programmar.Java.Terminals.Java_KeywordChoice(KEYWORDS);
			public Java_KeywordChoice XXkeyword = new Java_KeywordChoice(KEYWORDS);
		}
	}

}
