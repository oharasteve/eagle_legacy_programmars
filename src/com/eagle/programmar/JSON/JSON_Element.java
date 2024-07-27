// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.JSON;

import com.eagle.programmar.JSON.Terminals.JSON_KeywordChoice;
import com.eagle.programmar.JSON.Terminals.JSON_Literal;
import com.eagle.programmar.JSON.Terminals.JSON_Number;
import com.eagle.tokens.TokenChooser;

public class JSON_Element extends TokenChooser
{
	public @CHOICE JSON_Literal XXliteral;
	public @CHOICE JSON_Number XXnumber;
	public @CHOICE JSON_Object XXobject;
	public @CHOICE JSON_Dictionary XXdictionary;
	public @CHOICE JSON_KeywordChoice XXbuiltIn = new JSON_KeywordChoice("null", "true", "false");
}