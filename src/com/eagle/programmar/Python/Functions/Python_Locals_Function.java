// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 26, 2024

package com.eagle.programmar.Python.Functions;

import com.eagle.programmar.Python.Terminals.Python_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Python_Locals_Function extends PrimaryOperator
{
	public @S(10) Python_KeywordChoice LOCALS = new Python_KeywordChoice("globals", "locals");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE PunctuationRightParen rightParen;
}
