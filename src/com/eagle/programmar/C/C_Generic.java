// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2022

package com.eagle.programmar.C;

import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.programmar.C.Terminals.C_Number;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_Generic extends TokenSequence
{
	public @S(10) C_Punctuation lessThan = new C_Punctuation('<');
	public @S(20) SeparatedList<C_GenericType,PunctuationComma> types;
	public @S(30) C_Punctuation greaterThan = new C_Punctuation('>');
	
	public static class C_GenericType extends TokenChooser
	{
		public @LAST C_Number number;
		public @LAST C_Literal literal;
		
		public @CHOICE static class C_Plus_GenericType extends TokenSequence
		{
			public @S(10) C_Type type;
			public @S(20) @OPT C_Punctuation ellipsis = new C_Punctuation("...");
		}
		
		public @FIRST static class C_Plus_GenericVoid extends TokenSequence
		{
			public @S(10) C_Keyword VOID = new C_Keyword("void");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) @OPT SeparatedList<C_Type,PunctuationComma> types;
			public @S(40) PunctuationRightParen rightParen;
		}
	}
}
