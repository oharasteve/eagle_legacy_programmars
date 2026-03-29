// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 10, 2022

namespace com.eagle.programmar.C.Statements
{
	using C_Label_Reference = com.eagle.programmar.C.Symbols.C_Label_Reference;
	using C_Keyword = com.eagle.programmar.C.Terminals.C_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class C_GotoStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("#The-goto-Statement") com.eagle.programmar.C.Terminals.C_Keyword GOTO = new com.eagle.programmar.C.Terminals.C_Keyword("goto");
		public @DOC("#The-goto-Statement") C_Keyword GOTO = new C_Keyword("goto");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT C_Label_Reference label;
		public @OPT C_Label_Reference label;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;
	}

}
