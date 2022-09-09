// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodModifiers;
import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodParameter;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class CSharp_SubscriptOperator extends TokenSequence
{
	public @S(10) @OPT @NEWLINE TokenList<CSharp_Comment> comment;
	public @S(20) @OPT TokenList<CSharp_Annotation> annotation;
	public @S(30) @OPT @NEWLINE TokenList<CSharp_MethodModifiers> modifiers;
	public @S(40) CSharp_Type returnType;
	public @S(50) CSharp_Keyword THIS = new CSharp_Keyword("this");
	public @S(60) PunctuationLeftBracket leftBracket;
	public @S(70) CSharp_MethodParameter parameter;
	public @S(80) PunctuationRightBracket rightBracket;
	public @S(90) CSharp_MethodImplementation implementation;
}
