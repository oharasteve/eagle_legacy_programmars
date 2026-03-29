// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 12, 2011

namespace com.eagle.programmar.IBMASM.Data
{
	using IBMASM_Address = com.eagle.programmar.IBMASM.IBMASM_Address;
	using IBMASM_Label_Definition = com.eagle.programmar.IBMASM.Symbols.IBMASM_Label_Definition;
	using IBMASM_Keyword = com.eagle.programmar.IBMASM.Terminals.IBMASM_Keyword;
	using IBMASM_KeywordChoice = com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice;
	using IBMASM_Literal = com.eagle.programmar.IBMASM.Terminals.IBMASM_Literal;
	using IBMASM_Number = com.eagle.programmar.IBMASM.Terminals.IBMASM_Number;
	using IBMASM_Remark = com.eagle.programmar.IBMASM.Terminals.IBMASM_Remark;
	using IBMASM_Spaces = com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class IBMASM_DC_Instruction : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT IBMASM_Label_Definition label;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces spaces1;
		public IBMASM_Spaces spaces1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.IBMASM.Terminals.IBMASM_Keyword DC = new com.eagle.programmar.IBMASM.Terminals.IBMASM_Keyword("DC");
		public IBMASM_Keyword DC = new IBMASM_Keyword("DC");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces spaces2;
		public IBMASM_Spaces spaces2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) IBMASM_DC_Value value;
		public IBMASM_DC_Value value;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces spaces3;
		public IBMASM_Spaces spaces3;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT IBMASM_Remark remark;
		public  OPT;

		public class IBMASM_DC_Value : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class IBMASM_DC_Address extends com.eagle.tokens.TokenSequence
			public class IBMASM_DC_Address : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IBMASM.Terminals.IBMASM_Keyword A = new com.eagle.programmar.IBMASM.Terminals.IBMASM_Keyword("A");
				public IBMASM_Keyword A = new IBMASM_Keyword("A");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.IBMASM.IBMASM_Address address;
				public IBMASM_Address address;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class IBMASM_DC_Length extends com.eagle.tokens.TokenSequence
			public class IBMASM_DC_Length : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT IBMASM_Number replication;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice code = new com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice("CL", "XL");
				public IBMASM_KeywordChoice code = new IBMASM_KeywordChoice("CL", "XL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.IBMASM.Terminals.IBMASM_Number number;
				public IBMASM_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.IBMASM.Terminals.IBMASM_Literal literal;
				public IBMASM_Literal literal;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class IBMASM_DC_Literal extends com.eagle.tokens.TokenSequence
			public class IBMASM_DC_Literal : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice code = new com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice("B", "C", "E", "F", "G", "H", "P", "X");
				public IBMASM_KeywordChoice code = new IBMASM_KeywordChoice("B", "C", "E", "F", "G", "H", "P", "X");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IBMASM.Terminals.IBMASM_Literal literal;
				public IBMASM_Literal literal;
			}
		}
	}

}
