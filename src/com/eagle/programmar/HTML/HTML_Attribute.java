// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2011

package com.eagle.programmar.HTML;

import com.eagle.programmar.HTML.Terminals.HTML_HexNumber;
import com.eagle.programmar.HTML.Terminals.HTML_Identifier;
import com.eagle.programmar.HTML.Terminals.HTML_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Literal;
import com.eagle.programmar.HTML.Terminals.HTML_Number;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class HTML_Attribute extends TokenSequence
{
	public @S(10) HTML_IdentifierOrKeyword attribute;
	public @S(20) @OPT HTML_AttributeValue val;
	
	public static class HTML_AttributeValue extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationEquals equals;
		public @S(20) @NOSPACE HTML_Value value;
	}
	
	public static class HTML_IdentifierOrKeyword extends TokenChooser
	{
		public @CHOICE HTML_Identifier attribute;
		public @CHOICE HTML_Keyword style = new HTML_Keyword("style");
		
		public @CHOICE static class HTML_Namespace extends TokenSequence
		{
			public @S(10) HTML_Identifier id1;
			public @S(20) PunctuationColon colon;
			public @S(30) HTML_Identifier id2;
		}
	}
	
	public static class HTML_Value extends TokenChooser
	{
		public @FIRST HTML_HexNumber hex;
		public @FIRST HTML_Number number;
		public @FIRST HTML_Literal literal;
		
		public @CHOICE static class HTML_Id_Value extends TokenSequence
		{
			public @S(10) HTML_Identifier id;
			public @S(20) @OPT HTML_Id_DotValue dotValue;
			
			public static class HTML_Id_DotValue extends TokenSequence
			{
				public @S(10) HTML_PunctuationChoice dotOrColon = new HTML_PunctuationChoice(".", ":");
				public @S(20) HTML_Identifier id;
			}
		}
		
		public @CHOICE static class HTML_Label extends TokenSequence
		{
			public @S(10) HTML_Punctuation poundSign = new HTML_Punctuation('#');
			public @S(20) HTML_Identifier label;
		}
		
		public @CHOICE static class HTML_Strange_Number extends TokenSequence
		{
			public @S(10) HTML_Punctuation plus = new HTML_Punctuation('+');
			public @S(20) PunctuationPeriod point;
			public @S(30) HTML_Number number;
		}
	}
}
