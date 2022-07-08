// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

package com.eagle.programmar.JavaP.Constants;

import com.eagle.programmar.JavaP.Symbols.JavaP_Symbol_Reference;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_Number;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class JavaP_ConstantMethodHandle extends TokenSequence
{
	public @S(10) JavaP_Keyword METHODHANDLE = new JavaP_Keyword("MethodHandle");
	public @S(20) JavaP_Number number;
	public @S(30) PunctuationColon colon;
	public @S(40) JavaP_Symbol_Reference field;
}
