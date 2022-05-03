// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 2, 2022

package com.eagle.programmar.C;

import com.eagle.programmar.C.C_Function.C_Function_ParameterDefs;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class C_Lambda extends TokenSequence
{
	public @S(10) @OPT C_LambdaBrackets brackets;
	public @S(20) C_Function_ParameterDefs parameters;
	public @S(30) C_Punctuation arrow = new C_Punctuation("->");
	public @S(40) C_Type type;
	public @S(50) C_Statement statement;
	
	public static class C_LambdaBrackets extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) PunctuationRightBracket rightBracket;
	}
}
