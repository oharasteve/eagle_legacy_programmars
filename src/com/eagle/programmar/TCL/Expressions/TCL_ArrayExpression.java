// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.TCL.Expressions;

import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class TCL_ArrayExpression extends PrimaryOperator
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) TokenList<TCL_Expression> values;
	public @S(30) PunctuationRightBrace rightBrace;
}
