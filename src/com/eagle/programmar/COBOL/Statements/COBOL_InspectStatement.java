// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 13, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_InspectStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rlpsinsp.htm") COBOL_Keyword INSPECT = new COBOL_Keyword("INSPECT");
	public @S(20) COBOL_Expression expr;
	public @S(30) COBOL_InspectType type;

	public static class COBOL_InspectType extends TokenChooser
	{
		public @CHOICE static class COBOL_InspectReplacing extends TokenSequence
		{
			public @S(10) COBOL_Keyword REPLACING = new COBOL_Keyword("REPLACING");
			public @S(20) TokenList<COBOL_InspectReplacePattern> patterns;

			public static class COBOL_InspectReplacePattern extends TokenSequence
			{
				public @S(10) COBOL_KeywordChoice FIRST = new COBOL_KeywordChoice("ALL", "FIRST", "LEADING");
				public @S(20) COBOL_Expression pattern;
				public @S(30) COBOL_Keyword BY = new COBOL_Keyword("BY");
				public @S(40) COBOL_Expression replacement;
			}
		}

		public @CHOICE static class COBOL_InspectConverting extends TokenSequence
		{
			public @S(10) COBOL_Keyword CONVERTING = new COBOL_Keyword("CONVERTING");
			public @S(20) COBOL_InspectConvert convertFrom;
			public @S(30) COBOL_Keyword TO = new COBOL_Keyword("TO");
			public @S(40) COBOL_InspectConvert convertTo;

			public static class COBOL_InspectConvert extends TokenChooser
			{
				public @CHOICE COBOL_Literal literal;
				public @CHOICE COBOL_KeywordChoice cases = new COBOL_KeywordChoice("LOWER-CASE", "LowerCase",
						"UPPER-CASE", "UpperCase");
			}
		}

		public @CHOICE static class COBOL_InspectTallying extends TokenSequence
		{
			public @S(10) COBOL_Keyword TALLYING = new COBOL_Keyword("TALLYING");
			public @S(20) TokenList<COBOL_InspectTally> tallies;

			public static class COBOL_InspectTally extends TokenSequence
			{
				public @S(10) COBOL_Identifier_Reference var;
				public @S(20) COBOL_Keyword FOR = new COBOL_Keyword("FOR");
				public @S(30) COBOL_InpsectTallyingWhat forWhat;
			}

			public static class COBOL_InpsectTallyingWhat extends TokenChooser
			{
				public @CHOICE static class COBOL_InspectTallyingAllLiterals extends TokenSequence
				{
					public @S(10) COBOL_Keyword ALL = new COBOL_Keyword("ALL");
					public @S(20) TokenList<COBOL_Literal> literals;
				}

				public @LAST static class COBOL_InspectTallyingAllExpr extends TokenSequence
				{
					public @S(10) COBOL_Keyword ALL = new COBOL_Keyword("ALL");
					public @S(20) COBOL_Expression expr;
				}

				public @CHOICE static class COBOL_InspectTallyingSpaces extends TokenSequence
				{
					public @S(10) COBOL_Keyword LEADING = new COBOL_Keyword("LEADING");
					public @S(20) COBOL_Keyword SPACES = new COBOL_Keyword("SPACES");
				}

				public @CHOICE static class COBOL_InspectTallyingCharacters extends TokenSequence
				{
					public @S(10) COBOL_Keyword CHARACTERS = new COBOL_Keyword("CHARACTERS");
					public @S(20) COBOL_Keyword BEFORE = new COBOL_Keyword("BEFORE");
					public @S(30) COBOL_Keyword INITIAL = new COBOL_Keyword("INITIAL");
					public @S(40) COBOL_Expression expr;
				}
			}
		}
	}
}
