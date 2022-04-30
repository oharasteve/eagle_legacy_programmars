// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2013

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_KeywordChoice;
import com.eagle.programmar.Python.Terminals.Python_Literal;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Python_Type extends TokenChooser implements AbstractType
{
	public @CHOICE Python_KeywordChoice TYPES = new Python_KeywordChoice("bool", "float", "int", "object", "str");
	public @LAST Python_Literal typeName;
	public @CHOICE Python_Punctuation dots = new Python_Punctuation("...");
	
	public @CHOICE static class Python_Regular_Class extends TokenSequence
	{
		public @S(10) SeparatedList<Python_TypeName,PunctuationPeriod> superClass;
		
		public static class Python_TypeName extends TokenChooser
		{
			public @CHOICE Python_Keyword SELF = new Python_Keyword("self");
			public @CHOICE Python_Identifier_Reference id;
		}
	}
	
	public @FIRST static class Python_StrongType extends TokenSequence
	{
		public @S(10) Python_KeywordChoice TUPLE = new Python_KeywordChoice(
				"Dict",
				"List",
				"Optional",
				"Tuple",
				"Sequence",
				"Union");
		public @S(20) PunctuationLeftBracket leftBracket;
		public @S(30) SeparatedList<Python_Type,PunctuationComma> types;
		public @S(40) PunctuationRightBracket rightBracket;
	}
}
