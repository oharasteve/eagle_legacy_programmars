// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2022

package com.eagle.programmar.CSS.Directives;

import com.eagle.programmar.CSS.Terminals.CSS_FileName;
import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_Literal;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.programmar.CSS.Values.CSS_URL_Value;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSS_AtImport extends TokenSequence
{
	public @S(10) CSS_Punctuation at = new CSS_Punctuation('@');
	public @S(20) CSS_Keyword IMPORT = new CSS_Keyword("import");
	public @S(30) CSS_ImportWhat what;
	public @S(40) PunctuationSemicolon semicolon;
	
	public static class CSS_ImportWhat extends TokenChooser
	{
		public @FIRST CSS_URL_Value urlValue;
		public @CHOICE CSS_FileName fileName;
		public @CHOICE CSS_Literal literal;
	}
}