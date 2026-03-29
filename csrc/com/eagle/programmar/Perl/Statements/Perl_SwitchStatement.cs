// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2011

namespace com.eagle.programmar.Perl.Statements
{
	using Perl_Expression = com.eagle.programmar.Perl.Perl_Expression;
	using Perl_StatementOrComment = com.eagle.programmar.Perl.Perl_StatementOrComment;
	using Perl_Keyword = com.eagle.programmar.Perl.Terminals.Perl_Keyword;
	using Perl_KeywordChoice = com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Perl_SwitchStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("control-structures.switch.php") com.eagle.programmar.Perl.Terminals.Perl_Keyword SWITCH = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("switch");
		public @DOC("control-structures.switch.php") Perl_Keyword SWITCH = new Perl_Keyword("switch");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Perl.Perl_Expression val;
		public Perl_Expression val;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
		public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.TokenList<Perl_CaseClause> caseClause;
		public TokenList<Perl_CaseClause> caseClause;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT Perl_DefaultClause elseClause;
		public @OPT Perl_DefaultClause elseClause;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
		public PunctuationRightBrace rightBrace;

		public static class Perl_CaseClause extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Keyword CASE = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("case");
			public Perl_Keyword CASE = new Perl_Keyword("case");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Perl_Expression expr;
			public Perl_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PunctuationColon colon;
			public @OPT PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.Perl.Perl_StatementOrComment> statements;
			public @OPT TokenList<Perl_StatementOrComment> statements;
		}

		public static class Perl_DefaultClause extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice DEFAULT = new com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice("default", "else");
			public Perl_KeywordChoice DEFAULT = new Perl_KeywordChoice("default", "else");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PunctuationColon colon;
			public @OPT PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.Perl.Perl_StatementOrComment> statements;
			public TokenList<Perl_StatementOrComment> statements;
		}
	}

}
