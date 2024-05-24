// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 12, 2022

package com.eagle.programmar.CSS.Qualifiers;

import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
import com.eagle.programmar.CSS.Terminals.CSS_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class CSS_QualifierType extends TokenSequence
{
	public @S(10) CSS_Keyword TYPE = new CSS_Keyword("type");
	public @S(20) PunctuationEquals equals;
	public @S(30) CSS_QualifierWhichType which;
	public @S(40) @OPT @CURIOUS("What does this mean>") CSS_Keyword I = new CSS_Keyword("i");

	public static class CSS_QualifierWhichType extends TokenChooser
	{
		public @CHOICE CSS_KeywordChoice value = new CSS_KeywordChoice("button", "checkbox", "date", "datetime-local",
				"email", "file", "month", "number", "password", "radio", "range", "reset", "search", "submit", "time",
				"text");
		public @CHOICE CSS_Literal literal;
	}
}
