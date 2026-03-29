// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 13, 2011

namespace com.eagle.programmar.IBMASM
{
	using IBMASM_KeywordChoice = com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice;
	using IBMASM_Literal = com.eagle.programmar.IBMASM.Terminals.IBMASM_Literal;
	using IBMASM_Number = com.eagle.programmar.IBMASM.Terminals.IBMASM_Number;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class IBMASM_Immediate : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_Number XXnumber;
		public IBMASM_Number XXnumber;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_Address XXaddress;
		public IBMASM_Address XXaddress;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class IBMASM_Immediate_Data extends com.eagle.tokens.TokenSequence
		public class IBMASM_Immediate_Data : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice code = new com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice("C", "X");
			public IBMASM_KeywordChoice code = new IBMASM_KeywordChoice("C", "X");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IBMASM.Terminals.IBMASM_Literal literal;
			public IBMASM_Literal literal;
		}
	}

}
