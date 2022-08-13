// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 12, 2022

package com.eagle.programmar.CSS.Values;

import com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
import com.eagle.programmar.CSS.Terminals.CSS_Number;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CSS_NumericValue extends TokenSequence
{
	public @S(10) CSS_Number number;
	public @S(20) @OPT CSS_NumericSuffix suffix;
	public @S(30) @OPT CSS_SlashNumber slash;
	
	public static class CSS_NumericSuffix extends TokenChooser
	{
		public @CHOICE CSS_KeywordChoice units = new CSS_KeywordChoice(
				"deg",
				"em",
				"in",
				"px",
				"s",
				"vw"
		);
		public @CHOICE CSS_Punctuation percent = new CSS_Punctuation('%');
	}
	
	public static class CSS_SlashNumber extends TokenSequence
	{
		public @S(10) PunctuationSlash slash;
		public @S(20) CSS_Number number;
		public @S(30) @OPT CSS_NumericSuffix suffix;
	}
}