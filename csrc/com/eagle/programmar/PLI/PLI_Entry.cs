// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2015

namespace com.eagle.programmar.PLI
{
	using PLI_ProcedureOption = com.eagle.programmar.PLI.PLI_Procedure.PLI_ProcedureOption;
	using PLI_Procedure_Parameters = com.eagle.programmar.PLI.PLI_Procedure.PLI_Procedure_Parameters;
	using PLI_StatementOrComment = com.eagle.programmar.PLI.PLI_Procedure.PLI_StatementOrComment;
	using PLI_Procedure_Definition = com.eagle.programmar.PLI.Symbols.PLI_Procedure_Definition;
	using PLI_Keyword = com.eagle.programmar.PLI.Terminals.PLI_Keyword;
	using PLI_Punctuation = com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class PLI_Entry : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PLI_Punctuation percent1 = new com.eagle.programmar.PLI.Terminals.PLI_Punctuation('%');
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PLI.Symbols.PLI_Procedure_Definition id1;
		public PLI_Procedure_Definition id1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationColon colon;
		public PunctuationColon colon;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.PLI.Terminals.PLI_Keyword ENTRY = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("ENTRY");
		public PLI_Keyword ENTRY = new PLI_Keyword("ENTRY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT PLI_Procedure_Parameters params;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT TokenList<com.eagle.programmar.PLI.PLI_Procedure.PLI_ProcedureOption> options;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.TokenList<com.eagle.programmar.PLI.PLI_Procedure.PLI_StatementOrComment> statements;
		public TokenList<PLI_StatementOrComment> statements;
	}

}
