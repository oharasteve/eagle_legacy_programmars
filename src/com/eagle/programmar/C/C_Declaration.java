// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2022

package com.eagle.programmar.C;

import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class C_Declaration extends TokenChooser
{
	private @SKIP static String[] declarations = new String[]
	{
		"fallthrough", "__fallthrough__",
		"maybe_unused",
		"nodiscard"
	};
	
	public @CHOICE static class C_DeclarationBrackets extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket1;
		public @S(20) PunctuationLeftBracket leftBracket2;
		public @S(30) C_KeywordChoice DECLARATION = new C_KeywordChoice(declarations);
		public @S(40) PunctuationRightBracket rightBracket1;
		public @S(50) PunctuationRightBracket rightBracket2;
		public @S(60) PunctuationSemicolon semicolon;
	}

	public @CHOICE static class C_DeclarationParens extends TokenSequence
	{
		public @S(10) C_Keyword ATTRIBUTE = new C_Keyword("__attribute__");
		public @S(20) PunctuationLeftParen leftParen1;
		public @S(30) PunctuationLeftParen leftParen2;
		public @S(40) C_KeywordChoice DECLARATION = new C_KeywordChoice(declarations);
		public @S(50) PunctuationRightParen rightParen1;
		public @S(60) PunctuationRightParen rightParen2;
		public @S(70) PunctuationSemicolon semicolon;
	}
}
