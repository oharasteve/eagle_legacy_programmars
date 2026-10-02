// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 2, 2026: TypeScript is JavaScript plus types. The type
// syntax lives here; the JavaScript rules carry optional hooks to it, so one grammar reads both.

package com.eagle.programmar.Javascript.TypeScript;

import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

/** A type: a union of intersections. `| A` may lead, as in a multi-line alias. */
public class TS_Type extends TokenSequence
{
	public @S(5) @OPT Javascript_Punctuation leadingBar = new Javascript_Punctuation("|");
	public @S(10) TS_Intersection first;
	public @S(20) @OPT TokenList<TS_MoreUnion> more;

	public static class TS_MoreUnion extends TokenSequence
	{
		public @S(10) Javascript_Punctuation bar = new Javascript_Punctuation("|");
		public @S(20) TS_Intersection type;
	}

	public static class TS_Intersection extends TokenSequence
	{
		public @S(10) TS_PostfixType first;
		public @S(20) @OPT TokenList<TS_MoreIntersection> more;

		public static class TS_MoreIntersection extends TokenSequence
		{
			public @S(10) Javascript_Punctuation amp = new Javascript_Punctuation("&");
			public @S(20) TS_PostfixType type;
		}
	}
}
