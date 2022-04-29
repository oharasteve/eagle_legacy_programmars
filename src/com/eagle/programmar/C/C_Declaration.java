// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2022

package com.eagle.programmar.C;

import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class C_Declaration extends TokenSequence
{
	public @S(10) C_Punctuation leftBracket1 = new C_Punctuation("[");
	public @S(20) C_Punctuation leftBracket2 = new C_Punctuation("[");
	public @S(30) C_KeywordChoice DECLARATION = new C_KeywordChoice(
			"fallthrough", "__fallthrough__", "maybe_unused", "nodiscard");
	public @S(40) C_Punctuation rightBracket1 = new C_Punctuation("]");
	public @S(50) C_Punctuation rightBracket2 = new C_Punctuation("]");
	public @S(60) PunctuationSemicolon semicolon;
}
