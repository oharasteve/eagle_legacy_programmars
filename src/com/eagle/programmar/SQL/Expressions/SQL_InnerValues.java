// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.SQL.Expressions;

import com.eagle.programmar.SQL.Statements.SQL_ValuesStatement;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class SQL_InnerValues extends PrimaryOperator
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) SQL_ValuesStatement innerValues;
	public @S(30) PunctuationRightParen rightParen;
}
