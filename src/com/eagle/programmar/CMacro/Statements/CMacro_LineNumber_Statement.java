// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2020

package com.eagle.programmar.CMacro.Statements;

import com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine;
import com.eagle.programmar.CMacro.Terminals.CMacro_Literal;
import com.eagle.programmar.CMacro.Terminals.CMacro_Number;
import com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CMacro_LineNumber_Statement extends TokenSequence
{
	public @S(10) CMacro_Punctuation pound = new CMacro_Punctuation('#');
	public @S(20) CMacro_Number lineNumber;
	public @S(30) CMacro_Literal fileName;
	public @S(40) @OPT TokenList<CMacro_Number> moreNumbers;
	public @S(50) CMacro_EndOfLine endOfLine;
}
