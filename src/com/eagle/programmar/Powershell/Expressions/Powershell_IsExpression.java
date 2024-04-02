// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Powershell_Type;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Powershell_IsExpression extends PrecedenceOperator
{
	public @S(10) Powershell_Expression expr = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Powershell_Keyword IS = new Powershell_Keyword("-is"); 
	public @S(30) PunctuationLeftBracket leftBracket;
	public @S(40) Powershell_Type type;
	public @S(50) PunctuationRightBracket rightBracket;
}
