// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Gupta.Expressions;

import com.eagle.programmar.Gupta.Symbols.Gupta_Identifier_Reference;
import com.eagle.tokens.PrimaryOperator;

public class Gupta_IdentifierExpression extends PrimaryOperator
{
	public @S(10) Gupta_Identifier_Reference identifier;
}
