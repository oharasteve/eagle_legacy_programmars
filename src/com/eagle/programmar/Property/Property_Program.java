// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 29, 2014

package com.eagle.programmar.Property;

import com.eagle.core.EagleLanguage;
import com.eagle.programmar.Property.Terminals.Property_Comment;
import com.eagle.programmar.Property.Terminals.Property_EndOfLine;
import com.eagle.programmar.Property.Terminals.Property_Identifier;
import com.eagle.programmar.Property.Terminals.Property_RestOfLine;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Property_Program extends EagleLanguage
{
	public static final String NAME = "Property";
	
	public Property_Program()
	{
		super(NAME, new Property_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://www.w3schools.com/json/";
	}
	
	public @S(10) TokenList<Property_Element> elements;
	
	public static class Property_Element extends TokenChooser
	{
		public @CHOICE Property_Comment comment;
		public @CHOICE Property_Value pair;
		public @CHOICE Property_EndOfLine eoln;
	}
	
	public static class Property_Value extends TokenSequence
	{
		public @S(10) @OPT PunctuationPeriod period;
		public @S(20) @OPT SeparatedList<Property_Identifier,PunctuationPeriod> ids;
		public @S(30) PunctuationEquals equals;
		public @S(40) Property_RestOfLine value;
	}
}
