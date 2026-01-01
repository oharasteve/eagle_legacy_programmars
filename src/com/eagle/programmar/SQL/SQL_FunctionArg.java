// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 31, 2025

package com.eagle.programmar.SQL;

import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class SQL_FunctionArg extends TokenChooser
{
	public @CHOICE SQL_Expression XXarg;

	public @CHOICE static class SQL_FunctionColonParam extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) SQL_Expression arg;
	}

	public @FIRST static class SQL_FunctionNamedArg extends TokenSequence
	{
		public @S(10) SQL_Identifier_Reference parameterName;
		public @S(20) SQL_Punctuation equalsGreater = new SQL_Punctuation("=>");
		public @S(30) SQL_Expression arg;
	}
}
