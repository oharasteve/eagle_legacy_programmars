// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.JSON;

import com.eagle.core.AbstractLanguage;
import com.eagle.programmar.JSON.Terminals.JSON_Comment;
import com.eagle.programmar.JSON.Terminals.JSON_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class JSON_Program extends AbstractLanguage
{
	public static final String JSON = "JSON";

	public JSON_Program()
	{
		super(JSON, new JSON_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://www.w3schools.com/json/";
	}

	public @S(10) @OPT @CURIOUS("Strange file header") JSON_Punctuation header = new JSON_Punctuation(")]}'");
	public @S(20) TokenList<JSON_ElementOrComment> elements;

	public static class JSON_ElementOrComment extends TokenChooser
	{
		public @CHOICE JSON_Comment XXcomment;
		public @CHOICE JSON_Element XXelement;
	}
}