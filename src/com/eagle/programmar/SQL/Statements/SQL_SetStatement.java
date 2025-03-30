// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Expressions.SQL_VariableExpression;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_SetStatement extends TokenSequence
{
	public @S(10) @DOC("sql_set.asp") SQL_Keyword SET = new SQL_Keyword("SET");
	public @S(20) SQL_VariableExpression var;
	public @S(30) PunctuationEquals equals;
	public @S(40) SQL_Expression expr;
	public @S(50) PunctuationSemicolon semicolon;
}
