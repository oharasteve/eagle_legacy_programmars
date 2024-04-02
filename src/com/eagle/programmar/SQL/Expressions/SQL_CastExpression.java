// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Expressions;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.SQL_Type;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class SQL_CastExpression extends PrimaryOperator
{
	public @S(10) SQL_Keyword CAST = new SQL_Keyword("CAST");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) SQL_Expression expr;
	public @S(40) SQL_Keyword AS = new SQL_Keyword("AS");
	public @S(50) SQL_Type type;
	public @S(60) PunctuationRightParen rightParen;
}
