// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 2, 2026: one parameter of a function type or signature.

package com.eagle.programmar.Javascript.TypeScript;

import com.eagle.programmar.Javascript.Symbols.Javascript_Field_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.TokenSequence;

public class TS_FunctionTypeParam extends TokenSequence
	{
	public @S(10) @OPT Javascript_Punctuation rest = new Javascript_Punctuation("...");
	public @S(20) Javascript_Field_Definition name;
	public @S(30) @OPT Javascript_Punctuation optional = new Javascript_Punctuation("?");
	public @S(40) @OPT TS_TypeAnnotation type;
}
