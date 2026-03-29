// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2011

namespace com.eagle.programmar.PLI.Statements
{
	using PLI_Label = com.eagle.programmar.PLI.PLI_Label;
	using PLI_StatementOrComment = com.eagle.programmar.PLI.PLI_Procedure.PLI_StatementOrComment;
	using PLI_Identifier_Reference = com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
	using PLI_Keyword = com.eagle.programmar.PLI.Terminals.PLI_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class PLI_BeginStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PLI_Label label1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("7.5") com.eagle.programmar.PLI.Terminals.PLI_Keyword BEGIN = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("BEGIN");
		public @DOC("7.5") PLI_Keyword BEGIN = new PLI_Keyword("BEGIN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon1;
		public PunctuationSemicolon semicolon1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<com.eagle.programmar.PLI.PLI_Procedure.PLI_StatementOrComment> statements;
		public TokenList<PLI_StatementOrComment> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.PLI.Terminals.PLI_Keyword END = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("END");
		public PLI_Keyword END = new PLI_Keyword("END");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT PLI_Identifier_Reference label2;
		public @OPT PLI_Identifier_Reference label2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon2;
		public PunctuationSemicolon semicolon2;
	}

}
