// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_Label = com.eagle.programmar.Natural.Natural_Label;
	using Natural_Identifier_Reference = com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Natural_StoreStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Natural_Label label;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("sm/store.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword STORE = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("STORE");
		public @DOC("sm/store.htm") Natural_Keyword STORE = new Natural_Keyword("STORE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Natural_Keyword RECORD = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("RECORD");
		public @OPT Natural_Keyword RECORD = new Natural_Keyword("RECORD");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Natural_Keyword IN = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("IN");
		public @OPT Natural_Keyword IN = new Natural_Keyword("IN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Natural_Keyword FILE = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("FILE");
		public @OPT Natural_Keyword FILE = new Natural_Keyword("FILE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference viewName;
		public Natural_Identifier_Reference viewName;
	}

}
