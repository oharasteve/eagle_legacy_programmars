// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodParameter;
import com.eagle.programmar.CSharp.Symbols.CSharp_Variable_Definition;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_Property extends TokenSequence
{
	public @S(10) @OPT TokenList<CSharp_Annotation> annotation;
	public @S(20) @OPT TokenList<CSharp_PropertyModifier> modifier;
	public @S(30) CSharp_Type type;
	public @S(40) CSharp_Variable_Definition id;
	public @S(50) @OPT CSharp_PropertySubscript subscript;

	public @S(60) PunctuationLeftBrace leftBrace;
	public @S(70) @OPT TokenList<CSharp_GetterSetter> getSet;
	public @S(80) PunctuationRightBrace rightBrace;

	public static class CSharp_PropertyModifier extends TokenSequence
	{
		public @S(10) CSharp_KeywordChoice modifier = new CSharp_KeywordChoice(CSharp_Program.MODIFIERS);
	}

	public static class CSharp_PropertySubscript extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) @OPT SeparatedList<CSharp_MethodParameter, PunctuationComma> params;
		public @S(30) PunctuationRightBracket rightBracket;
	}

	public static class CSharp_GetterSetter extends TokenChooser
	{
		public @CHOICE static class CSharp_GetterNoBody extends TokenSequence
		{
			public @S(10) CSharp_Keyword get = new CSharp_Keyword("get");
			public @S(20) PunctuationSemicolon semicolon;
		}

		public @CHOICE static class CSharp_GetterBody extends TokenSequence
		{
			public @S(10) CSharp_Keyword get = new CSharp_Keyword("get");
			public @S(20) CSharp_Statement getBody;
		}

		public @CHOICE static class CSharp_SetterNoBody extends TokenSequence
		{
			public @S(10) @OPT CSharp_Keyword csPrivate = new CSharp_Keyword("private");
			public @S(20) CSharp_Keyword set = new CSharp_Keyword("set");
			public @S(30) PunctuationSemicolon semicolon;
		}

		public @CHOICE static class CSharp_SetterBody extends TokenSequence
		{
			public @S(10) CSharp_Keyword set = new CSharp_Keyword("set");
			public @S(20) CSharp_Statement setBody;
		}
	}
}
