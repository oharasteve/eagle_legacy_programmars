// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Python_BracesColons extends PrimaryOperator
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) @OPT Python_EndOfLine eoln1;
	public @S(30) @OPT @SYNTAX(Python_Multiline_Syntax.class) Python_Dictionary dictionary;
	public @S(40) @OPT Python_EndOfLine eoln2;
	public @S(50) PunctuationRightBrace rightBrace;

	public static class Python_Dictionary extends TokenSequence
	{
		public @S(10) @OPT TokenList<Python_Comment> comment1;
		public @S(20) @OPT Python_DictionaryElement element;
		public @S(30) @OPT TokenList<Python_MoreDictionaryElement> nextElement;
		public @S(40) @OPT PunctuationComma comma;
		public @S(50) @OPT TokenList<Python_Comment> comment2;

		public static class Python_DictionaryElement extends TokenSequence
		{
			public @S(10) Python_Expression key;
			public @S(20) PunctuationColon colon;
			public @S(30) @OPT Python_EndOfLine eoln;
			public @S(40) @OPT Python_Comment comment;
			public @S(50) Python_Expression value;
		}

		public static class Python_MoreDictionaryElement extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) @OPT TokenList<Python_Comment> comment;
			public @S(30) Python_DictionaryElement element;
		}
	}

	public static Python_Expression generateDictionary(AbstractToken source)
	{
		Python_BracesColons braces = new Python_BracesColons();
		braces.leftBrace = new PunctuationLeftBrace();
		braces.rightBrace = new PunctuationRightBrace();
		braces.setTransformationSource(source);
		return Python_Generator.wrapExpression(braces);
	}
}
