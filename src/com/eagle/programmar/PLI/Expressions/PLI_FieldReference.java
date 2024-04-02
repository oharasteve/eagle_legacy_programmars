// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.PLI.Expressions;

import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class PLI_FieldReference extends PrimaryOperator
{
	public @S(10) PLI_Identifier_Reference var;
	public @S(20) PunctuationPeriod dot;
	public @S(30) PLI_Identifier_Reference field;
}
