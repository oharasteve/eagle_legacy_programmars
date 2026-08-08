// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2014

package com.eagle.programmar.CSS;

import com.eagle.programmar.CSS.Qualifiers.CSS_QualiferCode;
import com.eagle.programmar.CSS.Qualifiers.CSS_QualifierClass;
import com.eagle.programmar.CSS.Qualifiers.CSS_QualifierDataToggle;
import com.eagle.programmar.CSS.Qualifiers.CSS_QualifierDir;
import com.eagle.programmar.CSS.Qualifiers.CSS_QualifierFrame;
import com.eagle.programmar.CSS.Qualifiers.CSS_QualifierHighlight;
import com.eagle.programmar.CSS.Qualifiers.CSS_QualifierRole;
import com.eagle.programmar.CSS.Qualifiers.CSS_QualifierRow;
import com.eagle.programmar.CSS.Qualifiers.CSS_QualifierType;
import com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class CSS_Qualifier extends TokenSequence
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) CSS_QualifierChoice qual;
	public @S(30) PunctuationRightBracket rightBracket;
	public @S(40) @OPT CSS_Punctuation greaterThan = new CSS_Punctuation('>');

	public static class CSS_QualifierChoice extends TokenChooser
	{
		public @CHOICE CSS_KeywordChoice XXchoice = new CSS_KeywordChoice(
				"for",
				"has-element-focus",
				"multiple",
				"readonly",
				"selected",
				"size",
				"subframe");

		public @CHOICE CSS_QualifierClass XXqualifierClass;
		public @CHOICE CSS_QualiferCode XXqualifierCode;
		public @CHOICE CSS_QualifierDataToggle XXqualifierDataToggle;
		public @CHOICE CSS_QualifierDir XXqualifierDir;
		public @CHOICE CSS_QualifierFrame XXqualifierFrame;
		public @CHOICE CSS_QualifierHighlight XXqualifierHighlight;
		public @CHOICE CSS_QualifierRole XXqualifierRole;
		public @CHOICE CSS_QualifierRow XXqualifierRow;
		public @CHOICE CSS_QualifierType XXqualifierType;
	}
}
