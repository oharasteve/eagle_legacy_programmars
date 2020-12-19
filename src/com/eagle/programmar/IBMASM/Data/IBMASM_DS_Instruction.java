// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 12, 2011

package com.eagle.programmar.IBMASM.Data;

import com.eagle.programmar.IBMASM.Symbols.IBMASM_Label_Definition;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Keyword;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Number;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Remark;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class IBMASM_DS_Instruction extends TokenSequence
{
	public @S(10) @OPT IBMASM_Label_Definition label;
	public @S(20) IBMASM_Spaces spaces1;
	public @S(30) IBMASM_Keyword DS = new IBMASM_Keyword("DS");
	public @S(40) IBMASM_Spaces spaces2;
	public @S(50) IBMASM_DS_Value value;
	public @S(60) IBMASM_Spaces spaces3;
	public @S(70) @OPT IBMASM_Remark remark;

	public static class IBMASM_DS_Value extends TokenChooser
	{
		public @CHOICE static class IBMASM_DS_storage extends TokenSequence
		{
			public @S(10) @OPT IBMASM_Number replication;
			public @S(20) IBMASM_KeywordChoice code = new IBMASM_KeywordChoice("F");
		}
	}
}
