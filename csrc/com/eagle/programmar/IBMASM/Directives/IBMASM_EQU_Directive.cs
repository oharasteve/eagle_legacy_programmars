// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 11, 2011

namespace com.eagle.programmar.IBMASM.Directives
{
	using IBMASM_Immediate = com.eagle.programmar.IBMASM.IBMASM_Immediate;
	using IBMASM_Keyword = com.eagle.programmar.IBMASM.Terminals.IBMASM_Keyword;
	using IBMASM_Spaces = com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationStar = com.eagle.tokens.punctuation.PunctuationStar;

	public class IBMASM_EQU_Directive : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IBMASM.Terminals.IBMASM_Keyword EQU = new com.eagle.programmar.IBMASM.Terminals.IBMASM_Keyword("EQU");
		public IBMASM_Keyword EQU = new IBMASM_Keyword("EQU");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces spaces;
		public IBMASM_Spaces spaces;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) IBMASM_EQU_What what;
		public IBMASM_EQU_What what;

		public class IBMASM_EQU_What : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationStar XXstar;
			public PunctuationStar XXstar;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_Immediate XXimmediage;
			public IBMASM_Immediate XXimmediage;
		}
	}

}
