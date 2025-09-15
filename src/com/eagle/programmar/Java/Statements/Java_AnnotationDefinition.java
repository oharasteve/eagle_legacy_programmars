// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 15, 2025

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_Annotation;
import com.eagle.programmar.Java.Java_Method.Java_MethodModifier;
import com.eagle.programmar.Java.Java_Type;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Identifier;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_AnnotationDefinition extends TokenSequence
{
	public @S(10) @OPT Java_Annotation annotation;
	public @S(20) TokenList<Java_MethodModifier> modifiers;
	public @S(30) Java_Punctuation atSign = new Java_Punctuation('@');
	public @S(40) Java_Keyword INTERFACE = new Java_Keyword("interface");
	public @S(50) Java_Identifier id;
	public @S(60) PunctuationLeftBrace leftBrace;
	public @S(70) @OPT TokenList<Java_Comment> comments;
	public @S(80) @OPT Java_AnnotationParameter parameter;
	public @S(90) PunctuationRightBrace rightBrace;

	public static class Java_AnnotationParameter extends TokenSequence
	{
		public @S(10) Java_Type type;
		public @S(20) Java_Identifier id;
		public @S(30) PunctuationLeftParen leftParen;
		public @S(40) PunctuationRightParen rightParen;
		public @S(50) PunctuationSemicolon semicolon;
	}
}