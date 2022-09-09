// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 2, 2022

package com.eagle.programmar.C;

import com.eagle.programmar.CMacro.CMacro_StatementOrComment;
import com.eagle.programmar.CMacro.CMacro_Syntax;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class C_DataInitialValue extends TokenSequence
{
	public @S(10) @OPT @SYNTAX(CMacro_Syntax.class) CMacro_StatementOrComment macro1;
	public @S(20) PunctuationEquals equals;
	public @S(30) @OPT @SYNTAX(CMacro_Syntax.class) CMacro_StatementOrComment macro2;
	public @S(40) C_Expression expression;
	public @S(50) @OPT @SYNTAX(CMacro_Syntax.class) CMacro_StatementOrComment macro3;
}