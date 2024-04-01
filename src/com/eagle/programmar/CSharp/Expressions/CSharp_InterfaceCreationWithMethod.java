// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.programmar.CSharp.CSharp_Method;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_InterfaceCreationWithMethod extends PrimaryOperator
{
	public @S(10) CSharp_Keyword NEW = new CSharp_Keyword("new");
	public @S(20) CSharp_KeywordChoice jinterface = new CSharp_KeywordChoice(
			"Runnable", "ActionListener", "WindowAdapter");
	public @S(30) @NOSPACE PunctuationLeftParen leftParen;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;
	public @S(50) PunctuationLeftBrace leftBrace;
	public @S(60) @NOSPACE CSharp_Method method;
	public @S(70) @NOSPACE PunctuationRightBrace rightBrace;
}
