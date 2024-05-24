// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2022

package com.eagle.programmar.CSS.Directives;

import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
import com.eagle.programmar.CSS.Terminals.CSS_Literal;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSS_AtCharset extends TokenSequence
{
	public @S(10) CSS_Punctuation at = new CSS_Punctuation('@');
	public @S(20) CSS_Keyword CHARSET = new CSS_Keyword("charset");
	public @S(30) @OPT CSS_AtCharsetACD acd;
	public @S(40) CSS_Literal charset;
	public @S(50) PunctuationSemicolon semicolon;

	public static class CSS_AtCharsetACD extends TokenSequence
	{
		public @S(10) CSS_Punctuation backSlash = new CSS_Punctuation("\\");
		public @S(20) CSS_KeywordChoice ACD = new CSS_KeywordChoice("A", "C", "D", "9");
	}
}