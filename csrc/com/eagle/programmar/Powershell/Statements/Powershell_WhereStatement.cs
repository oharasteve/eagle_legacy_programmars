// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2022

namespace com.eagle.programmar.Powershell.Statements
{
	using Powershell_Element = com.eagle.programmar.Powershell.Powershell_Element;
	using Powershell_EndOfLine = com.eagle.programmar.Powershell.Powershell_EndOfLine;
	using Powershell_KeywordChoice = com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
	using Powershell_Punctuation = com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;

	public class Powershell_WhereStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Powershell_Where where;
		public Powershell_Where where;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
		public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Powershell_EndOfLine eoln;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<com.eagle.programmar.Powershell.Powershell_Element> stmts;
		public TokenList<Powershell_Element> stmts;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
		public PunctuationRightBrace rightBrace;

		public class Powershell_Where : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_Punctuation XXpercent = new com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation("?");
			public Powershell_Punctuation XXpercent = new Powershell_Punctuation("?");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_KeywordChoice XXWHERE = new com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice("Where", "Where-Object");
			public Powershell_KeywordChoice XXWHERE = new Powershell_KeywordChoice("Where", "Where-Object");
		}
	}

}
