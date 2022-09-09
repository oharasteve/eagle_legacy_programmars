// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.JavaP.Parameters;

import com.eagle.programmar.JavaP.JavaP_MethodArgument;
import com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class JavaP_OneClassGeneric extends TokenSequence
{
	public @S(10) JavaP_Punctuation lessThan = new JavaP_Punctuation('<');
	public @S(20) SeparatedList<JavaP_MethodArgument,PunctuationComma> names;
	public @S(30) JavaP_Punctuation greaterThan = new JavaP_Punctuation('>');
}