// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 25, 2024

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Python_Subscript extends TokenSequence
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) @OPT Python_EndOfLine eoln;
	public @S(30) @SYNTAX(Python_Multiline_Syntax.class) Python_SubscrExpr body;
	public @S(40) PunctuationRightBracket rightBracket;

	public static class Python_SubscrExpr extends TokenSequence
	{
		public @S(10) @OPT Python_Expression subscr;
		public @S(20) @OPT Python_ColonSubscript subscriptStop;
		public @S(30) @OPT Python_ColonSubscript subscriptStep;
	}

	public static class Python_ColonSubscript extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) @OPT Python_EndOfLine eoln;
		public @S(30) @OPT Python_Expression expr;
	}
}
