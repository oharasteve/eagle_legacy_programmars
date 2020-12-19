// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 13, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class COBOL_MergeStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rlpsmerg.htm") COBOL_Keyword MERGE = new COBOL_Keyword("MERGE");
	public @S(20) COBOL_Identifier_Reference file;
	public @S(30) COBOL_Keyword ON = new COBOL_Keyword("ON");
	public @S(40) COBOL_Keyword ASCENDING = new COBOL_Keyword("ASCENDING");
	public @S(50) @OPT COBOL_Keyword KEY = new COBOL_Keyword("KEY");
	public @S(60) COBOL_Identifier_Reference key;
	public @S(70) @OPT TokenList<COBOL_MergeKey> moreKeys;
	public @S(80) @OPT COBOL_MergeUsing using;
	public @S(90) @OPT COBOL_MergeGiving giving;

	public static class COBOL_MergeKey extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) COBOL_Identifier_Reference key;
	}
	
	public static class COBOL_MergeUsing extends TokenSequence
	{
		public @S(10) COBOL_Keyword USING = new COBOL_Keyword("USING");
		public @S(20) COBOL_Identifier_Reference using;
		public @S(30) @OPT TokenList<COBOL_MergeMoreUsing> moreUsings;

		public static class COBOL_MergeMoreUsing extends TokenSequence
		{
			public @S(10) @OPT PunctuationComma comma;
			public @S(20) COBOL_Identifier_Reference using;
		}
	}
	
	public static class COBOL_MergeGiving extends TokenSequence
	{
		public @S(10) COBOL_Keyword GIVING = new COBOL_Keyword("GIVING");
		public @S(20) COBOL_Identifier_Reference giving;
	}
}
