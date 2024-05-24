// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Expressions;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Delphi_Type;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Delphi_Cast extends PrimaryOperator
{
	public @S(10) Delphi_Type type;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Delphi_Expression expr;
	public @S(40) PunctuationRightParen rightParen;
}
