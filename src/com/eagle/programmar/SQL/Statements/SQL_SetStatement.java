// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.SQL_Expression.SQL_VariableExpression;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_SetStatement extends TokenSequence
{
	public @DOC("sql_set.asp") SQL_Keyword SELECT = new SQL_Keyword("SET");
	public SQL_VariableExpression var;
	public PunctuationEquals equals;
	public SQL_Expression expr;
	public PunctuationSemicolon semicolon;
}
