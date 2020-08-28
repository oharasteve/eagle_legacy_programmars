// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2020

package com.eagle.programmar.CMacro.Statements;

import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.programmar.C.Terminals.C_Number;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CMacro_LineNumber_Statement extends TokenSequence
{
	public C_Punctuation pound = new C_Punctuation('#');
	public C_Number lineNumber;
	public C_Literal fileName;
	public @OPT TokenList<C_Number> moreNumbers;
}
