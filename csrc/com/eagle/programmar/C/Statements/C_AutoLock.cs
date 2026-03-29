// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 2, 2022

namespace com.eagle.programmar.C.Statements
{
	using C_Expression = com.eagle.programmar.C.C_Expression;
	using C_Identifier_Reference = com.eagle.programmar.C.Symbols.C_Identifier_Reference;
	using C_Comment = com.eagle.programmar.C.Terminals.C_Comment;
	using C_Keyword = com.eagle.programmar.C.Terminals.C_Keyword;
	using C_Punctuation = com.eagle.programmar.C.Terminals.C_Punctuation;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class C_AutoLock : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<com.eagle.programmar.C.Terminals.C_Comment> comments;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT C_AutoLockMutex mutex;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.Terminals.C_Keyword AUTOLOCK = new com.eagle.programmar.C.Terminals.C_Keyword("AutoLock");
		public C_Keyword AUTOLOCK = new C_Keyword("AutoLock");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.C.Symbols.C_Identifier_Reference id;
		public C_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.C.C_Expression expr;
		public C_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

		public class C_AutoLockMutex : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_Keyword MUTEX = new com.eagle.programmar.C.Terminals.C_Keyword("Mutex");
			public C_Keyword MUTEX = new C_Keyword("Mutex");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Terminals.C_Punctuation colonColon = new com.eagle.programmar.C.Terminals.C_Punctuation("::");
			public C_Punctuation colonColon = new C_Punctuation("::");
		}
	}
}
