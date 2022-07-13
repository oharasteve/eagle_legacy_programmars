// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp;

import com.eagle.programmar.FSharp.FSharp_Syntax.FSharp_Multiline_Syntax;
import com.eagle.programmar.FSharp.Symbols.FSharp_Identifier_Reference;
import com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine;
import com.eagle.programmar.FSharp.Terminals.FSharp_Keyword;
import com.eagle.programmar.FSharp.Terminals.FSharp_PunctuationChoice;
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
	public @S(10) FSharp_SelfOrVariable var;
	public @S(20) @OPT TokenList<FSharp_Subscript> subscript1;
	public @S(30) @OPT TokenList<FSharp_DotVariable> moreFields1;
	public @S(40) @OPT TokenList<FSharp_Subscript> subscript2;
	public @S(50) @OPT TokenList<FSharp_DotVariable> moreFields2;
	// public @S(60) @OPT FSharp_ColonType colonType;
	
	public static class FSharp_SelfOrVariable extends TokenChooser
	{
		public @CHOICE FSharp_Keyword SELF = new FSharp_Keyword("self");
		public @CHOICE FSharp_Identifier_Reference id;
		public @CHOICE FSharp_DotVariable dotVariable;
		public @CHOICE FSharp_PunctuationChoice dotDot = new FSharp_PunctuationChoice("..", ".", "_1", "_2", "__", "_$", "_");
	}
	
	public static class FSharp_DotVariable extends TokenSequence
	{
		public @S(10) PunctuationPeriod dot1;
		public @S(20) @OPT PunctuationPeriod dot2;
		public @S(30) FSharp_Identifier_Reference fld;
	}
	
	public static class FSharp_Subscript extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) @SYNTAX(FSharp_Multiline_Syntax.class) FSharp_Subscript_Body body;
		public @S(30) PunctuationRightBracket rightBracket;

		public static class FSharp_Subscript_Body extends TokenSequence
		{
			public @S(10) @OPT FSharp_EndOfLine eoln;
			public @S(20) SeparatedList<FSharp_Subscript_Dimension,PunctuationComma> dimensions;
		}

		public static class FSharp_Subscript_Dimension extends TokenSequence
		{
			public @S(10) @OPT FSharp_Expression subscr;
			public @S(20) @OPT FSharp_ColonSubscr colonStop;
			public @S(30) @OPT FSharp_ColonSubscr colonIncrement;

			public static class FSharp_ColonSubscr extends TokenSequence
			{
				public @S(10) PunctuationColon colon;
				public @S(20) @OPT FSharp_EndOfLine eoln;
				public @S(30) @OPT FSharp_Expression subscr;
			}
		}
	}
	
	public static class FSharp_ColonType extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) FSharp_Type type;
	}
}
