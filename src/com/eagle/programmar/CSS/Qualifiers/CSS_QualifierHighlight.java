// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 12, 2022

package com.eagle.programmar.CSS.Qualifiers;

import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
import com.eagle.programmar.CSS.Terminals.CSS_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class CSS_QualifierHighlight extends TokenSequence
{
	public @S(10) CSS_Keyword HIGHLIGHT = new CSS_Keyword("highlight");
	public @S(20) PunctuationEquals equals;
	public @S(30) CSS_QualifierWhichHighlight which;

	public static class CSS_QualifierWhichHighlight extends TokenChooser
	{
		public @CHOICE CSS_KeywordChoice XXSTRONG = new CSS_KeywordChoice("strong");
		public @CHOICE CSS_Literal XXliteral;
	}
}