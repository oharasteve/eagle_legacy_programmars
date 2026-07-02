// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 26, 2011

package com.eagle.programmar.PLI;

import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Symbols.PLI_Variable_Definition;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_DeclareGeneric extends TokenSequence
{
	public @S(10) PLI_Keyword DECLARE = new PLI_Keyword("DECLARE");
	public @S(20) PLI_Variable_Definition id;
	public @S(30) PLI_Keyword GENERIC = new PLI_Keyword("GENERIC");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) SeparatedList<PLI_GenericWhen, PunctuationComma> whens;
	public @S(60) PunctuationRightParen rightParen;
	public @S(70) PunctuationSemicolon semicolon;

	public static class PLI_GenericWhen extends TokenSequence
	{
		public @S(10) PLI_Identifier_Reference id;
		public @S(20) PLI_Keyword WHEN = new PLI_Keyword("WHEN");
		public @S(30) PunctuationLeftParen leftParen;
		public @S(40) @OPT SeparatedList<PLI_WhenType, PunctuationComma> whens;
		public @S(50) PunctuationRightParen rightParen;
	}

	public static class PLI_WhenType extends TokenChooser
	{
		public @CHOICE PLI_Type XXtype;
		
		public @CHOICE static class PLI_WhenTypeList extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) @OPT SeparatedList<PLI_Type, PunctuationComma> types;
			public @S(30) PunctuationRightParen rightParen;
			public @S(40) @OPT  PLI_Type type;
		}
	}
}
