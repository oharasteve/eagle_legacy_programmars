// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 9, 2011

namespace com.eagle.programmar.IBMASM
{
	using IBMASM_Format_I = com.eagle.programmar.IBMASM.Formats.IBMASM_Format_I;
	using IBMASM_Format_R = com.eagle.programmar.IBMASM.Formats.IBMASM_Format_R;
	using IBMASM_Format_RI = com.eagle.programmar.IBMASM.Formats.IBMASM_Format_RI;
	using IBMASM_Format_RR = com.eagle.programmar.IBMASM.Formats.IBMASM_Format_RR;
	using IBMASM_Format_RRS = com.eagle.programmar.IBMASM.Formats.IBMASM_Format_RRS;
	using IBMASM_Format_RSS = com.eagle.programmar.IBMASM.Formats.IBMASM_Format_RSS;
	using IBMASM_Format_RX = com.eagle.programmar.IBMASM.Formats.IBMASM_Format_RX;
	using IBMASM_Format_S = com.eagle.programmar.IBMASM.Formats.IBMASM_Format_S;
	using IBMASM_Format_SI = com.eagle.programmar.IBMASM.Formats.IBMASM_Format_SI;
	using IBMASM_Format_SII = com.eagle.programmar.IBMASM.Formats.IBMASM_Format_SII;
	using IBMASM_Format_SS = com.eagle.programmar.IBMASM.Formats.IBMASM_Format_SS;
	using IBMASM_Label_Definition = com.eagle.programmar.IBMASM.Symbols.IBMASM_Label_Definition;
	using IBMASM_Remark = com.eagle.programmar.IBMASM.Terminals.IBMASM_Remark;
	using IBMASM_Spaces = com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class IBMASM_Instruction : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT IBMASM_Label_Definition label;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces spaces1;
		public IBMASM_Spaces spaces1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) IBMASM_Instr instr;
		public IBMASM_Instr instr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT IBMASM_Spaces spaces2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT IBMASM_Remark remark;
		public  OPT;

		public class IBMASM_Instr : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_Format_I XXimmediate;
			public IBMASM_Format_I XXimmediate;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_Format_R XXregister;
			public IBMASM_Format_R XXregister;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_Format_RI XXregisterImmediate;
			public IBMASM_Format_RI XXregisterImmediate;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_Format_RR XXregisterRegister;
			public IBMASM_Format_RR XXregisterRegister;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_Format_RRS XXregisterRegisterAddress;
			public IBMASM_Format_RRS XXregisterRegisterAddress;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_Format_RSS XXregisterAddressAddress;
			public IBMASM_Format_RSS XXregisterAddressAddress;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_Format_RX XXrx;
			public IBMASM_Format_RX XXrx;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_Format_S XXaddress;
			public IBMASM_Format_S XXaddress;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_Format_SI XXaddressImmediate;
			public IBMASM_Format_SI XXaddressImmediate;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_Format_SII XXaddressImmediateImmediate;
			public IBMASM_Format_SII XXaddressImmediateImmediate;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_Format_SS XXaddressAddress;
			public IBMASM_Format_SS XXaddressAddress;
		}
	}

}
