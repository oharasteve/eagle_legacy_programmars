// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 2, 2026: TypeScript is JavaScript plus types. The type
// syntax lives here; the JavaScript rules carry optional hooks to it, so one grammar reads both.

package com.eagle.programmar.Javascript.TypeScript;

import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

/** <T, U> on a use. */
public class TS_TypeArguments extends TokenSequence
{
	public @S(10) @NOSPACE Javascript_Punctuation open = new Javascript_Punctuation("<");
	public @S(20) SeparatedList<TS_Type, PunctuationComma> arguments;
	public @S(30) @NOSPACE Javascript_Punctuation close = new Javascript_Punctuation(">");
}
