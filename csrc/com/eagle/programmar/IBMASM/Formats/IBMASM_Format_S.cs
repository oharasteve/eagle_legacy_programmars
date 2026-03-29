// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 12, 2011

namespace com.eagle.programmar.IBMASM.Formats
{
	using IBMASM_Address = com.eagle.programmar.IBMASM.IBMASM_Address;
	using IBMASM_KeywordChoice = com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice;
	using IBMASM_Spaces = com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class IBMASM_Format_S : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice opcode = new com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice("B", "BE", "BNE", "BNH", "BNL", "NOP", "STCK");
		public IBMASM_KeywordChoice opcode = new IBMASM_KeywordChoice("B", "BE", "BNE", "BNH", "BNL", "NOP", "STCK");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces spaces;
		public IBMASM_Spaces spaces;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.IBMASM.IBMASM_Address address;
		public IBMASM_Address address;
	}

}
