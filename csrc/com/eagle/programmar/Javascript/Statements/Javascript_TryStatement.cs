// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

namespace com.eagle.programmar.Javascript.Statements
{
	using Javascript_Element = com.eagle.programmar.Javascript.Javascript_Element;
	using Javascript_StatementOrComment = com.eagle.programmar.Javascript.Javascript_Element.Javascript_StatementOrComment;
	using Javascript_Variable_Definition = com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
	using Javascript_Keyword = com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Javascript_TryStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("js_try_catch.asp") com.eagle.programmar.Javascript.Terminals.Javascript_Keyword TRY = new com.eagle.programmar.Javascript.Terminals.Javascript_Keyword("try");
		public @DOC("js_try_catch.asp") Javascript_Keyword TRY = new Javascript_Keyword("try");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
		public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.Javascript.Javascript_Element.Javascript_StatementOrComment> statements;
		public @OPT TokenList<Javascript_Element.Javascript_StatementOrComment> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
		public PunctuationRightBrace rightBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Javascript_CatchBlock catchBlock;
		public @OPT Javascript_CatchBlock catchBlock;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Javascript_FinallyBlock finallyBlock;
		public @OPT Javascript_FinallyBlock finallyBlock;

		public static class Javascript_CatchBlock extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Javascript.Terminals.Javascript_Keyword CATCH = new com.eagle.programmar.Javascript.Terminals.Javascript_Keyword("catch");
			public Javascript_Keyword CATCH = new Javascript_Keyword("catch");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition id;
			public Javascript_Variable_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Javascript.Javascript_Element catchStatement;
			public Javascript_Element catchStatement;
		}

		public static class Javascript_FinallyBlock extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Javascript.Terminals.Javascript_Keyword FINALLY = new com.eagle.programmar.Javascript.Terminals.Javascript_Keyword("finally");
			public Javascript_Keyword FINALLY = new Javascript_Keyword("finally");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Javascript.Javascript_Element finallyStatement;
			public Javascript_Element finallyStatement;
		}
	}

}
