// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Statements;

import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Algol68_Type;
import com.eagle.programmar.Algol68.Symbols.Algol68_Variable_Definition;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Algol68_Data extends TokenSequence
{
	public @S(10) Algol68_Type type;
	public @S(20) SeparatedList<Algol68_Variable_Definition, PunctuationComma> ids;
	public @S(30) @OPT Algol68_DataInitialValue init;
	public @S(40) PunctuationSemicolon semicolon;

	public static class Algol68_DataInitialValue extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) Algol68_Expression value;
	}
}
