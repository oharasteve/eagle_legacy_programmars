// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 20, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Type;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_VaArgExpr extends PrimaryOperator
{
	public @S(10) C_KeywordChoice VA_ARG = new C_KeywordChoice("va_arg", "__builtin_va_arg");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) C_Expression expr;
	public @S(40) PunctuationComma comma;
	public @S(50) C_Type ctype;
	public @S(60) PunctuationRightParen rightParen;
}
