// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2013

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
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
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Python_Type extends TokenChooser implements AbstractType
{
	public @LAST Python_Literal typeName;
	public @CHOICE Python_Punctuation dots = new Python_Punctuation("...");

	public @CHOICE static class Python_TypeTuple extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<Python_Type,PunctuationComma> types;
		public @S(30) PunctuationRightParen rightParen;
	}
	
	public @CHOICE static class Python_TypeList extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) @OPT SeparatedList<Python_Type,PunctuationComma> types;
		public @S(30) PunctuationRightBracket rightBracket;
	}
	
	public @CHOICE Python_KeywordChoice PRIMITIVES = new Python_KeywordChoice(
			"Any",
			"None",
			"bool",
			"bytes",
			"float",
			"int",
			"object",
			"str",
			"Text");
	
	public @FIRST static class Python_StructuredType extends TokenSequence
	{
		public @S(10) @OPT Python_TypeTyping typing;
		public @S(20) Python_KeywordChoice TUPLE = new Python_KeywordChoice(
				"Awaitable", "awaitable",
				"Callable", "callable",
				"DefaultDict", "defaultdict",
				"Dict", "dict",
				"Generic", "generic",
				"Iterable", "iterable",
				"Iterator", "iterator",
				"List", "list",
				"Mapping", "mapping",
				"MutableMapping", "mutablemapping",
				"Optional", "optional",
				"Sequence", "sequence",
				"Set", "set",
				"Tuple", "tuple",
				"Type", "type",
				"Union", "union");
		public @S(30) PunctuationLeftBracket leftBracket;
		public @S(40) @OPT Python_EndOfLine eoln;
		public @S(50) SeparatedList<Python_Type,PunctuationComma> types;
		public @S(60) PunctuationRightBracket rightBracket;
		
		public static class Python_TypeTyping extends TokenSequence
		{
			public @S(10) Python_Keyword TYPING = new Python_Keyword("typing");
			public @S(20) PunctuationPeriod dot;
		}
	}
	
	public @CHOICE static class Python_Regular_Class extends TokenSequence
	{
		public @S(10) SeparatedList<Python_TypeName,PunctuationPeriod> superClass;
		
		public static class Python_TypeName extends TokenChooser
		{
			public @CHOICE Python_Keyword SELF = new Python_Keyword("self");
			public @CHOICE Python_Identifier_Reference id;
		}
	}
}
