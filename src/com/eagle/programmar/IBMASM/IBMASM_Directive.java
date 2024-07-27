// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 9, 2011

package com.eagle.programmar.IBMASM;

import com.eagle.programmar.IBMASM.Directives.IBMASM_CSECT_Directive;
import com.eagle.programmar.IBMASM.Directives.IBMASM_EJECT_Directive;
import com.eagle.programmar.IBMASM.Directives.IBMASM_END_Directive;
import com.eagle.programmar.IBMASM.Directives.IBMASM_EQU_Directive;
import com.eagle.programmar.IBMASM.Directives.IBMASM_LTORG_Directive;
import com.eagle.programmar.IBMASM.Directives.IBMASM_USING_Directive;
import com.eagle.programmar.IBMASM.Symbols.IBMASM_Label_Definition;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Remark;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class IBMASM_Directive extends TokenSequence
{
	public @S(10) @OPT IBMASM_Label_Definition label;
	public @S(20) IBMASM_Spaces spaces1;
	public @S(30) IBMASM_Direct directive;
	public @S(40) @OPT IBMASM_Spaces spaces2;
	public @S(50) @OPT IBMASM_Remark remark;

	public static class IBMASM_Direct extends TokenChooser
	{
		public @CHOICE IBMASM_CSECT_Directive XXCSECT_directive;
		public @CHOICE IBMASM_EJECT_Directive XXEJECT_directive;
		public @CHOICE IBMASM_END_Directive XXEND_Directive;
		public @CHOICE IBMASM_EQU_Directive XXEQU_Directive;
		public @CHOICE IBMASM_LTORG_Directive XXLTORG_directive;
		public @CHOICE IBMASM_USING_Directive XXUSING_Directive;
	}
}
