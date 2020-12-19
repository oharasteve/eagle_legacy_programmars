// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 21, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.CSharp_Data.CSharp_DataModifier;
import com.eagle.programmar.CSharp.Symbols.CSharp_Variable_Definition;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_Enum extends TokenSequence
{
	public @S(10) @OPT TokenList<CSharp_Annotation> annotation;
	public @S(20) @OPT @NEWLINE TokenList<CSharp_DataModifier> modifiers;
	public @S(30) CSharp_Keyword ENUM = new CSharp_Keyword("enum");
	public @S(40) CSharp_Variable_Definition id;
	public @S(50) @OPT CSharp_Enum_Basetype baseType;
	public @S(60) PunctuationLeftBrace leftBrace;
	public @S(70) @OPT TokenList<CSharp_Comment> comments;
	public @S(80) CSharp_Variable_Definition firstEnum;
	public @S(90) @OPT CSharp_EnumInitializer initializer;
	public @S(100) @OPT TokenList<CSharp_MoreEnums> moreEnums;
	public @S(110) @OPT @CURIOUS("Extra comma") PunctuationComma comma;
	public @S(120) PunctuationRightBrace rightBrace;
	public @S(130) @OPT PunctuationSemicolon semicolon;
	
	public static class CSharp_Enum_Basetype extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) CSharp_Type type;
	}
	
	public static class CSharp_MoreEnums extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) CSharp_Variable_Definition nextEnum;
		public @S(30) @OPT CSharp_EnumInitializer initialize;
	}
	
	public static class CSharp_EnumInitializer extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) CSharp_Expression expr;
	}
}
