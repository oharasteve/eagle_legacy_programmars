// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 10, 2011

package com.eagle.programmar.IBMASM.Directives;

import com.eagle.programmar.IBMASM.Terminals.IBMASM_Keyword;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Register;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationStar;

public class IBMASM_USING_Directive extends TokenSequence
{
	public @S(10) IBMASM_Keyword USING = new IBMASM_Keyword("USING");
	public @S(20) IBMASM_Spaces spaces;
	public @S(30) PunctuationStar star;
	public @S(40) PunctuationComma comma;
	public @S(50) IBMASM_Register register;
}
