// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 3, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_GetStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("7.24") PLI_Keyword GET = new PLI_Keyword("GET");
	public @S(20) @OPT PLI_Keyword SKIP = new PLI_Keyword("SKIP");
	public @S(30) @OPT PLI_GetFile getFile;
	public @S(40) @OPT PLI_GetEdit getEdit;
	public @S(50) PunctuationSemicolon semicolon;

	public static class PLI_GetFile extends TokenSequence
	{
		public @S(10) PLI_Keyword FILE = new PLI_Keyword("FILE");
		public @S(20) PunctuationLeftParen leftParen1;
		public @S(30) PLI_Identifier_Reference fileName;
		public @S(40) PunctuationRightParen rightParen1;
	}

	public static class PLI_GetEdit extends TokenSequence
	{
		public @S(10) PLI_Keyword EDIT = new PLI_Keyword("EDIT");
		public @S(20) PunctuationLeftParen leftParen2;
		public @S(30) SeparatedList<PLI_Expression, PunctuationComma> exprs;
		public @S(40) PunctuationRightParen rightParen2;
		public @S(50) PLI_Punctuation leftParen3 = new PLI_Punctuation('(');
		public @S(60) SeparatedList<PLI_GetFormat, PunctuationComma> formats;
		public @S(70) PLI_Punctuation rightParen3 = new PLI_Punctuation(')');

		public static class PLI_Get_Subscript extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) PLI_Expression expr;
			public @S(30) PunctuationRightParen rightParen;
		}

		public static class PLI_GetFormat extends TokenSequence
		{
			public @S(10) @OPT PLI_GetFormat_Count formatCount;
			public @S(20) PLI_KeywordChoice formatCode = new PLI_KeywordChoice("A", "F", "L", "X");
			public @S(30) @OPT PLI_GetFormat_Count formatSize;
		}

		public static class PLI_GetFormat_Count extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) PLI_Expression count;
			public @S(30) PunctuationRightParen rightParen;
		}
	}
}
