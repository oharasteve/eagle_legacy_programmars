// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 9, 2011

package com.eagle.programmar.IBMASM;

import com.eagle.core.EagleLanguage;
import com.eagle.programmar.IBMASM.Data.IBMASM_DC_Instruction;
import com.eagle.programmar.IBMASM.Data.IBMASM_DS_Instruction;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Comment;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_EndOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class IBMASM_Program extends EagleLanguage
{
	public static final String IBMASM = "IBMASM";

	public IBMASM_Program()
	{
		super(IBMASM, new IBMASM_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://publibz.boulder.ibm.com/bookmgr_OS390/libraryserver/zosv1r7/";
	}

	public @S(10) TokenList<IBMASM_Line> lines;

	public static class IBMASM_Line extends TokenSequence
	{
		public @S(10) IBMASM_LineContents contents;
		public @S(20) IBMASM_EndOfLine eoln;
	}

	public static class IBMASM_LineContents extends TokenChooser
	{
		public @CHOICE IBMASM_Comment XXcomment;
		public @CHOICE IBMASM_Instruction XXinstruction;
		public @CHOICE IBMASM_Directive XXdirective;
		public @CHOICE IBMASM_Macro XXmacro;
		public @CHOICE IBMASM_DC_Instruction XXdefineConstant;
		public @CHOICE IBMASM_DS_Instruction XXdeclareStorage;
	}
}
