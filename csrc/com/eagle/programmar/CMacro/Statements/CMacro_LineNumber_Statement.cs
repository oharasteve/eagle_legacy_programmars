// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2020

namespace com.eagle.programmar.CMacro.Statements
{
	using C_Keyword = com.eagle.programmar.C.Terminals.C_Keyword;
	using CMacro_Literal = com.eagle.programmar.CMacro.Terminals.CMacro_Literal;
	using CMacro_Number = com.eagle.programmar.CMacro.Terminals.CMacro_Number;
	using CMacro_Punctuation = com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class CMacro_LineNumber_Statement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation pound = new com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation('#');
		public CMacro_Punctuation pound = new CMacro_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT C_Keyword LINE = new com.eagle.programmar.C.Terminals.C_Keyword("line");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMacro.Terminals.CMacro_Number lineNumber;
		public CMacro_Number lineNumber;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.CMacro.Terminals.CMacro_Literal fileName;
		public CMacro_Literal fileName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<com.eagle.programmar.CMacro.Terminals.CMacro_Number> moreNumbers;
		public  OPT;
	}

}
