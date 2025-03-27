// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 16, 2022

package com.eagle.programmar.IntelASM;

import com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
import com.eagle.tokens.TokenChooser;

public class IntelASM_Register extends TokenChooser
{
	public @CHOICE IntelASM_KeywordChoice XXreg = new IntelASM_KeywordChoice("EAX", "EBX", "ECX", "EDX", "ESI", "EDI",
			"EBP", "ESP", "CS", "DS", "SS", "ES", "FS", "GS", "AH", "AL", "BH", "BL", "CH", "CL", "DH", "DL", "AX",
			"BX", "CX", "DX", "BP", "SI", "DI", "SP", "RDI", "RSI");
}
