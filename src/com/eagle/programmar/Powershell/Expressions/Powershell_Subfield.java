// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Powershell_ExpressionList;
import com.eagle.programmar.Powershell.Symbols.Powershell_Variable_Reference;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Powershell_Subfield extends PrecedenceOperator
{
	public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationPeriod dot;
	public @S(30) Powershell_Variable_Reference right;
	public @S(40) @OPT Powershell_SubfieldArgs args;
	
	public static class Powershell_SubfieldArgs extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT Powershell_ExpressionList arguments;
		public @S(30) PunctuationRightParen rightParen;
	}
}
