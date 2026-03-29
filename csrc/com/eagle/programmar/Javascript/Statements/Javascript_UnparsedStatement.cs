// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 12, 2014

namespace com.eagle.programmar.Javascript.Statements
{
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using Javascript_Syntax = com.eagle.programmar.Javascript.Javascript_Syntax;
	using Javascript_Identifier_Reference = com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
	using Javascript_Comment = com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
	using Javascript_KeywordChoice = com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
	using Javascript_Literal = com.eagle.programmar.Javascript.Terminals.Javascript_Literal;
	using Javascript_Number = com.eagle.programmar.Javascript.Terminals.Javascript_Number;
	using Javascript_PunctuationChoice = com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
	using Javascript_RegularExpression = com.eagle.programmar.Javascript.Terminals.Javascript_RegularExpression;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using UnparsedElement = com.eagle.tokens.UnparsedElement;

	public class Javascript_UnparsedStatement : UnparsedElement
	{
		private static EagleSyntax SYNTAX = new Javascript_Syntax();
		internal static string[] KEYWORDS = SYNTAX.allReservedWords();
		internal static string[] PUNCTS = new string[] {"+", "-", "*", "/", ".", ",", "?", ":", "=", ">", "(", ")", "[", "]", "&", "|"};

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// JAVA TO C# CONVERTER WARNING: Java wildcard generics have no direct equivalent in C#:
// ORIGINAL LINE: @Override public @SKIP TokenList<? extends com.eagle.tokens.AbstractToken> unparsedPieces()
		internal override TokenList<AbstractToken> unparsedPieces()
		{
			return elements;
		}

		public TokenList<Javascript_UnparsedElement> elements;

		public class Javascript_UnparsedElement : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_Identifier_Reference XXid;
			public Javascript_Identifier_Reference XXid;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Javascript_PunctuationChoice XXpunct = new com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice(PUNCTS);
			public Javascript_PunctuationChoice XXpunct = new Javascript_PunctuationChoice(PUNCTS);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_Literal XXliteral;
			public Javascript_Literal XXliteral;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_Number XXnumber;
			public Javascript_Number XXnumber;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_Comment XXcomment;
			public Javascript_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_RegularExpression XXregex;
			public Javascript_RegularExpression XXregex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_KeywordChoice XXkeyword = new com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice(KEYWORDS);
			public Javascript_KeywordChoice XXkeyword = new Javascript_KeywordChoice(KEYWORDS);
		}
	}

}
