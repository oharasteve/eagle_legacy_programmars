// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 12, 2011

namespace com.eagle.programmar.IBMASM.Formats
{
	using IBMASM_KeywordChoice = com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice;
	using IBMASM_Register = com.eagle.programmar.IBMASM.Terminals.IBMASM_Register;
	using IBMASM_Spaces = com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class IBMASM_Format_RR : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice opcode = new com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice("ALR", "AR", "ADR", "AER", "CLR", "CR", "CDR", "CER", "DR", "DDR", "DER", "HDR", "HER", "LCR", "LCDR", "LCER", "LNR", "LNDR", "LNER", "LPR", "LPDR", "LPER", "LR", "LDR", "LER", "LTR", "LTDR", "LTER", "MR", "MDR", "MER", "NR", "OR", "SLR", "SR", "SDR", "SER", "XR", "BALR", "BCTR", "BCR", "ISK", "SPM", "SSK", "BASR", "CLCL", "MVCL");
		public IBMASM_KeywordChoice opcode = new IBMASM_KeywordChoice("ALR", "AR", "ADR", "AER", "CLR", "CR", "CDR", "CER", "DR", "DDR", "DER", "HDR", "HER", "LCR", "LCDR", "LCER", "LNR", "LNDR", "LNER", "LPR", "LPDR", "LPER", "LR", "LDR", "LER", "LTR", "LTDR", "LTER", "MR", "MDR", "MER", "NR", "OR", "SLR", "SR", "SDR", "SER", "XR", "BALR", "BCTR", "BCR", "ISK", "SPM", "SSK", "BASR", "CLCL", "MVCL");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces spaces;
		public IBMASM_Spaces spaces;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.IBMASM.Terminals.IBMASM_Register register1;
		public IBMASM_Register register1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationComma comma;
		public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.IBMASM.Terminals.IBMASM_Register register2;
		public IBMASM_Register register2;
	}

}
