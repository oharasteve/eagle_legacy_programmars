// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.JSON;

import com.eagle.programmar.JSON.Terminals.JSON_KeywordChoice;
import com.eagle.programmar.JSON.Terminals.JSON_Literal;
import com.eagle.programmar.JSON.Terminals.JSON_Number;
import com.eagle.tokens.TokenChooser;

public class JSON_Element extends TokenChooser
{
	public @CHOICE JSON_Literal literal;
	public @CHOICE JSON_Number number;
	public @CHOICE JSON_Object object;
	public @CHOICE JSON_Dictionary dictionary;
	public @CHOICE JSON_KeywordChoice builtIn = new JSON_KeywordChoice("null", "true", "false");
}