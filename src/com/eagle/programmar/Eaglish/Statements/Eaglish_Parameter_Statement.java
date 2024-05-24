// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Statements;

import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Eaglish_Type;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Parameter_Definition;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.TokenSequence;

public class Eaglish_Parameter_Statement extends TokenSequence
{
	public @S(10) Eaglish_Keyword PARAMETER = new Eaglish_Keyword("PARAMETER");
	public @S(20) Eaglish_Parameter_Definition param;
	public @S(30) Eaglish_Keyword IS = new Eaglish_Keyword("IS");
	public @S(40) Eaglish_Type type;
	public @S(50) @OPT Eaglish_ParemeterDefaultValue defaultValue;
	public @S(60) Eaglish_EndOfLine eoln;

	public static class Eaglish_ParemeterDefaultValue extends TokenSequence
	{
		public @S(10) Eaglish_Keyword DEFAULT = new Eaglish_Keyword("DEFAULT");
		public @S(20) Eaglish_Expression value;
	}
}
