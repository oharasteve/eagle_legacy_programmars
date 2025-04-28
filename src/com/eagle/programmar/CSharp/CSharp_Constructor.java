// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 24, 2015

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodBody;
import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodModifier;
import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodParameter;
import com.eagle.programmar.CSharp.Symbols.CSharp_Current_Class_Reference;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_Constructor extends TokenSequence
{
	public @S(10) @OPT @NEWLINE TokenList<CSharp_Comment> comment;
	public @S(20) @OPT TokenList<CSharp_Annotation> annotation;
	public @S(30) @OPT TokenList<CSharp_MethodModifier> modifiers;
	public @S(40) @OPT CSharp_Punctuation tilde = new CSharp_Punctuation("~");
	public @S(50) CSharp_Current_Class_Reference constructorName;
	public @S(60) PunctuationLeftParen leftParen;
	public @S(70) @OPT SeparatedList<CSharp_MethodParameter, PunctuationComma> params;
	public @S(80) PunctuationRightParen rightParen;
	public @S(90) @OPT CSharp_ExtendsBase extendsBase;
	public @S(100) CSharp_MethodBody body;

	public static class CSharp_ExtendsBase extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) CSharp_KeywordChoice baseOrthis = new CSharp_KeywordChoice("base", "this");
		public @S(30) PunctuationLeftParen leftParen;
		public @S(40) @OPT CSharp_ArgumentList argList;
		public @S(50) PunctuationRightParen rightParen;
	}
}
