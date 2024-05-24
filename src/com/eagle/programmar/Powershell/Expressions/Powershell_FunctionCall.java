// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.programmar.Powershell.Powershell_ExpressionList;
import com.eagle.programmar.Powershell.Powershell_Library;
import com.eagle.programmar.Powershell.Symbols.Powershell_Function_Reference;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Powershell_FunctionCall extends PrimaryOperator
{
	public @S(10) @OPT Powershell_DiscardResult discardResult;
	public @S(20) @OPT Powershell_Library library;
	public @S(30) Powershell_Function_Reference func;
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) @OPT Powershell_ExpressionList arguments;
	public @S(60) PunctuationRightParen rightParen;

	public static class Powershell_DiscardResult extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) Powershell_Keyword VOID = new Powershell_Keyword("void");
		public @S(30) PunctuationRightBracket rightBracket;
	}
}
