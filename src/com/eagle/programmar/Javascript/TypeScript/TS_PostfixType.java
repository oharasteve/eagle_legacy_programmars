// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 2, 2026: TypeScript is JavaScript plus types. The type
// syntax lives here; the JavaScript rules carry optional hooks to it, so one grammar reads both.

package com.eagle.programmar.Javascript.TypeScript;

import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

/** A primary type with any number of [] and [index] after it. */
public class TS_PostfixType extends TokenSequence
{
	public @S(10) TS_PrimaryType primary;
	public @S(20) @OPT TokenList<TS_TypeSuffix> suffixes;

	public static class TS_TypeSuffix extends TokenChooser
	{
		public @CHOICE static class TS_ArraySuffix extends TokenSequence
		{
			public @S(10) @NOSPACE PunctuationLeftBracket leftBracket;
			public @S(20) @NOSPACE PunctuationRightBracket rightBracket;
		}

		public @CHOICE static class TS_IndexedSuffix extends TokenSequence
		{
			public @S(10) @NOSPACE PunctuationLeftBracket leftBracket;
			public @S(20) TS_Type index;
			public @S(30) @NOSPACE PunctuationRightBracket rightBracket;
		}
	}
}
