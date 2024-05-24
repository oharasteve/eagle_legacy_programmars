// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_AllocateStatement extends TokenSequence
{
	public @S(10) @DOC("7.2") PLI_Keyword ALLOCATE = new PLI_Keyword("ALLOCATE");
	public @S(20) PLI_AllocateVariable var;
	public @S(30) @OPT TokenList<PLI_Allocate_MoreVars> moreIds;
	public @S(40) PunctuationSemicolon semicolon;

	public static class PLI_AllocateVariable extends TokenSequence
	{
		public @S(10) PLI_Identifier_Reference id;
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) SeparatedList<PLI_AllocateSize, PunctuationComma> sizes;
		public @S(40) PunctuationRightParen rightParen;
	}

	public static class PLI_AllocateSize extends TokenSequence
	{
		public @S(10) PLI_Expression size1;
		public @S(20) @OPT PunctuationColon colon;
		public @S(30) @OPT PLI_Expression size2;
	}

	public static class PLI_Allocate_MoreVars extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) PLI_AllocateVariable var;
	}
}
