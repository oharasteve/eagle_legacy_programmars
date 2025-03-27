// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.tokens.TokenChooser;

public class Javascript_Type extends TokenChooser
{
	public @CHOICE Javascript_KeywordChoice XXbuitinType = new Javascript_KeywordChoice(
			"const", "let", "var", "Array", "Date", "Image", "String");
	public @CHOICE Javascript_Variable XXuserType;
}
