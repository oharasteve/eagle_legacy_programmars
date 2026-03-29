// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 12, 2011

namespace com.eagle.programmar.IBMASM.Data
{
	using IBMASM_Label_Definition = com.eagle.programmar.IBMASM.Symbols.IBMASM_Label_Definition;
	using IBMASM_Keyword = com.eagle.programmar.IBMASM.Terminals.IBMASM_Keyword;
	using IBMASM_KeywordChoice = com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice;
	using IBMASM_Number = com.eagle.programmar.IBMASM.Terminals.IBMASM_Number;
	using IBMASM_Remark = com.eagle.programmar.IBMASM.Terminals.IBMASM_Remark;
	using IBMASM_Spaces = com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class IBMASM_DS_Instruction : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT IBMASM_Label_Definition label;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces spaces1;
		public IBMASM_Spaces spaces1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.IBMASM.Terminals.IBMASM_Keyword DS = new com.eagle.programmar.IBMASM.Terminals.IBMASM_Keyword("DS");
		public IBMASM_Keyword DS = new IBMASM_Keyword("DS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces spaces2;
		public IBMASM_Spaces spaces2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) IBMASM_DS_Value value;
		public IBMASM_DS_Value value;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces spaces3;
		public IBMASM_Spaces spaces3;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT IBMASM_Remark remark;
		public  OPT;

		public class IBMASM_DS_Value : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class IBMASM_DS_storage extends com.eagle.tokens.TokenSequence
			public class IBMASM_DS_storage : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT IBMASM_Number replication;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice code = new com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice("F");
				public IBMASM_KeywordChoice code = new IBMASM_KeywordChoice("F");
			}
		}
	}

}
