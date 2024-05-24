// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL;

import com.eagle.programmar.TCL.Symbols.TCL_Identifier_Reference;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class TCL_Variable extends TokenSequence
{
	public @S(10) TCL_Identifier_Reference id;
	public @S(20) @OPT TCL_Subscript subscript;

	public static class TCL_Subscript extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) TCL_Expression expr;
		public @S(30) PunctuationRightParen rightParen;
	}
}
