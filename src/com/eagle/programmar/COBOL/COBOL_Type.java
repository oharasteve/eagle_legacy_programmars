// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 19, 2022

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Picture.COBOL_ObjectReference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class COBOL_Type extends TokenChooser
{
	public @CHOICE COBOL_ObjectReference objectReference;

	public @CHOICE COBOL_KeywordChoice type = new COBOL_KeywordChoice("1-RECTL", "2SIZE", "BINARY-LONG", "BYTE",
			"CLIPFORMAT", "CLSID", "COMP", "COMP-0", "COMP-3", "COMP-5", "COMP-X", "DATA-POINTER", "DWORD", "FILETIME",
			"FLOAT-LONG", "FORMATETC", "IID", "LONG", "POINT", "POINTER", "PROCEDURE-POINTER", "TAGMSG", "TAGPOINT",
			"TAGRECT", "TAGSIZE", "UINT", "ULARGE-INTEGER", "ULONG", "USHORT", "VARTYPE", "WORD", "WRAPPED-BYTE");

	public @CHOICE static class COBOL_TypeType extends TokenSequence
	{
		public @S(10) COBOL_Keyword TYPE = new COBOL_Keyword("TYPE");
		public @S(20) COBOL_Literal typename;
	}
}
