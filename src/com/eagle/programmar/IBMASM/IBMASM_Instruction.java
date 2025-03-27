// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 9, 2011

package com.eagle.programmar.IBMASM;

import com.eagle.programmar.IBMASM.Formats.IBMASM_Format_I;
import com.eagle.programmar.IBMASM.Formats.IBMASM_Format_R;
import com.eagle.programmar.IBMASM.Formats.IBMASM_Format_RI;
import com.eagle.programmar.IBMASM.Formats.IBMASM_Format_RR;
import com.eagle.programmar.IBMASM.Formats.IBMASM_Format_RRS;
import com.eagle.programmar.IBMASM.Formats.IBMASM_Format_RSS;
import com.eagle.programmar.IBMASM.Formats.IBMASM_Format_RX;
import com.eagle.programmar.IBMASM.Formats.IBMASM_Format_S;
import com.eagle.programmar.IBMASM.Formats.IBMASM_Format_SI;
import com.eagle.programmar.IBMASM.Formats.IBMASM_Format_SII;
import com.eagle.programmar.IBMASM.Formats.IBMASM_Format_SS;
import com.eagle.programmar.IBMASM.Symbols.IBMASM_Label_Definition;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Remark;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class IBMASM_Instruction extends TokenSequence
{
	public @S(10) @OPT IBMASM_Label_Definition label;
	public @S(20) IBMASM_Spaces spaces1;
	public @S(30) IBMASM_Instr instr;
	public @S(40) @OPT IBMASM_Spaces spaces2;
	public @S(50) @OPT IBMASM_Remark remark;

	public static class IBMASM_Instr extends TokenChooser
	{
		public @CHOICE IBMASM_Format_I XXimmediate;
		public @CHOICE IBMASM_Format_R XXregister;
		public @CHOICE IBMASM_Format_RI XXregisterImmediate;
		public @CHOICE IBMASM_Format_RR XXregisterRegister;
		public @CHOICE IBMASM_Format_RRS XXregisterRegisterAddress;
		public @CHOICE IBMASM_Format_RSS XXregisterAddressAddress;
		public @CHOICE IBMASM_Format_RX XXrx;
		public @CHOICE IBMASM_Format_S XXaddress;
		public @CHOICE IBMASM_Format_SI XXaddressImmediate;
		public @CHOICE IBMASM_Format_SII XXaddressImmediateImmediate;
		public @CHOICE IBMASM_Format_SS XXaddressAddress;
	}
}
