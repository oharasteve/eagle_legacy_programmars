// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 2, 2026: TypeScript is JavaScript plus types. The type
// syntax lives here; the JavaScript rules carry optional hooks to it, so one grammar reads both.

package com.eagle.programmar.Javascript.TypeScript;

import com.eagle.programmar.Javascript.Symbols.Javascript_Class_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;

/** <T, U extends V = W> on a declaration. */
public class TS_Generics extends TokenSequence
{
	public @S(10) @NOSPACE Javascript_Punctuation open = new Javascript_Punctuation("<");
	public @S(20) SeparatedList<TS_TypeParameter, PunctuationComma> parameters;
	public @S(25) @OPT PunctuationComma trailingComma;
	public @S(30) @NOSPACE Javascript_Punctuation close = new Javascript_Punctuation(">");

	public static class TS_TypeParameter extends TokenSequence
	{
		public @S(5) @OPT Javascript_Keyword CONST = new Javascript_Keyword("const");
		public @S(10) Javascript_Class_Definition name;
		public @S(20) @OPT TS_Constraint constraint;
		public @S(30) @OPT TS_Default fallback;

		public static class TS_Constraint extends TokenSequence
		{
			public @S(10) Javascript_Keyword EXTENDS = new Javascript_Keyword("extends");
			public @S(20) TS_Type type;
		}

		public static class TS_Default extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) TS_Type type;
		}
	}
}
