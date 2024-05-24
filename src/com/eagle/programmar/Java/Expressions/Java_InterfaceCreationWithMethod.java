// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.programmar.Java.Java_Method;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_InterfaceCreationWithMethod extends PrimaryOperator
{
	public @S(10) Java_Keyword NEW = new Java_Keyword("new");
	public @S(20) Java_KeywordChoice jinterface = new Java_KeywordChoice("Runnable", "ActionListener", "WindowAdapter");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) PunctuationLeftBrace leftBrace;
	public @S(60) Java_Method method;
	public @S(70) PunctuationRightBrace rightBrace;
}
