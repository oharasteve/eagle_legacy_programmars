// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 12, 2022

package com.eagle.programmar.CSS.Qualifiers;

import com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
import com.eagle.programmar.CSS.Terminals.CSS_Literal;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class CSS_QualiferCode extends TokenSequence
{
	public @S(10) CSS_KeywordChoice CODE = new CSS_KeywordChoice(
			"controls",
			"data-original-title",
			"disabled",
			"hidden",
			"href",
			"title");
	public @S(20) @OPT CSS_BracketsHatEquals hatEquals;
	
	public static class CSS_BracketsHatEquals extends TokenSequence
	{
		public @S(10) CSS_Punctuation hat = new CSS_Punctuation('^');
		public @S(20) PunctuationEquals equals;
		public @S(30) CSS_Literal literal;
	}
}