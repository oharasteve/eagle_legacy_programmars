// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class COBOL_SortStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rlpssort.htm") COBOL_Keyword SORT = new COBOL_Keyword("SORT");
	public @S(20) COBOL_Identifier_Reference file;
	public @S(30) COBOL_Keyword ON = new COBOL_Keyword("ON");
	public @S(40) COBOL_KeywordChoice ASCENDING = new COBOL_KeywordChoice("ASCENDING", "DESCENDING");
	public @S(50) @OPT COBOL_Keyword KEY = new COBOL_Keyword("KEY");
	public @S(60) COBOL_Identifier_Reference key;
	public @S(70) @OPT TokenList<COBOL_SortKey> moreKeys;
	public @S(80) @OPT COBOL_SortInput input;
	public @S(90) @OPT COBOL_SortUsing using;
	public @S(100) @OPT COBOL_SortOutput output;
	public @S(110) @OPT COBOL_SortGiving giving;

	public static class COBOL_SortKey extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) COBOL_Identifier_Reference key;
	}

	public static class COBOL_SortInput extends TokenSequence
	{
		public @S(10) COBOL_Keyword INPUT = new COBOL_Keyword("INPUT");
		public @S(20) COBOL_Keyword PROCEDURE = new COBOL_Keyword("PROCEDURE");
		public @S(30) COBOL_Keyword IS = new COBOL_Keyword("IS");
		public @S(40) COBOL_Identifier_Reference inProc;
	}

	public static class COBOL_SortOutput extends TokenSequence
	{
		public @S(10) COBOL_Keyword OUTPUT = new COBOL_Keyword("OUTPUT");
		public @S(20) COBOL_Keyword PROCEDURE = new COBOL_Keyword("PROCEDURE");
		public @S(30) COBOL_Keyword IS = new COBOL_Keyword("IS");
		public @S(40) COBOL_Identifier_Reference outProc;
	}

	public static class COBOL_SortUsing extends TokenSequence
	{
		public @S(10) COBOL_Keyword USING = new COBOL_Keyword("USING");
		public @S(20) COBOL_Identifier_Reference file;
	}

	public static class COBOL_SortGiving extends TokenSequence
	{
		public @S(10) COBOL_Keyword GIVING = new COBOL_Keyword("GIVING");
		public @S(20) COBOL_Identifier_Reference file;
	}
}
