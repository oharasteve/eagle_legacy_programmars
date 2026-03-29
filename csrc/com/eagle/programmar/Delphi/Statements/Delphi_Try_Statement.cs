// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 16, 2013

namespace com.eagle.programmar.Delphi.Statements
{
	using Delphi_Statement_List = com.eagle.programmar.Delphi.Delphi_Statement_List;
	using Delphi_Keyword = com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Delphi_Try_Statement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("Exceptions_(Delphi)") com.eagle.programmar.Delphi.Terminals.Delphi_Keyword TRY = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("Try");
		public @DOC("Exceptions_(Delphi)") Delphi_Keyword TRY = new Delphi_Keyword("Try");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Delphi.Delphi_Statement_List statements1;
		public Delphi_Statement_List statements1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Delphi_Keyword EXCEPT = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("Except");
		public @OPT Delphi_Keyword EXCEPT = new Delphi_Keyword("Except");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Delphi_Statement_List statements2;
		public @OPT Delphi_Statement_List statements2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Delphi_Keyword FINALLY = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("Finally");
		public @OPT Delphi_Keyword FINALLY = new Delphi_Keyword("Finally");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Delphi_Statement_List statements3;
		public @OPT Delphi_Statement_List statements3;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Delphi.Terminals.Delphi_Keyword END = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("End");
		public Delphi_Keyword END = new Delphi_Keyword("End");
	}

}
