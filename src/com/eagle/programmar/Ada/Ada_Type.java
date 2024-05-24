// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada;

import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.programmar.Ada.Terminals.Ada_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Ada_Type extends TokenSequence
{
	public @S(10) @OPT Ada_Keyword CONSTANT = new Ada_Keyword("constant");
	public @S(20) Ada_WhichType which;

	public static class Ada_WhichType extends TokenChooser
	{
		public @CHOICE Ada_KeywordChoice primitives = new Ada_KeywordChoice("Boolean", "Integer", "Unbounded_String");

		public @CHOICE static class Ada_ArrayType extends TokenSequence
		{
			public @S(10) Ada_Keyword ARRAY = new Ada_Keyword("array");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) Ada_Expression range;
			public @S(40) PunctuationRightParen rightParen;
			public @S(50) Ada_Keyword OF = new Ada_Keyword("of");
			public @S(60) Ada_Type type;
		}
	}
}
