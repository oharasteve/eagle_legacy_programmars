// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2014

namespace com.eagle.programmar.Perl.Statements
{
	using Perl_Statement = com.eagle.programmar.Perl.Perl_Statement;
	using Perl_Identifier_Reference = com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
	using Perl_Variable_Definition = com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition;
	using Perl_Comment = com.eagle.programmar.Perl.Terminals.Perl_Comment;
	using Perl_Keyword = com.eagle.programmar.Perl.Terminals.Perl_Keyword;
	using Perl_Punctuation = com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Perl_TryStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Keyword TRY = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("try");
		public Perl_Keyword TRY = new Perl_Keyword("try");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
		public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.Perl.Perl_Statement> statements;
		public TokenList<Perl_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
		public PunctuationRightBrace rightBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<com.eagle.programmar.Perl.Terminals.Perl_Comment> comments;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT TokenList<Perl_CatchBlock> catchBlocks;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT Perl_FinallyBlock finallyBlock;
		public  OPT;

		public class Perl_CatchBlock : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Keyword CATCH = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("catch");
			public Perl_Keyword CATCH = new Perl_Keyword("catch");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Perl_Punctuation backSlash = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('\\');
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference ref;
			public Perl_Identifier_Reference @ref;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Perl_Punctuation dollar = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('$');
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition id;
			public Perl_Variable_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.Perl.Perl_Statement catchStatement;
			public Perl_Statement catchStatement;
		}

		public class Perl_FinallyBlock : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Keyword FINALLY = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("finally");
			public Perl_Keyword FINALLY = new Perl_Keyword("finally");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Perl_Statement finallyStatement;
			public Perl_Statement finallyStatement;
		}
	}

}
