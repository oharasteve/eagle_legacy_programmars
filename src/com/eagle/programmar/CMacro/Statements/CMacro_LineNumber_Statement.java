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
	public @S(10) C_Punctuation pound = new C_Punctuation('#');
	public @S(20) C_Number lineNumber;
	public @S(30) C_Literal fileName;
	public @S(40) @OPT TokenList<C_Number> moreNumbers;
}
