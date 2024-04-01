// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.programmar.CSharp.CSharp_Type;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class CSharp_DotClass extends PrimaryOperator
{
	public @S(10) CSharp_Type jtype;
	public @S(20) PunctuationPeriod dot;
	public @S(30) CSharp_Keyword CLASS = new CSharp_Keyword("class");
}
