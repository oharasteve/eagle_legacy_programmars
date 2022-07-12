// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp;

import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class FSharp_Variable extends TokenSequence implements AbstractVariable
{
	public @S(10) Python_SelfOrVariable var;
	public @S(20) @OPT @NOSPACE TokenList<Python_Subscript> subscript1;
	public @S(30) @OPT @NOSPACE TokenList<Python_DotVariable> moreFields1;
	public @S(40) @OPT @NOSPACE TokenList<Python_Subscript> subscript2;
	public @S(50) @OPT @NOSPACE TokenList<Python_DotVariable> moreFields2;
	// public @S(60) @OPT @NOSPACE Python_ColonType colonType;
	
	public static class Python_SelfOrVariable extends TokenChooser
	{
		public @CHOICE Python_Keyword SELF = new Python_Keyword("self");
		public @CHOICE Python_Identifier_Reference id;
		public @CHOICE Python_DotVariable dotVariable;
		public @CHOICE Python_PunctuationChoice dotDot = new Python_PunctuationChoice("..", ".", "_1", "_2", "__", "_$", "_");
	}
	
	public static class Python_DotVariable extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationPeriod dot1;
		public @S(20) @NOSPACE @OPT PunctuationPeriod dot2;
		public @S(30) @NOSPACE Python_Identifier_Reference fld;
	}
	
	public static class Python_Subscript extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) @SYNTAX(Python_Multiline_Syntax.class) @NOSPACE Python_Subscript_Body body;
		public @S(30) @NOSPACE PunctuationRightBracket rightBracket;

		public static class Python_Subscript_Body extends TokenSequence
		{
			public @S(10) @OPT Python_EndOfLine eoln;
			public @S(20) SeparatedList<Python_Subscript_Dimension,PunctuationComma> dimensions;
		}

		public static class Python_Subscript_Dimension extends TokenSequence
		{
			public @S(10) @OPT FSharp_Expression subscr;
			public @S(20) @OPT Python_ColonSubscr colonStop;
			public @S(30) @OPT Python_ColonSubscr colonIncrement;

			public static class Python_ColonSubscr extends TokenSequence
			{
				public @S(10) PunctuationColon colon;
				public @S(20) @OPT Python_EndOfLine eoln;
				public @S(30) @OPT FSharp_Expression subscr;
			}
		}
	}
	
	public static class Python_ColonType extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) FSharp_Type type;
	}
}
