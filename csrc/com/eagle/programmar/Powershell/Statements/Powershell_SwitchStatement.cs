// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2024

namespace com.eagle.programmar.Powershell.Statements
{
	using Powershell_Element = com.eagle.programmar.Powershell.Powershell_Element;
	using Powershell_EndOfLine = com.eagle.programmar.Powershell.Powershell_EndOfLine;
	using Powershell_Expression = com.eagle.programmar.Powershell.Powershell_Expression;
	using Powershell_Keyword = com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Powershell_SwitchStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword SWITCH = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("Switch");
		public Powershell_Keyword SWITCH = new Powershell_Keyword("Switch");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Powershell.Powershell_Expression expr;
		public Powershell_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Powershell_EndOfLine eoln1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
		public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT Powershell_EndOfLine eoln2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT TokenList<Powershell_SwitchCases> cases;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
		public PunctuationRightBrace rightBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT Powershell_EndOfLine eoln3;
		public  OPT;

		public class Powershell_SwitchCases : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Powershell_SwitchCondition switchCondition;
			public Powershell_SwitchCondition switchCondition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
			public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Powershell_EndOfLine eoln1;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.Powershell.Powershell_Element> stmts;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
			public PunctuationRightBrace rightBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Powershell_EndOfLine eoln2;
			public  OPT;
		}

		public class Powershell_SwitchCondition : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST Powershell_Keyword XXDEFAULT = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("Default");
			public Powershell_Keyword XXDEFAULT = new Powershell_Keyword("Default");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_Expression XXexpr;
			public Powershell_Expression XXexpr;
		}
	}

}
