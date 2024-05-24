// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.Lisp;

import com.eagle.programmar.Lisp.Symbols.Lisp_Identifier_Reference;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_Variable extends TokenChooser
{
	public @CHOICE Lisp_Identifier_Reference var;

	public @CHOICE static class Lisp_VariableWithDot extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Lisp_Identifier_Reference var1;
		public @S(30) PunctuationPeriod dot;
		public @S(40) Lisp_Identifier_Reference var2;
		public @S(50) PunctuationRightParen rightParen;
	}
}
